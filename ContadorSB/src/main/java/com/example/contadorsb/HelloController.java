package com.example.contadorsb;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class HelloController {


    @FXML
    private Button btmas;

    @FXML
    private Button btcero;

    @FXML
    private Button btmenos;

    @FXML
    private Label cont;

    @FXML
    private TextField actCont;

    @FXML
    private ProgressBar progreso;


    private IntegerProperty num = new SimpleIntegerProperty(1);  // Cada contador tiene su propio IntegerProperty

    // Método para manejar los eventos de los botones
    private void botonPulsado(Button button) {
        num.set(button == btmas ? num.get() + 1 :
                button == btmenos ? num.get() - 1 : 0);
    }

    // Getter para acceder a la propiedad 'num'
    public IntegerProperty numProperty() {
        return num;
    }

    @FXML
    public void initialize() {
        // Vincular la propiedad num con el texto del Label
        cont.textProperty().bind(num.asString());

        progreso.progressProperty().bind(num.divide(50.0));

    }


    // Métodos para manejar los botones
    @FXML
    private void incrementar() {
        num.set(num.get() + 1);
    }

    @FXML
    private void decrementar() {
        num.set(num.get() - 1);
    }

    @FXML
    private void resetear() {
        num.set(0);
    }


    @FXML
    private void cambiar() {
        String texto = actCont.getText(); // Obtener el texto del TextField

        try {
            // Intentar convertir el texto a un número
            int numero = Integer.parseInt(texto);
            num.set(numero); // Actualizar el valor del contador
        } catch (NumberFormatException e) {
            // Si no se puede convertir, puedes manejar el error aquí
            System.out.println("No es un número válido: " + texto);
        }
    }
}
