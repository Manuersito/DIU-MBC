package com.example.agenda;

import com.example.agenda.Modelo.AgendaModelo;
import com.example.agenda.Modelo.ExcepcionPerson;
import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Modelo.repository.impl.PersonRepositoryImpl;
import com.example.agenda.controller.BirthdayStatisticsController;
import com.example.agenda.controller.PersonEditDialogController;
import com.example.agenda.controller.PersonOverviewController;
import com.example.agenda.util.PersonUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    // Hacer agendaModelo un campo de instancia
    private AgendaModelo agendaModelo;

    public MainApp() {
        // Initialize the agendaModelo and set the person repository
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        agendaModelo = new AgendaModelo();
        agendaModelo.setPersonRepository(personRepository);

        try {
            personData.addAll(agendaModelo.mostrar());
        } catch (ExcepcionPerson e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AGENDA");

        // Set the application icon
        this.primaryStage.getIcons().add(new Image("file:resources/imagenes/agenda.png"));

        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPersonOverview() {
        try {
            // Load person overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            controller.setModelo(agendaModelo);  // Inyectar agendaModelo en el controlador

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
