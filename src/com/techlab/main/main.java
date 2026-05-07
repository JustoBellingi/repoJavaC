package com.techlab.main;

import com.techlab.productos.*;
import com.techlab.pedidos.*;
import com.techlab.service.*;
import com.techlab.excepciones.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductoService ps = new ProductoService();
        PedidoService pedS = new PedidoService();

        int opcion;

        do {
            System.out.println("\n1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Crear pedido");
            System.out.println("5. Listar pedidos");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            try {
                switch (opcion) {

                    case 1:
                        sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();

                        System.out.print("Stock: ");
                        int stock = sc.nextInt();

                        ps.agregarProducto(new Producto(nombre, precio, stock));
                        break;

                    case 2:
                        ps.listarProductos();
                        break;

                    case 3:
                        System.out.print("ID a eliminar: ");
                        int id = sc.nextInt();
                        ps.eliminarProducto(id);
                        break;

                    case 4:
                        Pedido pedido = new Pedido();

                        System.out.print("Cantidad de productos: ");
                        int cant = sc.nextInt();

                        for (int i = 0; i < cant; i++) {

                            System.out.print("ID producto: ");
                            int idProd = sc.nextInt();

                            Producto p = ps.buscarPorId(idProd);

                            if (p == null) {
                                System.out.println("Producto no encontrado");
                                i--; 
                                continue;
                            }

                            System.out.print("Cantidad: ");
                            int cantidad = sc.nextInt();

                            if (p.getStock() < cantidad) {
                                throw new StockInsuficienteException("No hay stock suficiente");
                            }

                            p.setStock(p.getStock() - cantidad);

                            pedido.agregarLinea(new LineaPedido(p, cantidad));
                        }

                            pedS.agregarPedido(pedido);
                            System.out.println("Pedido creado correctamente");
                        break;

                    case 5:
                        pedS.listarPedidos();
                        break;
                }

            } catch (StockInsuficienteException e) {
                System.out.println(e.getMessage());
            }

        } while (opcion != 0);
    }
}