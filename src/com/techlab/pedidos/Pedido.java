package com.techlab.pedidos;

import java.util.ArrayList;

public class Pedido {

    private static int contador = 1;

    private int id;
    private ArrayList<LineaPedido> lineas;

    public Pedido() {
        this.id = contador++;
        this.lineas = new ArrayList<>();
    }

    public void agregarLinea(LineaPedido lp) {
        lineas.add(lp);
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido lp : lineas) {
            total += lp.calcularSubtotal();
        }
        return total;
    }

    public void mostrar() {
        System.out.println("Pedido #" + id);
        for (LineaPedido lp : lineas) {
            System.out.println("- " + lp.getProducto().getNombre() + " x" + lp.getCantidad());
        }
        System.out.println("Total: $" + calcularTotal());
    }
}