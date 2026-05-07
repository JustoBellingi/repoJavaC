package com.techlab.service;

import com.techlab.pedidos.Pedido;
import java.util.ArrayList;

public class PedidoService {

    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido p) {
        pedidos.add(p);
    }

    public void listarPedidos() {
        for (Pedido p : pedidos) {
            p.mostrar();
            System.out.println("------");
        }
    }
}