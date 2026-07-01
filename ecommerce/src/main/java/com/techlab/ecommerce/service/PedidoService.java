package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.LineaPedido;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.model.Producto;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public Pedido crearPedido(Pedido pedido) {

        Double total = 0.0;

        for (LineaPedido linea : pedido.getLineas()) {

            Producto producto = productoRepository
                    .findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            linea.setProducto(producto);
            linea.setPedido(pedido);

            Double precioLinea = producto.getPrecio() * linea.getCantidad();
            linea.setPrecio(precioLinea);
            total += precioLinea;
        }
        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }
}