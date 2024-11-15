package com.example.reservahotel;

import com.example.reservahotel.Controller.ClientOverview;
import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Modelo.repository.impl.ClientRepositoryImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> personData = FXCollections.observableArrayList();
    private ModeloHotel modeloHotel;

    public MainApp() {
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        modeloHotel = new ModeloHotel();
        modeloHotel.setPersonRepository(clientRepository);


        try {
            personData.addAll(modeloHotel.mostrar());
        } catch (ExcepcionCliente e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Reserva Hotel");
        this.primaryStage.getIcons().add(new Image("file:resources/imagenes/hotel_icon.png"));

        initRootLayout();
        showClientOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showClientOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClientOverview.fxml"));
            rootLayout.setCenter(loader.load());

            ClientOverview controller = loader.getController();
            if (controller != null) {
                controller.setMainApp(this);
                controller.setModelo(modeloHotel);
            }

            System.out.println(personData.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cliente> getPersonData() {
        return personData;
    }

    public static void main(String[] args) {
        launch();
    }
}
