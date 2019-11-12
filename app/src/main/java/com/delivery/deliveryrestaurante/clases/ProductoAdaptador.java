package com.delivery.deliveryrestaurante.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.delivery.deliveryrestaurante.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductoAdaptador extends BaseAdapter {

    private Context contexto;
    private ArrayList<Producto> lista;

    public ProductoAdaptador(Context contexto, ArrayList<Producto> lista) {
        this.contexto = contexto;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Producto producto = (Producto) getItem(position);

        convertView = LayoutInflater.from(contexto).inflate(R.layout.item_producto, null);
        TextView nombre = convertView.findViewById(R.id.item_nombre);
        TextView categoria = convertView.findViewById(R.id.item_categoria);
        TextView precio = convertView.findViewById(R.id.item_precio);
        ImageView imagen = convertView.findViewById(R.id.item_imagen);

        Picasso.get().load(producto.getImagen()).into(imagen);
        nombre.setText(producto.getNombre());
        categoria.setText(producto.getCategoria());
        precio.setText(producto.getPrecioEnSoles());

        return convertView;
    }
}
