package com.techlab.ecommerce.excepcion;

public class StockInsuficienteException extends Exception {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}