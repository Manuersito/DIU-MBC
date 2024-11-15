package com.example.reservahotel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Reserva{

    private final IntegerProperty id;
    private final StringProperty fechaEntrada;
    private final StringProperty fechaSalida;
    private final IntegerProperty numHabitaciones;
    private final StringProperty tipoHabitacion;
    private final StringProperty regimen;
    private final BooleanProperty fumador;
    private final StringProperty dniCliente;

    /**
     * Default constructor.
     */
    public Reserva() {
        this(0, null, null, 0, null, null, false, null);
    }

    /**
     * Constructor with initial data.
     *
     * @param id
     * @param fechaEntrada
     * @param fechaSalida
     * @param numHabitaciones
     * @param tipoHabitacion
     * @param regimen
     * @param fumador
     * @param dniCliente
     */
    public Reserva(int id, String fechaEntrada, String fechaSalida, int numHabitaciones,
                   String tipoHabitacion, String regimen, boolean fumador, String dniCliente) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaEntrada = new SimpleStringProperty(fechaEntrada);
        this.fechaSalida = new SimpleStringProperty(fechaSalida);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.regimen = new SimpleStringProperty(regimen);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.dniCliente = new SimpleStringProperty(dniCliente);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFechaEntrada() {
        return fechaEntrada.get();
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada.set(fechaEntrada);
    }

    public StringProperty fechaEntradaProperty() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida.get();
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public StringProperty fechaSalidaProperty() {
        return fechaSalida;
    }

    public int getNumHabitaciones() {
        return numHabitaciones.get();
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones.set(numHabitaciones);
    }

    public IntegerProperty numHabitacionesProperty() {
        return numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion.get();
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion.set(tipoHabitacion);
    }

    public StringProperty tipoHabitacionProperty() {
        return tipoHabitacion;
    }

    public String getRegimen() {
        return regimen.get();
    }

    public void setRegimen(String regimen) {
        this.regimen.set(regimen);
    }

    public StringProperty regimenProperty() {
        return regimen;
    }

    public boolean isFumador() {
        return fumador.get();
    }

    public void setFumador(boolean fumador) {
        this.fumador.set(fumador);
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public String getDniCliente() {
        return dniCliente.get();
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente.set(dniCliente);
    }

    public StringProperty dniClienteProperty() {
        return dniCliente;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id.get() +
                ", fechaEntrada=" + fechaEntrada.get() +
                ", fechaSalida=" + fechaSalida.get() +
                ", numHabitaciones=" + numHabitaciones.get() +
                ", tipoHabitacion='" + tipoHabitacion.get() + '\'' +
                ", regimen='" + regimen.get() + '\'' +
                ", fumador=" + fumador.get() +
                ", dniCliente='" + dniCliente.get() + '\'' +
                '}';
    }
}
