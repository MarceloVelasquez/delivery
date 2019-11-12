package com.delivery.deliveryrestaurante;

import android.content.Intent;
import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Carrito;
import com.delivery.deliveryrestaurante.clases.Pedido;
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
import android.widget.Toast;

public class DatosActivity extends AppCompatActivity {
    FirebaseFirestore db;
    EditText nombre;
    EditText telefono;
    EditText direccion;
    EditText efectivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nombre = findViewById(R.id.datos_nombre);
        telefono = findViewById(R.id.datos_telefono);
        direccion = findViewById(R.id.datos_direccion);
        efectivo = findViewById(R.id.datos_dinero);
        Button hacerPedido = findViewById(R.id.hacer_pedido);
        db = FirebaseFirestore.getInstance();
        hacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
    }

    public void registrar() {
        final Pedido ped = new Pedido(nombre.getText().toString(),telefono.getText().toString(),direccion.getText().toString(),0,0, Double.parseDouble(efectivo.getText().toString()),Carrito.getProductos());


        db.collection("pedidos")
                .add(ped)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        db.collection("pedidos").document(documentReference.getId())
                                .update("id", documentReference.getId())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(DatosActivity.this, "Pedido registrado", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(DatosActivity.this, ClienteActivity.class));
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(DatosActivity.this, "Pedido no registrado" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DatosActivity.this, "Pedido no registrado" + e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }



}
