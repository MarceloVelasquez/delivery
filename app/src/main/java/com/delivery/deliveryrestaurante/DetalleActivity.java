package com.delivery.deliveryrestaurante;

import android.os.Bundle;

import com.delivery.deliveryrestaurante.clases.Pedido;
import com.delivery.deliveryrestaurante.clases.Pedidos;
import com.delivery.deliveryrestaurante.clases.ProductoAdaptador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView nombre = findViewById(R.id.detalle_nombre);
        TextView direccion = findViewById(R.id.detalle_direccion);
        ListView lista = findViewById(R.id.detalle_lista);

        Pedido pedido = Pedidos.getPedidos().get(getIntent().getIntExtra("pedido",0));
        nombre.setText(pedido.getNombre());
        direccion.setText(pedido.getDireccion());

        ProductoAdaptador adaptador = new ProductoAdaptador(this, pedido.getProductos());
        lista.setAdapter(adaptador);
    }

}
