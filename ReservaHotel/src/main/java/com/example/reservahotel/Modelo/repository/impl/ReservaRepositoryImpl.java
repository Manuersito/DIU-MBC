package com.example.reservahotel.Modelo.repository.impl;

import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;
import com.example.reservahotel.Modelo.repository.impl.regimen; // Nombres con mayúscula
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private ArrayList<ReservaVO> reservas;

    public ReservaRepositoryImpl() {
    }

    public ArrayList<ReservaVO> obtenerReservaCliente(String dni) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            this.reservas = new ArrayList<>();
            String sentencia = "SELECT * FROM reservas WHERE dni_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sentencia)) {
                pstmt.setString(1, dni); // Uso de parámetros para evitar inyección SQL
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("Id");
                    LocalDate llegada = rs.getDate("Fecha_Llegada").toLocalDate();
                    LocalDate salida = rs.getDate("Fecha_Salida").toLocalDate();
                    tipo_hab tipoHabitacion = null;
                    if (rs.getString("Tipo_Habitacion") != null) {
                        try {
                            tipoHabitacion = tipo_hab.valueOf(rs.getString("Tipo_Habitacion"));
                        } catch (IllegalArgumentException e) {
                            throw new ExcepcionReserva("Valor no válido en columna Tipo_Habitacion");
                        }
                    }
                    int numHabitaciones = rs.getInt("NHabitaciones");
                    Boolean fumador = rs.getBoolean("Fumador");
                    regimen regimen = null;
                    if (rs.getString("Regimen") != null) {
                        try {
                            regimen = regimen.valueOf(rs.getString("Regimen"));
                        } catch (IllegalArgumentException e) {
                            throw new ExcepcionReserva("Valor no válido en columna Regimen");
                        }
                    }
                    String dniCliente = rs.getString("Dni_Cliente");
                    ReservaVO reserva = new ReservaVO(id, llegada, salida, tipoHabitacion, numHabitaciones, fumador, regimen, dniCliente);
                    this.reservas.add(reserva);
                }
            }
            return this.reservas;
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }
    }

    public void addReserva(ReservaVO reserva) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            String sentencia = "INSERT INTO reserva (fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, regimen, fumador, dni_cliente) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sentencia)) {
                pstmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pstmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pstmt.setInt(3, reserva.getNumHabitaciones());
                pstmt.setString(4, reserva.getTipoHabitacion().name());
                pstmt.setString(5, reserva.getRegimen().name());
                pstmt.setBoolean(6, reserva.isFumador());
                pstmt.setString(7, reserva.getDniCliente());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }
    }

    public void deleteReserva(int id) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            String sql = "DELETE FROM reserva WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la eliminación");
        }
    }

    public void editReserva(ReservaVO reserva) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            String sql = "UPDATE reserva SET fecha_entrada = ?, fecha_salida = ?, num_habitaciones = ?, " +
                    "tipo_habitacion = ?, regimen = ?, fumador = ?, dni_cliente = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pstmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pstmt.setInt(3, reserva.getNumHabitaciones());
                pstmt.setString(4, reserva.getTipoHabitacion().name());
                pstmt.setString(5, reserva.getRegimen().name());
                pstmt.setBoolean(6, reserva.isFumador());
                pstmt.setString(7, reserva.getDniCliente());
                pstmt.setInt(8, reserva.getId());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la edición");
        }
    }
}
