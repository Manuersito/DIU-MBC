package com.example.reservahotel.Modelo;

import java.time.LocalDate;

public class ReservaVO {
    private int id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int numHabitaciones;
    private String tipoHabitacion;
    private String regimen;
    private boolean fumador;
    private String dniCliente;

    public ReservaVO(int id, LocalDate fechaEntrada, LocalDate fechaSalida, int numHabitaciones,
                     String tipoHabitacion, String regimen, boolean fumador, String dniCliente) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.regimen = regimen;
        this.fumador = fumador;
        this.dniCliente = dniCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    @Override
    public String toString() {
        return "ReservaVO{" +
                "id=" + id +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", numHabitaciones=" + numHabitaciones +
                ", tipoHabitacion='" + tipoHabitacion + '\'' +
                ", regimen='" + regimen + '\'' +
                ", fumador=" + fumador +
                ", dniCliente='" + dniCliente + '\'' +
                '}';
    }
}
