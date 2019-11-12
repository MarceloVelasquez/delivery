package com.delivery.deliveryrestaurante;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class RegistroActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private StorageReference storage;
    private Spinner categorias;
    private EditText nombre;
    private EditText precio;
    private Button boton;
    private Button botonFoto;
    private String imagen;
    private boolean imagenCargada;
    private static final int GALLERY_INTENT = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imagenCargada = false;

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference();

        categorias = findViewById(R.id.registro_categoria);
        nombre = findViewById(R.id.registro_nombre);
        precio = findViewById(R.id.registro_precio);
        boton = findViewById(R.id.registrar);
        botonFoto = findViewById(R.id.addFoto);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
        botonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirImagen();
            }
        });
    }

    public void registrar() {

        if (!imagenCargada || TextUtils.isEmpty(nombre.getText().toString()) || TextUtils.isEmpty(precio.getText().toString())){
            Toast.makeText(this, "Complete todos los datos por favor", Toast.LENGTH_SHORT).show();
        }else {

            final Producto pro = new Producto(imagen, nombre.getText().toString(), categorias.getSelectedItem().toString(), Double.parseDouble(precio.getText().toString()), "ID");
            boton.setEnabled(false);
            db.collection("productos")
                    .add(pro)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            db.collection("productos").document(documentReference.getId())
                                    .update("id", documentReference.getId())
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegistroActivity.this, "Producto registrado", Toast.LENGTH_SHORT).show();
                                            limpiar();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegistroActivity.this, "Producto no registrado" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegistroActivity.this, "Producto no registrado" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    public void abrirImagen(){
        Intent intend = new Intent(Intent.ACTION_PICK).setType("image/*");
        startActivityForResult(intend,GALLERY_INTENT);

    }

    public void limpiar() {
        nombre.getText().clear();
        precio.getText().clear();
        botonFoto.setText(R.string.boton_foto);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode ==RESULT_OK ){
            Uri file = data.getData();
            final StorageReference ref = storage.child("images/"+file.getLastPathSegment()+".jpg");
            UploadTask uploadTask = ref.putFile(file);

//           Register observers to listen for when the download is done or if it fails
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(RegistroActivity.this, "Imagen no agregada", Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imagen = uri.toString();
                            boton.setEnabled(true);
                            imagenCargada = true;
                            botonFoto.setText(R.string.boton_foto_agregada);
                            Toast.makeText(RegistroActivity.this, "Imagen agregada", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                        }
                    });
                }
            });
        }

    }

}
