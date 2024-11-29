package com.example.reservahotel;

import com.example.reservahotel.Controller.ClientOverview;
import com.example.reservahotel.Controller.EditClienteController;
import com.example.reservahotel.Controller.EditReservaController;
import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Modelo.repository.impl.ClientRepositoryImpl;
import com.example.reservahotel.Modelo.repository.impl.ReservaRepositoryImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Cliente> personData = FXCollections.observableArrayList();
    private ModeloHotel modeloHotel;

    public MainApp() {
        ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
        ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
        modeloHotel = new ModeloHotel();
        modeloHotel.setPersonRepository(clientRepository);
        modeloHotel.setReservaRepository(reservaRepository);

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

    public boolean showPersonEditDialogEdit(Cliente cliente) {
        try {
            // Cargar el archivo FXML y crear una nueva ventana
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("EditCliente.fxml"));
            Pane page = (Pane) loader.load();

            // Crear la ventana del diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.NONE);  // No bloquear la ventana principal
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Pasar el cliente al controlador del diálogo
            EditClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setClienteEdit(cliente);

            // Mostrar el diálogo y esperar hasta que se cierre
            dialogStage.showAndWait();

            return controller.isOkClicked();  // Devolver si el usuario hizo clic en OK
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPersonEditDialogNew(Cliente cliente) {
        try {
            // Cargar el archivo FXML y crear una nueva ventana
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("EditCliente.fxml"));
            Pane page = (Pane) loader.load();

            // Crear la ventana del diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.NONE);  // No bloquear la ventana principal
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Pasar el cliente al controlador del diálogo
            EditClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setClienteNew(cliente);

            // Mostrar el diálogo y esperar hasta que se cierre
            dialogStage.showAndWait();

            return controller.isOkClicked();  // Devolver si el usuario hizo clic en OK
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReservationEditDialogNew(Reserva reserva, Cliente cliente) {
        try {
            // Cargar el archivo FXML y crear una nueva ventana
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("EditReservas.fxml"));
            Pane page = loader.load();

            // Crear la ventana del diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL); // Bloquear interacciones con la ventana principal
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(page));

            // Configurar el controlador
            EditReservaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReservaNew(reserva);
            controller.setClienteSeleccionado(cliente);

            // Mostrar el diálogo y esperar hasta que se cierre
            dialogStage.showAndWait();

            return controller.isOkClicked(); // Devolver si el usuario hizo clic en "OK"
        } catch (IOException e) {
            mostrarAlertaError("Error", "No se pudo cargar la ventana de edición de reservas.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReservationEditDialogEdit(Reserva reserva, Cliente cliente) {
        try {
            // Cargar el archivo FXML y crear una nueva ventana
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("EditReservas.fxml"));
            Pane page = loader.load();

            // Crear la ventana del diálogo
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva Reserva");
            dialogStage.initModality(Modality.WINDOW_MODAL); // Bloquear interacciones con la ventana principal
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(page));

            // Configurar el controlador
            EditReservaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReservaEdit(reserva);
            controller.setClienteSeleccionado(cliente);

            // Mostrar el diálogo y esperar hasta que se cierre
            dialogStage.showAndWait();

            return controller.isOkClicked(); // Devolver si el usuario hizo clic en "OK"
        } catch (IOException e) {
            mostrarAlertaError("Error", "No se pudo cargar la ventana de edición de reservas.");
            e.printStackTrace();
            return false;
        }
    }



    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public ObservableList<Cliente> getPersonData() {
        return personData;
    }

    public static void main(String[] args) {
        launch();
    }
}
