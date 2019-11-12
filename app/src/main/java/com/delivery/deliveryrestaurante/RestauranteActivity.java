package com.delivery.deliveryrestaurante;

import android.content.Intent;
import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Pedido;
import com.delivery.deliveryrestaurante.clases.PedidoAdaptador;
import com.delivery.deliveryrestaurante.clases.Pedidos;
import com.delivery.deliveryrestaurante.clases.Producto;
import com.delivery.deliveryrestaurante.clases.ProductoAdaptador;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RestauranteActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private PedidoAdaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lista = findViewById(R.id.lista_pedidos);
        adaptador = new PedidoAdaptador(this,Pedidos.getPedidos());
        lista.setAdapter(adaptador);
        cargarDatos();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detalle = new Intent(RestauranteActivity.this, DetalleActivity.class);
                detalle.putExtra("pedido",position);
                startActivity(detalle);
            }
        });
    }
    public void cargarDatos(){
        adaptador.clear();
        db = FirebaseFirestore.getInstance();
        db.collection("pedidos")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot doc : list) {
                                Pedido pedido = doc.toObject(Pedido.class);
                                Pedidos.agregar(pedido);
                            }

                            adaptador.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_restaurante, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_restaurante) {
            startActivity(new Intent(RestauranteActivity.this, ClienteActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        }

        if (id == R.id.action_registrar) {
            startActivity(new Intent(RestauranteActivity.this, RegistroActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}
