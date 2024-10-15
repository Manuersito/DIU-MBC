package com.example.conversor;

import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            ConexionJDBC conexion = new ConexionJDBC();
            conexion.conectarBD();
            MonedaRepositoryImpl monedaRepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("prueba",1.2F);
            monedaRepositoryImpl.addMoneda(monedaPrueba);
            

        }catch (Exception e){
            e.printStackTrace();
        }

        launch();


    }


}