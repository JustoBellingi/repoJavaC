package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Producto findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto save(Producto producto) {
        return repo.save(producto);
    }

    public Producto update(int id, Producto nuevo) {
        Producto p = findById(id);

        p.setNombre(nuevo.getNombre());
        p.setDescripcion(nuevo.getDescripcion());
        p.setPrecio(nuevo.getPrecio());
        p.setStock(nuevo.getStock());
        p.setImagen(nuevo.getImagen());
        p.setCategoria(nuevo.getCategoria());

        return repo.save(p);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}