package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // GET todos los productos
    @GetMapping
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    // GET por ID
    @GetMapping("/{id}")
    public Producto getById(@PathVariable Integer id) {
        return productoRepository.findById(id)
                .orElse(null);
    }

    // POST crear producto
    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // PUT actualizar producto
    @PutMapping("/{id}")
    public Producto update(@PathVariable Integer id, @RequestBody Producto nuevo) {

        Optional<Producto> optional = productoRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Producto producto = optional.get();

        producto.setNombre(nuevo.getNombre());
        producto.setDescripcion(nuevo.getDescripcion());
        producto.setPrecio(nuevo.getPrecio());
        producto.setStock(nuevo.getStock());
        producto.setImagen(nuevo.getImagen());
        producto.setCategoria(nuevo.getCategoria());

        return productoRepository.save(producto);
    }

    // DELETE producto
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        productoRepository.deleteById(id);
    }
}