package com.example.conversor.controller;

import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.swing.*;

public class HelloController {

    


    @FXML
    private JTextField dolares;

    @FXML
    private JTextField euros;

    @FXML
    protected void convertir() {
        dolares.setText(euros.getText());
    }


}