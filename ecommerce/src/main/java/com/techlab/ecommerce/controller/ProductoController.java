package com.techlab.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Listar productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // Buscar producto 
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Integer id) {

        Producto producto = productoService.buscarPorId(id);

        if (producto != null) {
            return ResponseEntity.ok(producto);
        }

        return ResponseEntity.notFound().build();
    }

    // Agregar 
    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }

    // Actualizar 
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Integer id,
            @RequestBody Producto producto) {

        Producto actualizado = productoService.actualizarProducto(id, producto);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        }

        return ResponseEntity.notFound().build();
    }

    // Eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id) {

        boolean eliminado = productoService.eliminarProducto(id);

        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente.");
        }

        return ResponseEntity.notFound().build();
    }
}