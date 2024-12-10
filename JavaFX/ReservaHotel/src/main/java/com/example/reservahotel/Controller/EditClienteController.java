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

            dialogStage.close();
        }
    }

    @FXML
    private void clienteCancelar() {
        dialogStage.close();
    }



    private boolean isInputValid() {
        String errorMessage = "";

        // Validación del DNI (solo para España en este caso)
        if (dniField.getText() == null || dniField.getText().length() == 0) {
            errorMessage += "El DNI no puede estar vacío.\n";
        } else if (!dniField.getText().matches("\\d{8}[A-Za-z]")) {
            errorMessage += "El DNI debe tener 8 dígitos seguidos de una letra.\n";
        } else {
            String dni = dniField.getText();
            String numero = dni.substring(0, 8);
            char letra = dni.charAt(8);

            if (!esDniValido(numero, letra)) {
                errorMessage += "El DNI no es válido.\n";
            }

            // Verificar que el DNI no esté ya registrado
            if (isDniDuplicado(dni)) {
                errorMessage += "Este DNI ya está registrado.\n";
            }
        }

        // Validación de los otros campos
        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "El nombre no puede estar vacío.\n";
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "El apellido no puede estar vacío.\n";
        }
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "La dirección no puede estar vacía.\n";
        }
        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "La localidad no puede estar vacía.\n";
        }
        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "La provincia no puede estar vacía.\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            mostrarAlertaError("Campos inválidos", errorMessage);
            return false;
        }
    }

    private boolean esDniValido(String numero, char letra) {
        // Tabla de letras de DNI
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        // El número debe ser divisible por 23 para obtener la letra correcta
        int indice = Integer.parseInt(numero) % 23;

        // Compara la letra proporcionada con la letra calculada
        return letras.charAt(indice) == Character.toUpperCase(letra);
    }

    // Método para verificar si el DNI ya está registrado
    private boolean isDniDuplicado(String dni) {
        // Obtener la lista de clientes
        ArrayList<ClienteVO> clientes = modelo.getPersons();

        // Verificar si el cliente actual tiene el mismo DNI que el ingresado
        if (cliente != null && cliente.getDni().equals(dni)) {
            return false; // No es duplicado si estamos editando el mismo cliente
        }

        // Verificar si el DNI ya existe en otros clientes
        for (ClienteVO cliente : clientes) {
            if (cliente.getDNI().equals(dni)) {
                return true; // DNI duplicado encontrado
            }
        }
        return false; // No hay duplicados
    }




    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


}
