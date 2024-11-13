package com.example.reservahotel.Controller;

import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Modelo.ClienteVO;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Util.ClientUtil;
import com.example.reservahotel.Cliente;
import com.example.reservahotel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

public class ClientOverview {

    @FXML
    private TableView<Cliente> personTable;
    @FXML
    private TableColumn<Cliente, String> firstNameColumn;
    @FXML
    private TableColumn<Cliente, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;

    private ModeloHotel modelo;
    private ClientUtil personUtil = new ClientUtil();
    private MainApp mainApp;

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Limpiar los detalles del cliente
        showPersonDetails(null);

        // Escuchar cambios de selección en la tabla
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setModelo(ModeloHotel modelo) {
        this.modelo = modelo;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Cliente cliente) {
        if (cliente != null) {
            firstNameLabel.setText(cliente.getFirstName());
            lastNameLabel.setText(cliente.getLastName());
            streetLabel.setText(cliente.getStreet());
            cityLabel.setText(cliente.getCity());
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String cod = personTable.getItems().get(selectedIndex).getDni();
            try {
                modelo.eliminarPerson(cod);
                personTable.getItems().remove(selectedIndex);
            } catch (ExcepcionCliente e) {
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

//    @FXML
//    private void handleNewPerson() {
//        Cliente tempPerson = new Cliente();
//        boolean okClicked = mainApp.showClientEditDialog(tempPerson);
//        if (okClicked) {
//            try {
//                List<Cliente> personsList = new ArrayList<>();
//                personsList.add(tempPerson);
//                ClienteVO clientVO = personUtil.getClientesVO((ArrayList<Cliente>) personsList).get(0);
//                modelo.nuevoPerson(clientVO);
//                personTable.getItems().add(tempPerson);
//            } catch (ExcepcionCliente e) {
//                mostrarAlertaError("Error al guardar la persona", e.getMessage());
//            }
//        }
//    }

//    @FXML
//    private void handleEditPerson() {
//        Cliente selectedPerson = personTable.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showClientEditDialog(selectedPerson);
//            if (okClicked) {
//                try {
//                    ClienteVO personVO = personUtil.clienteToClienteVO(selectedPerson);
//                    modelo.actualizarPerson(personVO);
//                    showPersonDetails(selectedPerson);
//                } catch (ExcepcionCliente e) {
//                    mostrarAlertaError("Error al editar la persona", e.getMessage());
//                }
//            }
//        } else {
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.setTitle("No Selection");
//            alert.setHeaderText("No Person Selected");
//            alert.setContentText("Please select a person in the table.");
//            alert.showAndWait();
//        }
//    }
//
    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
