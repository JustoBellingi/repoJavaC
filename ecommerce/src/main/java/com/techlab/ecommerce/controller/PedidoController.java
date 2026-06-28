package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.excepcion.StockInsuficienteException;
import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    // GET todos los pedidos
    @GetMapping
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    // POST crear pedido
    @PostMapping
    public Pedido create(@RequestBody Pedido pedido) throws StockInsuficienteException {

        // validar stock
        for (LineaPedido linea : pedido.getLineas()) {

            Producto producto = productoRepository.findById(
                    linea.getProducto().getId()
            ).orElse(null);

            if (producto == null) {
                throw new RuntimeException("Producto no encontrado");
            }

            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para: " + producto.getNombre()
                );
            }

            // descontar stock
            producto.setStock(producto.getStock() - linea.getCantidad());
            productoRepository.save(producto);

            // asegurar referencia real
            linea.setProducto(producto);
        }

        return pedidoRepository.save(pedido);
    }

    // GET por ID
    @GetMapping("/{id}")
    public Pedido getById(@PathVariable Integer id) {
        return pedidoRepository.findById(id)
                .orElse(null);
    }

    // DELETE pedido
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        pedidoRepository.deleteById(id);
    }
}