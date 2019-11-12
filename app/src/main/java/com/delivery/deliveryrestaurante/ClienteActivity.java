package com.delivery.deliveryrestaurante;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Carrito;
import com.delivery.deliveryrestaurante.clases.Producto;
import com.delivery.deliveryrestaurante.clases.ProductoAdaptador;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ArrayList<Producto> productos = new ArrayList<>();
    private ProductoAdaptador adaptador;
    private Spinner categorias;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lista;
        lista = findViewById(R.id.lista_general);
        categorias = findViewById(R.id.spinner);
        adaptador = new ProductoAdaptador(this, productos);
        lista.setAdapter(adaptador);

        db = FirebaseFirestore.getInstance();

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cargarDatos(categorias.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Carrito.agregar(productos.get(position));
                Toast.makeText(ClienteActivity.this, "Agregado al carrito" , Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.carrito);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClienteActivity.this, CarritoActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cliente) {

            // Validar clave de acceso
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Acceso al restaurante");
            builder.setMessage("Ingrese la clave de acceso");

            input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String clave = input.getText().toString().trim().toLowerCase();
                    if (clave.equals("delivery")) {
                        Toast.makeText(ClienteActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(ClienteActivity.this, RestauranteActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    } else {
                        Toast.makeText(ClienteActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            });

            builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void cargarDatos(String categoria) {
        productos.clear();
        adaptador.notifyDataSetChanged();

        db.collection("productos").whereEqualTo("categoria", categoria)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot doc : list) {
                                Producto producto = doc.toObject(Producto.class);
                                productos.add(producto);
                            }

                            adaptador.notifyDataSetChanged();
                        }
                    }
                });
    }

}
