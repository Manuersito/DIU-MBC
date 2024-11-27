package com.example.reservahotel.Controller;

import com.example.reservahotel.Cliente;
import com.example.reservahotel.Modelo.ClienteVO;
import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;
import com.example.reservahotel.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditReservaController {
    @FXML
    private Label clienteDNI;
    @FXML
    private DatePicker fechaEntrada;
    @FXML
    private DatePicker fechaSalida;
    @FXML
    private MenuButton nHabitaciones;
    @FXML
    private MenuButton tipoHabitacion;
    @FXML
    private MenuButton regimenElegir;
    @FXML
    private CheckBox fumadorBox;

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;
    private ModeloHotel modelo = new ModeloHotel();

    public EditReservaController() throws ExcepcionReserva {
    }



    @FXML
    private void initialize() throws ExcepcionReserva {
        ArrayList<ReservaVO> reservas = modelo.getReservas(reserva.getDniCliente());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAgendaModelo(ModeloHotel modelo) {
        this.modelo = modelo;
    }

    public void setReservaNew(Reserva reserva) {
        this.reserva = reserva;

        clienteDNI.setText(reserva.getDniCliente());
        fechaEntrada.setValue(null);
        fechaSalida.setValue(null);
        nHabitaciones.setText(null);
        tipoHabitacion.setText(null);
        regimenElegir.setText(null);
        fumadorBox.setSelected(false);

    }
    public void setReservaEdit(Cliente cliente) {
        this.reserva = reserva;

        clienteDNI.setText(reserva.getDniCliente());
        fechaEntrada.setValue(reserva.getFechaEntrada());
        fechaSalida.setValue(reserva.getFechaSalida());
        nHabitaciones.setText(String.valueOf(reserva.getNumHabitaciones()));
        tipoHabitacion.setText(reserva.getTipoHabitacion());
        regimenElegir.setText(reserva.getRegimen());
        fumadorBox.setSelected(false);

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void reservaAceptar() {
        if (isInputValid()) {
            reserva.setDniCliente(clienteDNI.getText());
            reserva.setFechaEntrada(fechaEntrada.getValue());
            reserva.setFechaSalida(fechaSalida.getValue());
            reserva.setNumHabitaciones(Integer.parseInt(nHabitaciones.getText()));
            reserva.setTipoHabitacion(tipoHabitacion.getText());
            reserva.setRegimen(regimenElegir.getText());
            reserva.setFumador(fumadorBox.isSelected());
            okClicked = true;

            // Actualiza la barra de progreso después de añadir una persona

            dialogStage.close();
        }
    }

    @FXML
    private void reservaCancelar() {
        dialogStage.close();
    }



    private boolean isInputValid() {
        String errorMessage = "";

//        if (dniField.getText() == null || dniField.getText().length() == 0) {
//            errorMessage += "No valid first name!\n";
//        }
//        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
//            errorMessage += "No valid last name!\n";
//        }
//        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
//            errorMessage += "No valid street!\n";
//        }
//        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
//            errorMessage += "No valid city!\n";
//        }
//        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
//            errorMessage += "No valid postal code!\n";
//        }
//        if (errorMessage.length() == 0) {
//            return true;
//        } else {
//            mostrarAlertaError("Invalid Fields", errorMessage);
//            return false;
//        }
        return false;
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
