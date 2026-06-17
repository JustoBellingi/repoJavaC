package com.techlab.service;

import java.util.ArrayList;

import com.techlab.model.Pedido;

public class PedidoService {

    private ArrayList<Pedido> pedidos;

    public PedidoService() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void listarPedidos() {

        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }

        for (Pedido pedido : pedidos) {
            pedido.mostrar();
        }

    }

    public Pedido buscarPorId(int id) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId() == id) {
                return pedido;
            }

        }

        return null;
    }

    public void cambiarEstado(int id, String estado) {

        Pedido pedido = buscarPorId(id);

        if (pedido != null) {

            pedido.setEstado(estado);
            System.out.println("Estado actualizado correctamente.");

        } else {

            System.out.println("Pedido no encontrado.");

        }

    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

}