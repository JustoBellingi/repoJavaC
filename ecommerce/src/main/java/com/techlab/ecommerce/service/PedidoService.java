package com.techlab.ecommerce.service;

import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public List<Pedido> findAll() {
        return repo.findAll();
    }

    public Pedido save(Pedido pedido) {
        pedido.setEstado("Pendiente");
        return repo.save(pedido);
    }

    public Pedido findById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }
}