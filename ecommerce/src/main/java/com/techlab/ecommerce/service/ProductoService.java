package com.techlab.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Agregar producto
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Listar productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Buscar por ID
    public Producto buscarPorId(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    // Actualizar 
    public Producto actualizarProducto(Integer id, Producto datosProducto) {

        Producto producto = buscarPorId(id);

        if (producto != null) {
            producto.setNombre(datosProducto.getNombre());
            producto.setDescripcion(datosProducto.getDescripcion());
            producto.setPrecio(datosProducto.getPrecio());
            producto.setCategoria(datosProducto.getCategoria());
            producto.setImagen(datosProducto.getImagen());
            producto.setStock(datosProducto.getStock());

            return productoRepository.save(producto);
        }

        return null;
    }

    // Eliminar 
    public boolean eliminarProducto(Integer id) {

        Producto producto = buscarPorId(id);

        if (producto != null) {
            productoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}