package com.example.reservahotel.Modelo;

import java.time.LocalDate;
import  com.example.reservahotel.Modelo.repository.impl.regimen;
import  com.example.reservahotel.Modelo.repository.impl.tipo_hab;

public class ReservaVO {
    private int id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int numHabitaciones;
    private tipo_hab tipoHabitacion;
    private regimen regimen;
    private boolean fumador;
    private String dniCliente;

    public ReservaVO(int id, LocalDate fechaEntrada, LocalDate fechaSalida, int numHabitaciones, tipo_hab tipoHabitacion, com.example.reservahotel.Modelo.repository.impl.regimen regimen, boolean fumador, String dniCliente) {
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

    public tipo_hab getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(tipo_hab tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public com.example.reservahotel.Modelo.repository.impl.regimen getRegimen() {
        return regimen;
    }

    public void setRegimen(com.example.reservahotel.Modelo.repository.impl.regimen regimen) {
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
                ", tipoHabitacion=" + tipoHabitacion +
                ", regimen=" + regimen +
                ", fumador=" + fumador +
                ", dniCliente='" + dniCliente + '\'' +
                '}';
    }
}
