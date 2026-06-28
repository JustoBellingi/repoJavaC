package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // GET todas
    @GetMapping
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    // GET por ID
    @GetMapping("/{id}")
    public Categoria getById(@PathVariable Integer id) {
        return categoriaRepository.findById(id)
                .orElse(null);
    }

    // POST crear
    @PostMapping
    public Categoria create(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // PUT actualizar
    @PutMapping("/{id}")
    public Categoria update(@PathVariable Integer id, @RequestBody Categoria nueva) {

        Optional<Categoria> optional = categoriaRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Categoria categoria = optional.get();
        categoria.setNombre(nueva.getNombre());

        return categoriaRepository.save(categoria);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoriaRepository.deleteById(id);
    }
}