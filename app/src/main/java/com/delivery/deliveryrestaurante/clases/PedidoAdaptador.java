package com.delivery.deliveryrestaurante.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.delivery.deliveryrestaurante.R;

import java.util.ArrayList;

public class PedidoAdaptador extends BaseAdapter {

    private Context contexto;
    private ArrayList<Pedido> lista;

    public PedidoAdaptador(Context contexto, ArrayList<Pedido> lista) {
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
        Pedido pedido = (Pedido) getItem(position);

        convertView = LayoutInflater.from(contexto).inflate(R.layout.item_pedido, null);
        TextView cliente = convertView.findViewById(R.id.item_cliente);
        TextView direccion = convertView.findViewById(R.id.item_direccion);
        TextView cantidad = convertView.findViewById(R.id.item_cantidad);
        TextView total = convertView.findViewById(R.id.item_total);

        cliente.setText(pedido.getNombre());
        direccion.setText(pedido.getDireccion());
        cantidad.setText(pedido.getCantidad()+"");
        total.setText(pedido.getTotalEnSoles());

        return convertView;
    }
    public void clear(){
        lista.clear();
    }
}
