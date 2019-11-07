package com.delivery.deliveryrestaurante.clases;

import java.text.DecimalFormat;

public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private String id;
    private String imagen;



    public Producto(String imagen, String nombre, String categoria, double precio, String id) {
        this.setImagen(imagen);
        this.setNombre(nombre);
        this.setCategoria(categoria);
        this.setPrecio(precio);
        this.setId(id);
    }

    public Producto() {

    }

    public double getPrecio() {
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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
