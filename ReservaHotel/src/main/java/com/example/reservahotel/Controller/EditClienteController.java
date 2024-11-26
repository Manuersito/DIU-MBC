package com.example.reservahotel.Controller;

import com.example.reservahotel.Cliente;
import com.example.reservahotel.Modelo.ClienteVO;
import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditClienteController {
    @FXML
    private TextField dniField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;

    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;
    private ModeloHotel modelo = new ModeloHotel();

    public EditClienteController() throws ExcepcionCliente {
    }



    @FXML
    private void initialize() throws ExcepcionCliente {
        ArrayList<ClienteVO> clientes = modelo.getPersons();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAgendaModelo(ModeloHotel modelo) {
        this.modelo = modelo;
    }

    public void setClienteNew(Cliente cliente) {
        this.cliente = cliente;

        dniField.setText(cliente.getDni());
        nombreField.setText(cliente.getFirstName());
        apellidoField.setText(cliente.getLastName());
        direccionField.setText(cliente.getStreet());
        localidadField.setText(cliente.getCity());
        provinciaField.setText(cliente.getState());

    }
    public void setClienteEdit(Cliente cliente) {
        this.cliente = cliente;

        dniField.setText(cliente.getDni());
        dniField.setDisable(true);
        nombreField.setText(cliente.getFirstName());
        apellidoField.setText(cliente.getLastName());
        direccionField.setText(cliente.getStreet());
        localidadField.setText(cliente.getCity());
        provinciaField.setText(cliente.getState());

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void clienteAceptar() {
        if (isInputValid()) {
            cliente.setDni(dniField.getText());
            cliente.setFirstName(nombreField.getText());
            cliente.setLastName(apellidoField.getText());
            cliente.setStreet(direccionField.getText());
            cliente.setCity(localidadField.getText());
            cliente.setState(provinciaField.getText());

            okClicked = true;

            // Actualiza la barra de progreso después de añadir una persona

            dialogStage.close();
        }
    }

    @FXML
    private void clienteCancelar() {
        dialogStage.close();
    }



    private boolean isInputValid() {
        String errorMessage = "";

        if (dniField.getText() == null || dniField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            mostrarAlertaError("Invalid Fields", errorMessage);
            return false;
        }
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
