package com.techlab.ecommerce.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate fecha;
    private String estado;
    private ArrayList<LineaPedido> lineas; 

    public Pedido() {
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