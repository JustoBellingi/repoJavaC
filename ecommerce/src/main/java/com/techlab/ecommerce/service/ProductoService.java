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
        return repo.findById(id).orElse(null);
    }

    public Producto save(Producto producto) {
        return repo.save(producto);
    }

    public Producto update(int id, Producto producto) {
        producto.setId(id);
        return repo.save(producto);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}