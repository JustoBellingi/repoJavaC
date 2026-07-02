package com.techlab.ecommerce.controller;

import com.techlab.ecommerce.model.Pedido;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoService pedidoService;

    public PedidoController(PedidoRepository pedidoRepository,
                            PedidoService pedidoService) {

        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listar(){

        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Integer id){

        return pedidoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido){

        return pedidoService.crearPedido(pedido);
    }

}