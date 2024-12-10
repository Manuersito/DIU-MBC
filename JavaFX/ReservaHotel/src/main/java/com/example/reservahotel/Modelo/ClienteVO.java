package com.example.reservahotel.Modelo;

import java.time.LocalDate;

public class ClienteVO {
    String DNI;
    String firstName;
    String lastName;
    String street;
    String city;
    String state;




    public ClienteVO(String firstName, String lastName, String street, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public ClienteVO(String DNI, String firstName, String lastName, String street, String city, String state) {
        this.DNI = DNI;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        DNI = DNI;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "DNI=" + DNI +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state"+ state +
                '}';
    }
}
