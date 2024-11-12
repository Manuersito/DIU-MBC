package com.example.reservahotel.Modelo.repository;

public class ExcepcionReserva extends RuntimeException {
    private String mensaje;

    public ExcepcionReserva() {
    }

    public ExcepcionReserva(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
