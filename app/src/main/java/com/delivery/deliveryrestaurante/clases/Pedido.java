package com.delivery.deliveryrestaurante.clases;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String nombre;
    private String telefono;
    private String direccion;
    private ArrayList<Producto> productos;

    public Pedido(String nombre, String telefono, String direccion, ArrayList<Producto> productos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.productos = productos;
    }

    public Pedido() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String numero) {
        this.telefono = numero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
}
