package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaRepository repo;

    public CategoriaController(CategoriaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Integer id) {
        return repo.findById(id).orElse(null);
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return repo.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Integer id, @RequestBody Categoria categoriaNueva) {

        Categoria categoria = repo.findById(id).orElse(null);

        if (categoria != null) {
            categoria.setNombre(categoriaNueva.getNombre());
            return repo.save(categoria);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Integer id) {

        repo.deleteById(id);

        return "Categoría eliminada";
    }
}