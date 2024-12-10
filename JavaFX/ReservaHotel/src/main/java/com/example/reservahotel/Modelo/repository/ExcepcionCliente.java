package com.example.reservahotel.Modelo.repository;

public class ExcepcionCliente extends RuntimeException {
    private String mensaje;

    public ExcepcionCliente() {
    }

    public ExcepcionCliente(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
