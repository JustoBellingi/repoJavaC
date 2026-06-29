package com.techlab.ecommerce.config;
import com.techlab.ecommerce.model.Categoria;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.CategoriaRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public DataInitializer(CategoriaRepository categoriaRepository,
                           ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {

        if (categoriaRepository.count() == 0) {

            Categoria frutas = new Categoria("Frutas");
            Categoria lacteos = new Categoria("Lácteos");
            Categoria bebidas = new Categoria("Bebidas");

            categoriaRepository.save(frutas);
            categoriaRepository.save(lacteos);
            categoriaRepository.save(bebidas);

            productoRepository.save(new Producto(
                    "Manzana",
                    "Manzana roja fresca",
                    500,
                    frutas,
                    "manzana.jpg",
                    10
            ));

            productoRepository.save(new Producto(
                    "Leche",
                    "Leche entera 1L",
                    900,
                    lacteos,
                    "leche.jpg",
                    20
            ));

            productoRepository.save(new Producto(
                    "Coca Cola",
                    "Gaseosa 2L",
                    1500,
                    bebidas,
                    "coca.jpg",
                    15
            ));

            System.out.println("SEED DE DATOS CARGADO");
        }
    }
}