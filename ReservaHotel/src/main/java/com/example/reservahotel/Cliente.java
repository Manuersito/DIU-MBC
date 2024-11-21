package com.example.reservahotel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

    private final StringProperty dni;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty city;
    private final StringProperty state;

    /**
     * Default constructor.
     */
    public Cliente() {
        this(null, null, null, null, null, null);
    }

    /**
     * Constructor with initial data.
     *
     * @param dni
     * @param firstName
     * @param lastName
     * @param street
     * @param city
     * @param state
     */
    public Cliente(String dni, String firstName, String lastName, String street, String city, String state) {
        this.dni = new SimpleStringProperty(dni);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
    }

    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public StringProperty stateProperty() {
        return state;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni=" + dni +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", street=" + street +
                ", city=" + city +
                ", state=" + state +
                '}';
    }
}
