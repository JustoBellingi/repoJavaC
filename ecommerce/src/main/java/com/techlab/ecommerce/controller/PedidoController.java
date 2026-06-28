package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.findAll();
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return service.save(pedido);
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable int id) {
        return service.findById(id);
    }
}