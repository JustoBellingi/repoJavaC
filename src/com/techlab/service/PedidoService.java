package src.com.techlab.service;

import src.com.techlab.pedidos.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido p) {
        pedidos.add(p);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos.");
            return;
        }

        for (Pedido p : pedidos) {
            p.mostrarDetalle();
            System.out.println("-------------------");
        }
    }
}