package com.example.manuelbernaldezcarrascojavafx;

import Modelo.ExcepcionMoneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.manuelbernaldezcarrascojavafx.Controller.ConversorController;
import com.example.manuelbernaldezcarrascojavafx.Modelo.ConversoModelo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class Conversor extends Application {
    private static ObservableList<Moneda> monedas = FXCollections.observableArrayList();
   private ConversoModelo conversor;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Conversor.class.getResource("ConversorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        ConversorController controller = fxmlLoader.getController();
        controller.setConversorMain(this);
        if (conversor != null) {
            controller.setCoversoModelo(conversor);
        } else {
            throw new RuntimeException("El modelo ConversoModelo no se inicializó correctamente.");
        }



    }
    public Conversor() {
        try {
            conversor = new ConversoModelo();
            MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
            conversor.setMonedaRepository(monedaRepository);
            monedas.addAll(conversor.setMonedas());

        } catch (ExcepcionMoneda e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("BBDD ERROR");
            alert.setHeaderText("No se ha podido establecer Conexión con la base de datos");
            alert.setContentText("Por favor, inicie la base de datos");
            alert.showAndWait();
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Moneda> getMonedas() {
        return monedas;
    }
    public static void main(String[] args) {
        launch();
    }
}