package com.example.agenda.controller;

import com.example.agenda.Modelo.AgendaModelo;
import com.example.agenda.Modelo.ExcepcionPerson;
import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Person;
import com.example.agenda.util.DateUtil;
import com.example.agenda.util.PersonUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    private AgendaModelo modelo;
    private PersonUtil personUtil;

    public void setModelo(AgendaModelo modelo) {
        this.modelo = modelo;
    }

    @FXML
    private void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        cityField.setText(person.getCity());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setCity(cityField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            okClicked = true;

            try {
                List<Person> personsList = new ArrayList<>();
                personsList.add(person);
                PersonVO personVO = personUtil.getPersonasVO((ArrayList<Person>) personsList).get(0);
                modelo.nuevoPerson(personVO);
            } catch (ExcepcionPerson e) {
                mostrarAlertaError("Error al guardar la persona", e.getMessage());
            }
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
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
