package com.example.reservahotel.Controller;

import com.example.reservahotel.Modelo.ClienteVO;
import com.example.reservahotel.Modelo.ModeloHotel;
import com.example.reservahotel.Cliente;
import com.example.reservahotel.MainApp;
import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.ClientRepository;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;
import com.example.reservahotel.Modelo.repository.impl.ClientRepositoryImpl;
import com.example.reservahotel.Reserva;
import com.example.reservahotel.Util.ClientUtil;
import com.example.reservahotel.Util.ReservaUtil;
import eu.hansolo.tilesfx.tools.DoubleExponentialSmoothingForLinearSeries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.ArrayList;
import java.util.List;

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
    private TextField dniField;


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
    private ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
    @FXML
    private void initialize() {
        // Configura las columnas de la tabla con las propiedades correctas
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        reservationColumn.setCellValueFactory(cellData -> cellData.getValue().fechaEntradaProperty().asString());
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

    private void loadPersonData() {
        try {
            ArrayList<ClienteVO> personVOList = clientRepository.ObtenerListaPersonas();
            ArrayList<Cliente> personList = ClientUtil.getClientes(personVOList);
            clientTable.getItems().setAll(personList); // Actualiza la tabla con nuevos datos
        } catch (ExcepcionCliente e) {
            mostrarAlertaError("Error al cargar la lista de personas", e.getMessage());
        }
    }

    public void showReservaDetails(Reserva reserva) {
        if (reserva != null) {
            entradaLabel.setText(String.valueOf(reserva.getFechaEntrada()));
            salidaLabel.setText(String.valueOf(reserva.getFechaSalida()));
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
            alert.setTitle("No seleccionado");
            alert.setHeaderText("Sin persona seleccionada");
            alert.setContentText("Por favor selecciona una persona.");
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
            alert.setTitle("No seleccionado");
            alert.setHeaderText("Sin reserva seleccionada");
            alert.setContentText("Por favor selecciona una reserva.");
            alert.showAndWait();
        }
    }

    @FXML
    private void addClientButton() {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showPersonEditDialogNew(tempCliente);
        if (okClicked) {
            try {
                List<Cliente> personsList = new ArrayList<>();
                personsList.add(tempCliente);  // Cambiado a tempPerson
                ClienteVO personVO = ClientUtil.getClientesVO((ArrayList<Cliente>) personsList).get(0);
                modelo.nuevoPerson(personVO);
                clientTable.getItems().add(tempCliente);
                loadPersonData();

            } catch (ExcepcionCliente e) {
                mostrarAlertaError("Error al guardar la persona", e.getMessage());
            }
        }
    }

    @FXML
    private void editClientButton() {
        Cliente selectedPerson = clientTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            // Abrir el diálogo de edición
            boolean okClicked = mainApp.showPersonEditDialogEdit(selectedPerson);

            if (okClicked) {
                try {
                    // Convertir la persona seleccionada a PersonVO
                    List<Cliente> personsList = new ArrayList<>();
                    personsList.add(selectedPerson);


                    ClienteVO personVO = ClientUtil.clienteToClienteVO(selectedPerson);
                    //personUtil.getPersonasVO((ArrayList<Person>) personsList).get(0);
                    // Llamar al método para actualizar la persona en la base de datos
                    modelo.actualizarPerson(personVO);
                    loadPersonData();

                    // Mostrar detalles actualizados en la vista
                    showPersonDetails(selectedPerson);
                } catch (ExcepcionCliente e) {
                    mostrarAlertaError("Error al editar la persona", e.getMessage());
                }
            }
        } else {
            // Mostrar una alerta si no hay ninguna persona seleccionada
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No seleccionado");
            alert.setHeaderText("Sin persona seleccionada");
            alert.setContentText("Por favor selecciona una persona.");
            alert.showAndWait();
        }
    }

    @FXML
    private void addReservationButton() {
        Cliente clienteSeleccionado = clientTable.getSelectionModel().getSelectedItem();

        if (clienteSeleccionado != null) {
            Reserva tempReserva = new Reserva();

            // Mostrar el diálogo para agregar una reserva
            boolean okClicked = mainApp.showReservationEditDialogNew(tempReserva, clienteSeleccionado);

            if (okClicked) {
                try {
                    // Convertir la reserva a ReservaVO para guardarla en el modelo
                    List<Reserva> reservaList = new ArrayList<>();
                    reservaList.add(tempReserva);
                    ReservaVO reservaVO = reservaUtil.getReservasVO((ArrayList<Reserva>) reservaList).get(0);

                    modelo.nuevaReserva(reservaVO);  // Guardar en la base de datos
                    reservationTable.getItems().add(tempReserva);  // Añadir a la tabla
                    loadReservasData(clienteSeleccionado.getDni());  // Actualizar la vista de reservas
                } catch (ExcepcionReserva e) {
                    mostrarAlertaError("Error al guardar la reserva", e.getMessage());
                }
            }
        } else {
            // Mostrar una alerta si no hay cliente seleccionado
            mostrarAlertaError("Sin cliente seleccionado", "Por favor, seleccione un cliente para añadir una reserva.");
        }
    }

    @FXML
    private void editReservationButton() {
        Reserva selectedReserva = reservationTable.getSelectionModel().getSelectedItem();

        if (selectedReserva != null) {
            // Obtener el cliente asociado a la reserva
            Cliente clienteSeleccionado = clientTable.getSelectionModel().getSelectedItem();
            if (clienteSeleccionado != null) {
                // Mostrar el diálogo de edición para la reserva
                boolean okClicked = mainApp.showReservationEditDialogEdit(selectedReserva, clienteSeleccionado);

                if (okClicked) {
                    try {
                        // Convertir la reserva modificada a ReservaVO
                        List<Reserva> reservaList = new ArrayList<>();
                        reservaList.add(selectedReserva);
                        ReservaVO reservaVO = reservaUtil.getReservasVO((ArrayList<Reserva>) reservaList).get(0);

                        // Llamar al modelo para actualizar la reserva en la base de datos
                        modelo.editarReserva(reservaVO);
                        loadReservasData(clienteSeleccionado.getDni());  // Actualizar la vista de reservas

                        // Mostrar detalles actualizados en la vista
                        showReservaDetails(selectedReserva);
                    } catch (ExcepcionReserva e) {
                        mostrarAlertaError("Error al editar la reserva", e.getMessage());
                    }
                }
            } else {
                // Mostrar alerta si no hay cliente seleccionado
                mostrarAlertaError("Sin cliente seleccionado", "Por favor, seleccione un cliente antes de editar la reserva.");
            }
        } else {
            // Mostrar una alerta si no hay ninguna reserva seleccionada
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No seleccionado");
            alert.setHeaderText("Sin reserva seleccionada");
            alert.setContentText("Por favor selecciona una reserva.");
            alert.showAndWait();
        }
    }


    @FXML
    private void dniField() {
        String dni = dniField.getText().trim();  // Obtener el DNI ingresado

        if (!dni.isEmpty()) {
            // Filtra los clientes por el DNI
            ObservableList<Cliente> filteredList = FXCollections.observableArrayList();
            for (Cliente cliente : mainApp.getPersonData()) {
                if (cliente.getDni().equals(dni)) {
                    filteredList.add(cliente);
                    break; // Se detiene después de encontrar el primer cliente que coincida
                }
            }
            clientTable.setItems(filteredList);  // Actualiza la tabla con el cliente filtrado
        } else {
            // Si el campo DNI está vacío, muestra todos los clientes
            clientTable.setItems(mainApp.getPersonData());
        }
    }


    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Corrige los datos invalidos");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
