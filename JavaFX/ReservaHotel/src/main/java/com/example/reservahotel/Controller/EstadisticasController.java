package com.example.reservahotel.Controller;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.example.reservahotel.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class EstadisticasController {

    @FXML
    private BarChart<String, Double> barChart; // Cambié a Double porque ahora manejamos medias.

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    // Número total de habitaciones disponibles por tipo
    private static final int totalHabitacionesDobles = 80;
    private static final int totalHabitacionesDoblesIndividuales = 20;
    private static final int totalHabitacionesJuniorSuite = 15;
    private static final int totalHabitacionesSuite = 5;

    @FXML
    private void initialize() {
        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        monthNames.addAll(Arrays.asList(meses));
        xAxis.setCategories(monthNames);
    }

    public void setReservaData(List<Reserva> reservas) {
        // Contadores para las habitaciones reservadas por tipo por mes
        int[] doblesReservadas = new int[12];
        int[] doblesIndReservadas = new int[12];
        int[] juniorSuiteReservadas = new int[12];
        int[] suiteReservadas = new int[12];

        // Contar las reservas por tipo de habitación y por mes
        for (Reserva reserva : reservas) {
            LocalDate fechaLlegada = reserva.getFechaEntrada();
            if (fechaLlegada != null) {
                int mes = fechaLlegada.getMonthValue() - 1; // Mes en formato 0-11

                // Aquí asignas los contadores por tipo de habitación
                switch (reserva.getTipoHabitacion()) {
                    case "DOBLE":
                        doblesReservadas[mes]++;
                        break;
                    case "DOBLE_USO_INDIVIDUAL":
                        doblesIndReservadas[mes]++;
                        break;
                    case "JUNIOR_SUITE":
                        juniorSuiteReservadas[mes]++;
                        break;
                    case "SUITE":
                        suiteReservadas[mes]++;
                        break;
                    default:
                        System.err.println("Tipo de habitación desconocido: " + reserva.getTipoHabitacion());
                        break;
                }
            } else {
                System.err.println("Fecha inválida: " + reserva.getFechaEntrada());
            }
        }
// Crear una serie para mostrar las medias
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < 12; i++) {
            // Calcular la media de ocupación para cada tipo de habitación en cada mes
            double mediaOcupacionDobles = (double) doblesReservadas[i] / totalHabitacionesDobles;
            double mediaOcupacionDoblesIndividuales = (double) doblesIndReservadas[i] / totalHabitacionesDoblesIndividuales;
            double mediaOcupacionJuniorSuite = (double) juniorSuiteReservadas[i] / totalHabitacionesJuniorSuite;
            double mediaOcupacionSuite = (double) suiteReservadas[i] / totalHabitacionesSuite;

            // Calcula la media total de ocupación para el mes (promedio de todos los tipos de habitaciones)
            double mediaMensual = (mediaOcupacionDobles + mediaOcupacionDoblesIndividuales + mediaOcupacionJuniorSuite + mediaOcupacionSuite) / 4;

            // Agregar los datos a la serie
            series.getData().add(new XYChart.Data<>(monthNames.get(i), mediaMensual));
        }

        // Limpiar y agregar la serie al gráfico
        barChart.getData().clear();
        barChart.getData().add(series);
    }

}