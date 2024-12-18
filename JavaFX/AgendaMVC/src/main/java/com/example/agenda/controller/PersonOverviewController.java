package com.example.agenda.controller;

import com.example.agenda.Modelo.AgendaModelo;
import com.example.agenda.Modelo.ExcepcionPerson;
import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Modelo.repository.PersonRepository;
import com.example.agenda.Modelo.repository.impl.PersonRepositoryImpl;
import com.example.agenda.util.DateUtil;
import com.example.agenda.util.PersonUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.agenda.MainApp;
import com.example.agenda.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    private PersonRepository personRepository = new PersonRepositoryImpl();
    private Person person = new Person();
    private PersonUtil personUtil = new PersonUtil();

    private AgendaModelo modelo = new AgendaModelo();

    public void setModelo(AgendaModelo modelo) {
        this.modelo = modelo;
    }

    // Reference to the main application.
    private MainApp mainApp;

    private void loadPersonData() {
        try {
            ArrayList<PersonVO> personVOList = personRepository.ObtenerListaPersonas();
            ArrayList<Person> personList = personUtil.getPersonas(personVOList);
            personTable.getItems().setAll(personList); // Actualiza la tabla con nuevos datos
        } catch (ExcepcionPerson e) {
            mostrarAlertaError("Error al cargar la lista de personas", e.getMessage());
        }
    }


    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    /**
     * Called when the user clicks on the delete button.
     */
    private void handleDeletePerson() throws ExcepcionPerson{
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Integer cod = personTable.getItems().get(selectedIndex).getId();
            modelo.eliminarPerson(cod);
            personTable.getItems().remove(selectedIndex);
            loadPersonData();
        } else {
            // Nothing selected.
            Alert alert= new Alert(AlertType.WARNING);
                    alert.setTitle("No Selection");
                    alert.setHeaderText("No Person Selected");
                    alert.setContentText("Please select a person in the table.");
                    alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            try {
                List<Person> personsList = new ArrayList<>();
                personsList.add(tempPerson);  // Cambiado a tempPerson
                PersonVO personVO = personUtil.getPersonasVO((ArrayList<Person>) personsList).get(0);
                modelo.nuevoPerson(personVO);
                personTable.getItems().add(tempPerson);
                loadPersonData();

            } catch (ExcepcionPerson e) {
                mostrarAlertaError("Error al guardar la persona", e.getMessage());
            }
        }
    }


    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            // Abrir el diálogo de edición
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);

            if (okClicked) {
                try {
                    // Convertir la persona seleccionada a PersonVO
                    List<Person> personsList = new ArrayList<>();
                    personsList.add(selectedPerson);


                    PersonVO personVO = personUtil.PersonToPersonVO(selectedPerson);
                    //personUtil.getPersonasVO((ArrayList<Person>) personsList).get(0);
                    // Llamar al método para actualizar la persona en la base de datos
                    modelo.actualizarPerson(personVO);
                    loadPersonData();

                    // Mostrar detalles actualizados en la vista
                    showPersonDetails(selectedPerson);
                } catch (ExcepcionPerson e) {
                    mostrarAlertaError("Error al editar la persona", e.getMessage());
                }
            }
        } else {
            // Mostrar una alerta si no hay ninguna persona seleccionada
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
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
