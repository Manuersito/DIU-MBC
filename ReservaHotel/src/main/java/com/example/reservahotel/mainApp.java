package com.example.reservahotel;

import com.example.reservahotel.Controller.ClientOverview;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class mainApp extends Application {

    Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Reserva Hotel");

        initRootLayout();
        showClientOverview();
    }


    public void initRootLayout() {
        try {
            // Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientOverview() {
        try {
            // Load person overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.class.getResource("ClientOverview.fxml"));
            Pane personOverview = loader.load();

            // Set person overview into the center of root layout
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app
            ClientOverview controller = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch();
    }
}