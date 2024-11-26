package com.example.manuelbernaldezcarrascojavafx.Controller;

import Modelo.ExcepcionMoneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.manuelbernaldezcarrascojavafx.Conversor;
import com.example.manuelbernaldezcarrascojavafx.Modelo.ConversoModelo;
import com.example.manuelbernaldezcarrascojavafx.Moneda;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ConversorController {
   @FXML
   private Label monedaLabel;

   @FXML
    private TextField convertidaEuros;

   @FXML
    private TextField convertidaX;

    @FXML
    private TableView monedaTable;

    @FXML
    private TableColumn<Moneda,String> monedaNombre;

    private Conversor conversorMain;
    private MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
    private ConversoModelo conversormodelo;
    public void setCoversoModelo(ConversoModelo conversor) {this.conversormodelo = conversor;}


    public ConversorController(){
    }

    @FXML
    private void initialize() {
        monedaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());

        monedaTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> getMonedaLabel((Moneda) newValue));
    }

    private void getMonedaLabel(Moneda moneda) {
        if (moneda != null) {
            monedaLabel.setText(moneda.getNombre());
        }else{
            monedaLabel.setText("");
        }
    }

    private float getMultiplicador(){
        int seleccionado = monedaTable.getSelectionModel().getSelectedIndex();
        Moneda monedaseleccionada = (Moneda) monedaTable.getSelectionModel().getSelectedItem();
        return monedaseleccionada.getMultiplicador();
    }

    @FXML
    private void borrarButton() {
        int seleccionado = monedaTable.getSelectionModel().getSelectedIndex();
        Moneda monedaseleccionada = (Moneda) monedaTable.getSelectionModel().getSelectedItem();

        if (seleccionado >= 0) {
            monedaTable.getItems().remove(seleccionado);
            try {
                conversormodelo.eliminarMoneda(monedaseleccionada.getCodigo());
            } catch (ExcepcionMoneda e) {
                throw new RuntimeException(e);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay artículo seleccionado");
            alert.setContentText("Por favor selecciona un artículo en la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void convertiraX() {
        if (convertidaEuros.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay articulo seleccionado");
        }else{
            float convertida =Float.parseFloat(convertidaEuros.getText())*getMultiplicador();
            convertidaX.setText(String.valueOf(convertida));
        }
    }

    @FXML
    private void convertiraEuros() {
        if (convertidaX.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay articulo seleccionado");
        }else{
            float convertida =Float.parseFloat(convertidaX.getText())*(2-getMultiplicador());
            convertidaX.setText(String.valueOf(convertida));
        }
    }
    @FXML
    private void convertirButton(){
        if (convertidaEuros.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay articulo seleccionado");
        }else{
            float convertida =Float.parseFloat(convertidaEuros.getText())*getMultiplicador();
            convertidaX.setText(String.valueOf(convertida));
        }if (convertidaX.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No hay articulo seleccionado");
        }else{
            float convertida =Float.parseFloat(convertidaX.getText())*(2-getMultiplicador());
            convertidaX.setText(String.valueOf(convertida));
        }
    }

    public void setConversorMain(Conversor conversorMain) {
        this.conversorMain = conversorMain;
        monedaTable.setItems(conversorMain.getMonedas());
    }




}