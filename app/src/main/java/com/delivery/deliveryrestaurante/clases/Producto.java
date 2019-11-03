package com.delivery.deliveryrestaurante.clases;

import java.text.DecimalFormat;

public class Producto {
    private String nombre;
    private String categoria;
    private float precio;
    private String id;

    public Producto(String nombre, String categoria, float precio, String id) {
        this.setNombre(nombre);
        this.setCategoria(categoria);
        this.setPrecio(precio);
        this.setId(id);
    }

    public Producto() {

    }

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecioEnSoles() {
        return "S/. " + new DecimalFormat("#.00").format(getPrecio());
    }

    public String getCategoria() {
        return categoria;
    }

    public String getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setId(String id) {
        this.id = id;
    }
}
