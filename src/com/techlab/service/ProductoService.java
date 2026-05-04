package src.com.techlab.service;

import src.com.techlab.productos.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos.");
            return;
        }
        productos.forEach(System.out::println);
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

    public List<Producto> getProductos() {
        return productos;
    }
}