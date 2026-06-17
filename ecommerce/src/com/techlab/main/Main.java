package com.techlab.main;

import com.techlab.model.Categoria;
import com.techlab.model.LineaPedido;
import com.techlab.model.Pedido;
import com.techlab.model.Producto;
import com.techlab.service.ProductoService;
import com.techlab.service.PedidoService;
import com.techlab.service.CategoriaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductoService productoService = new ProductoService();
        PedidoService pedidoService = new PedidoService();
        CategoriaService categoriaService = new CategoriaService();

        int opcion;

        do {

            System.out.println("\n=================================== SISTEMA DE GESTIÓN - TECHLAB ==================================");
            System.out.println("1) Gestionar Productos");
            System.out.println("2) Gestionar Categorías");
            System.out.println("3) Ver Carrito de Compras");
            System.out.println("4) Realizar Pedido");
            System.out.println("5) Consultar Historial de Pedidos");
            System.out.println("6) Administración (usuarios y stock)");
            System.out.println("7) Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    char opcionProducto;

                    do {

                        System.out.println("\n====================== GESTIÓN DE PRODUCTOS ======================");
                        System.out.println("a) Agregar Producto");
                        System.out.println("b) Listar Productos");
                        System.out.println("c) Buscar Producto por ID");
                        System.out.println("d) Actualizar Producto");
                        System.out.println("e) Eliminar Producto");
                        System.out.println("f) Volver");
                        System.out.print("Seleccione una opción: ");

                        opcionProducto = sc.next().charAt(0);
                        sc.nextLine();

                        switch (opcionProducto) {

                            case 'a':

                                System.out.print("Nombre: ");
                                String nombre = sc.nextLine();

                                System.out.print("Descripción: ");
                                String descripcion = sc.nextLine();

                                System.out.print("Precio: ");
                                double precio = sc.nextDouble();
                                sc.nextLine();

                                System.out.print("Categoría: ");
                                String categoria = sc.nextLine();

                                System.out.print("Imagen: ");
                                String imagen = sc.nextLine();

                                System.out.print("Stock: ");
                                int stock = sc.nextInt();
                                sc.nextLine();

                                Producto producto = new Producto(
                                        nombre,
                                        descripcion,
                                        precio,
                                        categoria,
                                        imagen,
                                        stock
                                );

                                productoService.agregarProducto(producto);

                                System.out.println("Producto agregado correctamente.");

                                break;

                            case 'b':

                                productoService.listarProductos();

                                break;

                            case 'c':

                                System.out.print("Ingrese el ID: ");
                                int idBuscar = sc.nextInt();
                                sc.nextLine();

                                Producto encontrado = productoService.buscarPorId(idBuscar);

                                if (encontrado != null) {
                                    System.out.println(encontrado);
                                } else {
                                    System.out.println("Producto no encontrado.");
                                }

                                break;

                            case 'd':

                                System.out.print("ID del producto: ");
                                int idActualizar = sc.nextInt();

                                System.out.print("Nuevo precio: ");
                                double nuevoPrecio = sc.nextDouble();

                                System.out.print("Nuevo stock: ");
                                int nuevoStock = sc.nextInt();
                                sc.nextLine();

                                productoService.actualizarProducto(
                                        idActualizar,
                                        nuevoPrecio,
                                        nuevoStock
                                );

                                break;

                        
                            case 'e':

                                System.out.print("Ingrese el ID del producto a eliminar: ");
                                int idEliminar = sc.nextInt();
                                sc.nextLine();

                                System.out.print("¿Está seguro de eliminar el producto? (S/N): ");
                                char respuesta = sc.next().charAt(0);
                                sc.nextLine();

                                if (respuesta == 'S' || respuesta == 's') {

                                productoService.eliminarProducto(idEliminar);

                                } else {

                                    System.out.println("Eliminación cancelada.");

                                }

                                break;

                            case 'f':

                                break;

                            default:

                                System.out.println("Opción inválida.");

                        }

                    } while (opcionProducto != 'f');

                    break;

                case 2:

                    System.out.print("Nombre de la categoría: ");
                    String nombreCategoria = sc.nextLine();

                    Categoria categoria = new Categoria(nombreCategoria);

                    categoriaService.agregarCategoria(categoria);

                    System.out.println("Categoría agregada correctamente.");

                    System.out.println("\nCategorías registradas:");

                    categoriaService.listarCategorias();

                    break;

                case 3:

                    System.out.println("Carrito de compras en desarrollo.");

                    break;

                case 4:

                    Pedido pedido = new Pedido();

                    System.out.print("¿Cuántos productos desea comprar?: ");
                    int cantidadProductos = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < cantidadProductos; i++) {

                        System.out.print("Ingrese el ID del producto: ");
                        int idProducto = sc.nextInt();
                        sc.nextLine();

                        Producto producto = productoService.buscarPorId(idProducto);

                        if (producto == null) {

                            System.out.println("Producto no encontrado.");
                            i--;
                            continue;

                        }

                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();

                        if (cantidad > producto.getStock()) {

                            System.out.println("Stock insuficiente.");
                            i--;
                            continue;

                        }

                        producto.setStock(producto.getStock() - cantidad);

                        LineaPedido linea = new LineaPedido(producto, cantidad);

                        pedido.agregarLinea(linea);

                    }

                    pedidoService.agregarPedido(pedido);

                    System.out.println("Pedido creado correctamente.");
                    System.out.println("Total del pedido: $" + pedido.calcularTotal());

                    break;


                case 5:

                    pedidoService.listarPedidos();

                    break;

                case 6:

                    System.out.println("Administración en desarrollo.");

                    break;

                case 7:

                    System.out.println("Saliendo del sistema...");

                    break;

                default:

                    System.out.println("Opción inválida.");

            }

        } while (opcion != 7);

        sc.close();

    }

}