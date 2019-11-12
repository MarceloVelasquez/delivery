package com.delivery.deliveryrestaurante.clases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String nombre;
    private String telefono;
    private String direccion;
    private double total;
    private int cantidad;
    private double efectivo;
    private ArrayList<Producto> productos;



    public Pedido(String nombre, String telefono, String direccion, double total,int cantidad, double efectivo,ArrayList<Producto> productos) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.total = total;
        this.productos = productos;
        this.cantidad = cantidad;
        this.efectivo = efectivo;
        for (Producto producto:productos) {
            this.total += producto.getPrecio();
            this.cantidad+=1;
        }
    }

    public Pedido() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getEfectivo() {
        return efectivo;
    }
    public String getEfectivoEnSoles() {
        return "S/. " + new DecimalFormat("#.00").format(getEfectivo());
    }
    public void setEfectivo(double efectivo) {
        this.efectivo = efectivo;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotalEnSoles() {
        return "S/. " + new DecimalFormat("#.00").format(getTotal());
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
