package com.techlab.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private static int contador = 1;

    private int id;
    private LocalDate fecha;
    private String estado;
    private ArrayList<LineaPedido> lineas; 

    public Pedido() {
        this.id = contador++;
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido linea) {
        lineas.add(linea);
    }

    public double calcularTotal() {

        double total = 0;

        for (LineaPedido linea : lineas) {
            total += linea.calcularSubtotal();
        }

        return total;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<LineaPedido> getLineas() {
        return lineas;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void mostrar() {
        System.out.println("Pedido N° " + id);
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
        
        for (LineaPedido linea : lineas) {

            System.out.println(
                    linea.getProducto().getNombre()
                    + " x "
                    + linea.getCantidad()
                    + " = $"
                    + linea.calcularSubtotal()
            );

        }

        System.out.println("TOTAL: $" + calcularTotal());

    }

}