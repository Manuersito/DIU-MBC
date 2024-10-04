package com.example.contadorsb;

import com.example.contadorsb.Controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 420);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 620, 420);
        stage2.setTitle("Hello!");
        stage2.setScene(scene2);
        stage2.show();

        HelloController controller = fxmlLoader.getController();
        HelloController controller2 = fxmlLoader2.getController();

        controller.numProperty().bindBidirectional(controller2.numProperty());



    }

    public static void main(String[] args) {
        launch();
    }
}