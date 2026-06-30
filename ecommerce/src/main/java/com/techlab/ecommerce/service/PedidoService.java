package com.techlab.ecommerce.service;

import com.techlab.ecommerce.excepcion.StockInsuficienteException;
import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepo;
    private final ProductoRepository productoRepo;

    public PedidoService(PedidoRepository pedidoRepo, ProductoRepository productoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.productoRepo = productoRepo;
    }

    public List<Pedido> findAll() {
        return pedidoRepo.findAll();
    }

    public Pedido findById(int id) {
        return pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public Pedido save(Pedido pedido) throws StockInsuficienteException {

        for (LineaPedido linea : pedido.getLineas()) {

            Producto producto = productoRepo.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para: " + producto.getNombre()
                );
            }

            producto.setStock(producto.getStock() - linea.getCantidad());
            productoRepo.save(producto);

            linea.setProducto(producto);
        }

        pedido.setEstado("Confirmado");

        return pedidoRepo.save(pedido);
    }
}