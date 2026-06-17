package com.techlab.ecommerce.service;

import java.util.ArrayList;

import com.techlab.ecommerce.model.Producto;

public class ProductoService {

    private ArrayList<Producto> productos;

    public ProductoService() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void listarProductos() {

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public Producto buscarPorId(int id) {

        for (Producto producto : productos) {

            if (producto.getId() == id) {
                return producto;
            }

        }

        return null;
    }

    public Producto buscarPorNombre(String nombre) {

        for (Producto producto : productos) {

            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }

        }

        return null;
    }

    public void actualizarProducto(int id, double precio, int stock) {

    Producto producto = buscarPorId(id);

    if (producto != null) {

        if (precio < 0 || stock < 0) {
            System.out.println("Precio o stock inválido.");
            return;
        }

        producto.setPrecio(precio);
        producto.setStock(stock);

        System.out.println("Producto actualizado.");

    } else {

        System.out.println("Producto no encontrado.");

    }

}

    public void eliminarProducto(int id) {

        Producto producto = buscarPorId(id);

        if (producto != null) {

            productos.remove(producto);
            System.out.println("Producto eliminado");

        } else {

            System.out.println("Producto no encontrado.");

        }

    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

}