package com.example.reservahotel.Controller;

import com.example.reservahotel.Cliente;
import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

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
    @FXML
    private Label mensajeFumon;


    private Cliente cliente;
    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;
    private ModeloHotel modelo;

    public EditReservaController() {
        modelo = new ModeloHotel();  // Inicializar modelo
    }

    @FXML
    private void initialize() {
        // Agregar manejador para las opciones de 'nHabitaciones'
        for (MenuItem item : nHabitaciones.getItems()) {
            item.setOnAction(event -> {
                nHabitaciones.setText(item.getText());
                System.out.println("nHabitaciones: " + item.getText()); // Depuración
            });
        }

        // Agregar manejador para las opciones de 'tipoHabitacion'
        for (MenuItem item : tipoHabitacion.getItems()) {
            item.setOnAction(event -> {
                tipoHabitacion.setText(item.getText());
                System.out.println("tipoHabitacion: " + item.getText()); // Depuración
            });
        }

        // Agregar manejador para las opciones de 'regimenElegir'
        for (MenuItem item : regimenElegir.getItems()) {
            item.setOnAction(event -> {
                regimenElegir.setText(item.getText());
                System.out.println("regimenElegir: " + item.getText()); // Depuración
            });
        }

        // Añadir listener para el CheckBox de fumadores
        fumadorBox.setOnAction(event -> actualizarMensajeFumador());
    }

    public void actualizarMensajeFumador() {
        if (fumadorBox.isSelected()) {
            mensajeFumon.setText("En virtud de la ley de sanidad se informa a los clientes de que solo podrán fumar en las habitaciones reservadas para tal fin.");
        } else {
            mensajeFumon.setText("");  // Limpiar el mensaje si no está seleccionado
        }
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReservaNew(Reserva reserva) {
        this.reserva = reserva;
        // Limpiar los campos
        clienteDNI.setText(cliente != null ? cliente.getDni() : "");
        fechaEntrada.setValue(null);
        fechaSalida.setValue(null);
        nHabitaciones.setText("");
        tipoHabitacion.setText("");
        regimenElegir.setText("");
        fumadorBox.setSelected(false);
    }

    public void setReservaEdit(Reserva reserva) {
        this.reserva = reserva;
        clienteDNI.setText(reserva.getDniCliente());
        fechaEntrada.setValue(reserva.getFechaEntrada());
        fechaSalida.setValue(reserva.getFechaSalida());
        nHabitaciones.setText(String.valueOf(reserva.getNumHabitaciones()));
        tipoHabitacion.setText(reserva.getTipoHabitacion());
        regimenElegir.setText(reserva.getRegimen());
        fumadorBox.setSelected(reserva.isFumador());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void reservaAceptar() {
        if (isInputValid()) {
            // Actualizamos los valores de la reserva con los campos editados
            reserva.setDniCliente(clienteDNI.getText());
            reserva.setFechaEntrada(fechaEntrada.getValue());
            reserva.setFechaSalida(fechaSalida.getValue());

            try {
                // Asegúrate de que 'nHabitaciones' sea el texto seleccionado
                reserva.setNumHabitaciones(Integer.parseInt(nHabitaciones.getText()));
            } catch (NumberFormatException e) {
                mostrarAlertaError("Número inválido", "Selecciona un número válido para habitaciones.");
                return;
            }

            reserva.setTipoHabitacion(tipoHabitacion.getText());
            reserva.setRegimen(regimenElegir.getText());
            reserva.setFumador(fumadorBox.isSelected());

            // Aquí mostramos un mensaje de confirmación para la edición
            System.out.println("Reserva editada: " + reserva);

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void reservaCancelar() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        // Validar fecha de entrada
        if (fechaEntrada.getValue() == null) {
            errorMessage += "La fecha de entrada no puede estar vacía.\n";
        } else if (fechaEntrada.getValue().isBefore(LocalDate.now())) {
            errorMessage += "La fecha de entrada no puede ser anterior a la fecha actual.\n";
        }

        // Validar fecha de salida
        if (fechaSalida.getValue() == null) {
            errorMessage += "La fecha de salida no puede estar vacía.\n";
        } else if (fechaEntrada.getValue() != null && fechaSalida.getValue().isBefore(fechaEntrada.getValue())) {
            errorMessage += "La fecha de salida no puede ser anterior a la fecha de entrada.\n";
        }

        // Validar número de habitaciones
        if (nHabitaciones.getText() == null || nHabitaciones.getText().length() == 0) {
            errorMessage += "Selecciona el número de habitaciones.\n";
        } else {
            try {
                int numHabitaciones = Integer.parseInt(nHabitaciones.getText());
                if (numHabitaciones <= 0) {
                    errorMessage += "El número de habitaciones debe ser mayor que 0.\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "El número de habitaciones debe ser un número válido.\n";
            }
        }

        // Validar tipo de habitación
        if (tipoHabitacion.getText() == null || tipoHabitacion.getText().length() == 0) {
            errorMessage += "Selecciona el tipo de habitación.\n";
        }

        // Validar régimen
        if (regimenElegir.getText() == null || regimenElegir.getText().length() == 0) {
            errorMessage += "Selecciona un régimen.\n";
        }

        // Validar DNI del cliente
        if (clienteDNI.getText() == null || clienteDNI.getText().length() == 0) {
            errorMessage += "El DNI del cliente no puede estar vacío.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            mostrarAlertaError("Campos inválidos", errorMessage);
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

    public void setClienteSeleccionado(Cliente cliente) {
        this.cliente = cliente;
        if (cliente != null) {
            clienteDNI.setText(cliente.getDni());
        } else {
            clienteDNI.setText("");
        }
    }
}
