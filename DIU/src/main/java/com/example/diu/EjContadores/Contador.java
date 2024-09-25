package com.example.diu.EjContadores;

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

    private Button btmas,btmenos,btcero;
    private Label contadior;
    private int num=1;

   /* private void botonPulsado(ActionEvent e) {
        if (e.getSource() == btmas)
            contadior.setText(String.valueOf(++num));
        else if (e.getSource() == btmenos)
            contadior.setText(String.valueOf(--num));
        else if (e.getSource() == btcero)
            contadior.setText(String.valueOf(num = 0));
    }
*/

    private void botonPulsado(Button button){
        num = (button == btmas) ? ++num : (button == btmenos) ? --num : 0;
        contadior.setText(String.valueOf(num));
    }


    @Override
    public void start(Stage stage) throws Exception {
        try{
            VBox raiz = new VBox();
            raiz.setPadding(new Insets(0, 0,0 , 0));
            raiz.setSpacing(0);
            raiz.getStyleClass().add("raiz");


            contadior = new Label("1");
            contadior.setFont(Font.font(60));
            contadior.setPadding(new Insets(30,0,0,130));
            contadior.getStyleClass().add("contadior");

            HBox botones = new HBox();
            botones.setPadding(new Insets(30, 0, 0,50 ));
            botones.setSpacing(60);

           btmas = new Button();
           btmenos = new Button();
           btcero = new Button();

            btmas.setText("+");
            btmas.setOnAction(e->botonPulsado(btmas));
            btmenos.setText("-");
            btmenos.setOnAction(e->botonPulsado(btmenos));
            btcero.setText("0");
            btcero.getStyleClass().add("btcero");
            btcero.setOnAction(e->botonPulsado(btcero));

            botones.getChildren().addAll(btmenos, btcero, btmas);
            raiz.getChildren().addAll(botones, contadior);

            Scene escena = new Scene(raiz, 300, 200);
            escena.getStylesheets().add(getClass().getResource("/CSS/Contador.css").toExternalForm());
            stage.setTitle("Contador");
            stage.setScene(escena);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
