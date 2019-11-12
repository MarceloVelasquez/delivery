package com.delivery.deliveryrestaurante.clases;

import java.util.ArrayList;

public class Pedidos {
    private static ArrayList<Pedido> pedidos = new ArrayList<> ();

    public static ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    public static void agregar(Pedido ped){
        pedidos.add(ped);
    }
}
