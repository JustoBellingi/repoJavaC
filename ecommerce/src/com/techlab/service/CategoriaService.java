package com.techlab.service;

import java.util.ArrayList;

import com.techlab.model.Categoria;

public class CategoriaService {

    private ArrayList<Categoria> categorias;

    public CategoriaService() {
        categorias = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void listarCategorias() {

        if(categorias.isEmpty()){

            System.out.println("No hay categorias registradas.");
            return;

        }

        for(Categoria categoria : categorias){

            System.out.println(categoria);

        }

    }

}
