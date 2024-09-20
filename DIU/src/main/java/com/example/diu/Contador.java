package com.example.diu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Insets;



public class Contador extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try{
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(0, 0,0 , 100));
            raiz.setSpacing(30);

            Label contadior;
            contadior = new Label("1");
            contadior.setFont(Font.font(20));

            HBox botones = new HBox();
            botones.setPadding(new Insets(30, 0, 0, 0));
            botones.setSpacing(3);

           javafx.scene.control.Button btmas;
           javafx.scene.control.Button btmenos;
           javafx.scene.control.Button btcero;

           btmas = new javafx.scene.control.Button();
           btmenos = new javafx.scene.control.Button();
           btcero = new javafx.scene.control.Button();

            btmas.setText("+");
            btmenos.setText("-");
            btcero.setText("0");

            botones.getChildren().addAll(btmenos, btcero, btmas);
            raiz.getChildren().addAll(botones, contadior);

            Scene escena = new Scene(raiz, 300, 200);
            stage.setTitle("Contador");
            stage.setScene(escena);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
