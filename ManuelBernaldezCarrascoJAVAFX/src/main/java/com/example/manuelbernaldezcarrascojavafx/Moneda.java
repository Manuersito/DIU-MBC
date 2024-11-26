package com.example.manuelbernaldezcarrascojavafx;

import javafx.beans.property.*;

public class Moneda {

    private StringProperty nombre;
    private FloatProperty multiplicador;
    private IntegerProperty codigo;

    public Moneda() {this(null,null,null);}

    public Moneda(String nombre, Float multiplicador, Integer codigo) {
        this.nombre =new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
        this.codigo = new SimpleIntegerProperty(codigo);
    }


    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public float getMultiplicador() {
        return multiplicador.get();
    }

    public FloatProperty multiplicadorProperty() {
        return multiplicador;
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", multiplicador=" + multiplicador +
                '}';
    }
}
