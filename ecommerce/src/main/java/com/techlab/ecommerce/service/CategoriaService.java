package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizar(Integer id, Categoria categoriaNueva) {

        Categoria categoria = buscarPorId(id);

        if (categoria != null) {
            categoria.setNombre(categoriaNueva.getNombre());
            return categoriaRepository.save(categoria);
        }

        return null;
    }

    public boolean eliminar(Integer id) {

        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }

        return false;
    }
}