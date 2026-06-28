package com.techlab.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.ecommerce.excepcion.StockInsuficienteException;
import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public Pedido crearPedido(Pedido pedido) throws StockInsuficienteException {

        for (LineaPedido linea : pedido.getLineas()) {

            Producto producto = productoRepository.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - linea.getCantidad());
            productoRepository.save(producto);

            linea.setProducto(producto);
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}