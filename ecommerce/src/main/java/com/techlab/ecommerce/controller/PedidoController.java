package com.techlab.ecommerce.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techlab.ecommerce.excepcion.StockInsuficienteException;
import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pedido pedido) {
        try {
            return ResponseEntity.ok(pedidoService.crearPedido(pedido));
        } catch (StockInsuficienteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Integer id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        return (pedido != null) ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }
}