package com.example.reservahotel.Modelo.repository.impl;

import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;
import com.example.reservahotel.Modelo.repository.ReservaRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;  // La lista se declara aquí
    private ReservaVO reserva;

    // Constructor para inicializar la lista
    public ReservaRepositoryImpl() {
        this.reservas = new ArrayList<>();  // Inicializamos la lista
    }

    // Método modificado para obtener las reservas por DNI del cliente
    @Override
    public ArrayList<ReservaVO> obtenerReservaCliente(String dni_cliente) throws ExcepcionReserva {
        // Inicializar la lista de reservas
        ArrayList<ReservaVO> reservas = new ArrayList<>();

        // Conexión a la base de datos
        try (Connection conn = this.conexion.conectarBD()) {

            // Crear el Statement una vez que la conexión esté establecida
            String sentencia = "SELECT * FROM reserva WHERE Dni_Cliente = ?";

            // Usar PreparedStatement para evitar inyección SQL
            try (PreparedStatement pstmt = conn.prepareStatement(sentencia)) {
                pstmt.setString(1, dni_cliente);  // Establecer el DNI del cliente

                // Ejecutar la consulta
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Verificar si se encontraron resultados
                    if (!rs.next()) {
                        System.out.println("No se encontraron reservas para el cliente con DNI: " + dni_cliente);
                    } else {
                        // Si hay resultados, vuelve al inicio del ResultSet para recorrerlo
                        rs.beforeFirst();
                        while (rs.next()) {
                            // Leer los datos del ResultSet con el nuevo orden solicitado
                            int id = rs.getInt("Id");
                            LocalDate llegada = rs.getDate("fecha_entrada").toLocalDate();
                            LocalDate salida = rs.getDate("fecha_salida").toLocalDate();

                            // Procesar tipoHabitacion y regimen con manejo de excepciones
                            tipo_hab tipoHabitacion = null;
                            if (rs.getString("tipo_habitacion") != null) {
                                try {
                                    tipoHabitacion = tipo_hab.valueOf(rs.getString("Tipo_Habitacion"));
                                } catch (IllegalArgumentException e) {
                                    throw new ExcepcionReserva("Valor no válido en columna Tipo_Habitacion");
                                }
                            }

                            int numHabitaciones = rs.getInt("num_habitaciones");
                            Boolean fumador = rs.getBoolean("fumador");

                            regimen regimen = null;
                            if (rs.getString("regimen") != null) {
                                try {
                                    regimen = regimen.valueOf(rs.getString("Regimen"));
                                } catch (IllegalArgumentException e) {
                                    throw new ExcepcionReserva("Valor no válido en columna Regimen");
                                }
                            }

                            String dniCliente = rs.getString("dni_cliente");

                            // Crear el objeto ReservaVO y agregarlo a la lista
                            ReservaVO reserva = new ReservaVO(id, llegada, salida, numHabitaciones, tipoHabitacion, regimen, fumador, dniCliente);
                            reservas.add(reserva);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            // Log de error para depurar
            e.printStackTrace();
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }

        return reservas;
    }

    public void addReserva(ReservaVO reserva) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            String sentencia = "INSERT INTO reserva (Fecha_Llegada, Fecha_Salida, NHabitaciones, Tipo_Habitacion, Regimen, Fumador, Dni_Cliente) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sentencia)) {
                pstmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pstmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pstmt.setInt(3, reserva.getNumHabitaciones());
                pstmt.setString(4, reserva.getTipoHabitacion() != null ? reserva.getTipoHabitacion().name() : null);
                pstmt.setString(5, reserva.getRegimen() != null ? reserva.getRegimen().name() : null);
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
            String sql = "UPDATE reserva SET Fecha_Llegada = ?, Fecha_Salida = ?, NHabitaciones = ?, " +
                    "Tipo_Habitacion = ?, Regimen = ?, Fumador = ?, Dni_Cliente = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pstmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pstmt.setInt(3, reserva.getNumHabitaciones());
                pstmt.setString(4, reserva.getTipoHabitacion() != null ? reserva.getTipoHabitacion().name() : null);
                pstmt.setString(5, reserva.getRegimen() != null ? reserva.getRegimen().name() : null);
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
