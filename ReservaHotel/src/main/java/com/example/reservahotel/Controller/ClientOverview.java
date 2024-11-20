package com.example.reservahotel.Controller;

import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Cliente;
import com.example.reservahotel.MainApp;
import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;
import com.example.reservahotel.Reserva;
import com.example.reservahotel.Util.ReservaUtil;
import eu.hansolo.tilesfx.tools.DoubleExponentialSmoothingForLinearSeries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;

public class ClientOverview {
    ObservableList<Reserva> reservaObservableList = FXCollections.observableArrayList();
    ArrayList<Reserva> reservas = new ArrayList<>();

    @FXML
    private TableView<Reserva> reservationTable;
    @FXML
    private TableColumn<Reserva, String> reservationColumn;

    @FXML
    private TableView<Cliente> clientTable;
    @FXML
    private TableColumn<Cliente, String> firstNameColumn;
    @FXML
    private TableColumn<Cliente, String> lastNameColumn;

    @FXML
    private Label dniLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;

    @FXML
    private Label entradaLabel;
    @FXML
    private Label salidaLabel;
    @FXML
    private Label numLabel;
    @FXML
    private Label tipoLabel;
    @FXML
    private Label regimenLabel;
    @FXML
    private Label fumadorLabel;

    private ModeloHotel modelo;
    private MainApp mainApp;
    private ReservaUtil reservaUtil = new ReservaUtil();

    @FXML
    private void initialize() {
        // Configura las columnas de la tabla con las propiedades correctas
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        reservationColumn.setCellValueFactory(cellData -> cellData.getValue().fechaEntradaProperty());
        // Limpiar los detalles del cliente
        showPersonDetails(null);

        // Escuchar cambios de selección en la tabla
        clientTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showPersonDetails(newValue);
                        loadReservasData(newValue.getDni());
                    } else{
                        reservationTable.getItems().clear();
                    }

                });

        reservationTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservaDetails(newValue));
        System.out.println("inizialice overview controller");

    }

    public void setModelo(ModeloHotel modelo) {
        this.modelo = modelo;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        clientTable.setItems(mainApp.getPersonData());  // Se pasan los datos al TableView
    }

    private void showPersonDetails(Cliente cliente) {
        if (cliente != null) {
            dniLabel.setText(cliente.getDni());
            nombreLabel.setText(cliente.getFirstName());
            apellidoLabel.setText(cliente.getLastName());
            direccionLabel.setText(cliente.getStreet());
            localidadLabel.setText(cliente.getCity());
            provinciaLabel.setText(cliente.getState());


        } else {
            dniLabel.setText("");
            nombreLabel.setText("");
            apellidoLabel.setText("");
            direccionLabel.setText("");
            localidadLabel.setText("");
            provinciaLabel.setText("");
        }
    }
    public void loadReservasData(String dniCliente) {
        try {
            if (dniCliente != null && !dniCliente.isEmpty()) {
                ObservableList<Reserva> reservaData = FXCollections.observableArrayList(modelo.mostrarReservas(dniCliente));

                if (reservaData.isEmpty()) {
                    mostrarAlertaError("Sin reservas", "Este cliente no tiene reservas.");
                    reservationTable.getItems().clear();
                } else {
                    reservationTable.setItems(reservaData);
                }
            } else {
                mostrarAlertaError("Error DNI", "El DNI del cliente no está registrado.");
            }
        } catch (Exception e) {
            mostrarAlertaError("Error al cargar", "Error: " + e.getMessage());
        }
    }

    public void showReservaDetails(Reserva reserva) {
        if (reserva != null) {
            entradaLabel.setText(reserva.getFechaEntrada());
            salidaLabel.setText(reserva.getFechaSalida());
            numLabel.setText(String.valueOf(reserva.getNumHabitaciones()));
            tipoLabel.setText(reserva.getTipoHabitacion());
            regimenLabel.setText(reserva.getRegimen());
            fumadorLabel.setText(String.valueOf(reserva.isFumador()));


        } else {
            // Limpiar los campos si no hay reserva seleccionada
            entradaLabel.setText("");
            salidaLabel.setText("");
            numLabel.setText("");
            tipoLabel.setText("");
            regimenLabel.setText("");
            fumadorLabel.setText("");
        }
    }


    @FXML
    private void deleteClientButton() {
        int selectedIndex = clientTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String cod = clientTable.getItems().get(selectedIndex).getDni();
            try {
                modelo.eliminarPerson(cod);
                clientTable.getItems().remove(selectedIndex);
            } catch (Exception e) {
                mostrarAlertaError("Error al eliminar", e.getMessage());
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteReservationButton() {
        int selectedIndex = reservationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            int cod = (reservationTable.getItems().get(selectedIndex).getId());
            try {
                modelo.eliminarReserva(cod);
                reservationTable.getItems().remove(selectedIndex);
            } catch (Exception e) {
                mostrarAlertaError("Error al eliminar", e.getMessage());
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }


    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
