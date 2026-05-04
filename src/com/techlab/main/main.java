package src.com.techlab.main;

import src.com.techlab.productos.*;
import src.com.techlab.pedidos.*;
import src.com.techlab.service.*;
import src.com.techlab.excepciones.*;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductoService ps = new ProductoService();
        PedidoService pedS = new PedidoService();

        int opcion;

        do {
            System.out.println("\n=== FERRETERÍA DON TITO =====");
            System.out.println("1. Agregar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Crear pedido");
            System.out.println("6. Listar pedidos");
            System.out.println("0. Salir");

            try {
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {

                    case 1:
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();

                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(sc.nextLine());

                        System.out.print("Stock: ");
                        int stock = Integer.parseInt(sc.nextLine());

                        ps.agregarProducto(new Producto(nombre, precio, stock));
                        break;

                    case 2:
                        ps.listarProductos();
                        break;

                    case 3:
                        System.out.print("ID: ");
                        int idBuscar = Integer.parseInt(sc.nextLine());

                        Producto p = ps.buscarPorId(idBuscar);

                        if (p != null) {
                            System.out.println(p);
                        } else {
                            System.out.println("No encontrado");
                        }
                        break;

                    case 4:
                        System.out.print("ID a eliminar: ");
                        int idEliminar = Integer.parseInt(sc.nextLine());
                        ps.eliminarProducto(idEliminar);
                        break;

                    case 5:
                        Pedido pedido = new Pedido();

                        while (true) {
                            ps.listarProductos();

                            System.out.print("ID producto: ");
                            int idProd = Integer.parseInt(sc.nextLine());

                            if (idProd == 0) break;

                            Producto prod = ps.buscarPorId(idProd);

                            if (prod == null) {
                                System.out.println("No existe");
                                continue;
                            }

                            System.out.print("Cantidad: ");
                            int cant = Integer.parseInt(sc.nextLine());

                            if (cant > prod.getStock()) {
                                throw new StockInsuficienteException("No hay suficiente stock");
                            }

                            prod.setStock(prod.getStock() - cant);
                            pedido.agregarLinea(new LineaPedido(prod, cant));
                        }

                        pedS.agregarPedido(pedido);
                        System.out.println("Pedido creado!");
                        break;

                    case 6:
                        pedS.listarPedidos();
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: ingresaste un valor inválido.");
                opcion = -1;
            } catch (StockInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
                opcion = -1;
            }

        } while (opcion != 0);
    }
}