package com.techlab.service;

import com.techlab.productos.Producto;
import java.util.ArrayList;

public class ProductoService {

    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void listarProductos() {
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
    }
}