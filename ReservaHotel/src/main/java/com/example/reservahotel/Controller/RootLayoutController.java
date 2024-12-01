package com.example.reservahotel.Controller;

import com.example.reservahotel.MainApp;
import com.example.reservahotel.Modelo.repository.impl.ReservaRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

public class RootLayoutController {

    private MainApp mainApp; // Evitamos crear una nueva instancia

    private ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private MenuItem menuDoble;

    @FXML
    private MenuItem menuDobleIndividual;

    @FXML
    private MenuItem menuSuit;

    @FXML
    private MenuItem menuJuniorSuit;

    @FXML
    private void initialize() {
        // Vincular eventos a los menús
        menuDoble.setOnAction(this::abrirCarruselDoble);
        menuDobleIndividual.setOnAction(this::abrirCarruselDobleIndividual);
        menuSuit.setOnAction(this::abrirCarruselSuit);
        menuJuniorSuit.setOnAction(this::abrirCarruselJuniorSuit);
    }

    @FXML
    private void abrirCarruselDoble(ActionEvent event) {
        mostrarCarrusel("Doble", List.of(
                cargarImagen("/images/doble1.jpg"),
                cargarImagen("/images/doble2.jpg"),
                cargarImagen("/images/doble3.jpg")
        ));
    }

    @FXML
    private void abrirCarruselDobleIndividual(ActionEvent event) {
        mostrarCarrusel("Doble_uso_Individual", List.of(
                cargarImagen("/images/doble_individual1.jpg"),
                cargarImagen("/images/doble_individual2.jpg"),
                cargarImagen("/images/doble_individual3.jpg")
        ));
    }

    @FXML
    private void abrirCarruselSuit(ActionEvent event) {
        mostrarCarrusel("Suite", List.of(
                cargarImagen("/images/suite1.jpeg"),
                cargarImagen("/images/suite2.jpeg"),
                cargarImagen("/images/suite3.jpeg")
        ));
    }

    @FXML
    private void abrirCarruselJuniorSuit(ActionEvent event) {
        mostrarCarrusel("Junior_Suite", List.of(
                cargarImagen("/images/jr_suite1.jpeg"),
                cargarImagen("/images/jr_suite2.jpeg"),
                cargarImagen("/images/jr_suite3.jpeg")
        ));
    }

    private Image cargarImagen(String ruta) {
        URL url = getClass().getResource(ruta);
        if (url == null) {
            System.err.println("No se encontró la imagen en: " + ruta);
            return null; // Devuelve null si no se encuentra
        }
        return new Image(url.toExternalForm());
    }

    private void mostrarCarrusel(String tipoHabitacion, List<Image> imagenes) {
        try {
            // Cargar el FXML del carrusel
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/reservahotel/Carrusel.fxml"));
            VBox page = loader.load();

            // Obtener el controlador del carrusel
            CarruselDialogController controller = loader.getController();
            controller.configurarCarrusel(tipoHabitacion, imagenes, reservaRepository.countHabitacionesOcupadas(tipoHabitacion));

            // Mostrar el diálogo
            Stage dialog = new Stage();
            dialog.setTitle("Galería de " + tipoHabitacion);
            dialog.setScene(new Scene(page));
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar el carrusel: " + e.getMessage());
        }
    }
}
