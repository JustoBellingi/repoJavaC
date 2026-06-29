package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.CategoriaRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;
    private final CategoriaRepository categoriaRepo;

    @Autowired
    public ProductoService(ProductoRepository repo,
                           CategoriaRepository categoriaRepo) {
        this.repo = repo;
        this.categoriaRepo = categoriaRepo;
    }

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Producto findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto save(Producto producto) {

        Integer categoriaId = producto.getCategoria().getId();

        Categoria categoria = categoriaRepo.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        producto.setCategoria(categoria);

        return repo.save(producto);
    }

    public Producto update(int id, Producto nuevo) {

        Producto p = findById(id);

        p.setNombre(nuevo.getNombre());
        p.setDescripcion(nuevo.getDescripcion());
        p.setPrecio(nuevo.getPrecio());
        p.setStock(nuevo.getStock());
        p.setImagen(nuevo.getImagen());

        if (nuevo.getCategoria() != null) {
            Integer categoriaId = nuevo.getCategoria().getId();

            Categoria categoria = categoriaRepo.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            p.setCategoria(categoria);
        }

        return repo.save(p);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }
}