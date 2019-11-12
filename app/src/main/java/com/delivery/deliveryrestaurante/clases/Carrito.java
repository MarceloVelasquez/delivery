package com.delivery.deliveryrestaurante.clases;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private static ArrayList productos = new ArrayList<Producto>();
    public static ArrayList getProductos() {
        return productos;
    }
   public static void agregar( Producto pro){
       productos.add(pro);
   }
   public static void eliminar(int pos){
       productos.remove(pos);
   }
}
