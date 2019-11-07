package com.delivery.deliveryrestaurante;

import android.content.Intent;
import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Carrito;
import com.delivery.deliveryrestaurante.clases.ProductoAdaptador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CarritoActivity extends AppCompatActivity {
    private ListView lista;
    private ProductoAdaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista_carrito);
        adaptador = new ProductoAdaptador(this, Carrito.getProductos());
        lista.setAdapter(adaptador);
        final Button comprar = findViewById(R.id.comprar);
        if (!Carrito.getProductos().isEmpty()) comprar.setEnabled(true);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Carrito.eliminar(position);
                Toast.makeText(CarritoActivity.this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                if (Carrito.getProductos().isEmpty()) comprar.setEnabled(false);
                adaptador.notifyDataSetChanged();
            }
        });
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarritoActivity.this, DatosActivity.class));
            }
        });

    }

}
