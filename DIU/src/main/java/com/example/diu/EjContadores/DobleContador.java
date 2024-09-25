package com.example.diu.EjContadores;

import javafx.application.Application;
import javafx.stage.Stage;

public class DobleContador extends Application {
    Contador contador1 = new Contador();
    Contador contador2 = new Contador();

    @Override
    public void start(Stage stage) throws Exception {
    Stage stage1 = new Stage();
    contador1.start(stage1);
    Stage stage2 = new Stage();
    contador2.start(stage2);
    }
}
