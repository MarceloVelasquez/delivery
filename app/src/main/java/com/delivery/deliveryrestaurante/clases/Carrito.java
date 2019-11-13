package com.delivery.deliveryrestaurante.clases;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private static ArrayList<Producto> productos = new ArrayList<>();

    public static ArrayList<Producto> getProductos() {
        return productos;
    }
   public static void agregar( Producto pro){
       productos.add(pro);
   }
   public static void eliminar(int pos){
       productos.remove(pos);
   }
   public static void vaciar(){
        productos.clear();
   }
}
