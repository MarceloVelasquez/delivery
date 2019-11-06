package com.delivery.deliveryrestaurante;

import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Producto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Spinner categorias;
    private EditText nombre;
    private EditText precio;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = FirebaseFirestore.getInstance();

        categorias = findViewById(R.id.registro_categoria);
        nombre = findViewById(R.id.registro_nombre);
        precio = findViewById(R.id.registro_precio);
        boton = findViewById(R.id.registrar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    public void registrar() {
        final Producto pro = new Producto("imagen", nombre.getText().toString(), categorias.getSelectedItem().toString(), Double.parseDouble(precio.getText().toString()), "ID");


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

    public void limpiar() {
        nombre.getText().clear();
        precio.getText().clear();
    }

}
