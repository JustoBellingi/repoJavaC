package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return service.crear(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Integer id,
                                @RequestBody Categoria categoria) {
        return service.actualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable Integer id) {
        return service.eliminar(id);
    }
}