package com.delivery.deliveryrestaurante.clases;

public class Producto {
    private String imagen;
    private String nombre;
    private String categoria;
    private float precio;
    private String id;

    public Producto(String imagen, String nombre, String categoria, float precio, String id) {
        this.setImagen(imagen);
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

    public String getImagen() {
        return imagen;
    }


    public String getNombre() {
        return nombre;
    }



    public String getCategoria() {
        return categoria;
    }


    public String getId() {
        return id;
    }


    public void setImagen(String imagen) {
        this.imagen = imagen;
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
