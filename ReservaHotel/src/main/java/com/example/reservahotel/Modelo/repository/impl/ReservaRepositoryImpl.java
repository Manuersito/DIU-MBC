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

    @Override
    public ArrayList<ReservaVO> obtenerReservaCliente(String dni_cliente) throws ExcepcionReserva {
        ArrayList<ReservaVO> reservas = new ArrayList<>();
        try (Connection conn = this.conexion.conectarBD()) {

            // Verificar la conexión
            if (conn == null) {
                System.out.println("Error: No se pudo conectar a la base de datos.");
                return reservas;  // Retornar lista vacía si no se pudo conectar
            }

            String sentencia = "SELECT * FROM reserva WHERE dni_cliente = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sentencia)) {
                pstmt.setString(1, dni_cliente);  // Establecer el DNI del cliente

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("No se encontraron reservas para el cliente con DNI: " + dni_cliente);
                    } else {
                        rs.beforeFirst();
                        while (rs.next()) {
                            int id = rs.getInt("Id");
                            LocalDate llegada = rs.getDate("fecha_entrada").toLocalDate();
                            LocalDate salida = rs.getDate("fecha_salida").toLocalDate();

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

                            ReservaVO reserva = new ReservaVO(id, llegada, salida, numHabitaciones, tipoHabitacion, regimen, fumador, dniCliente);
                            reservas.add(reserva);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }

        return reservas;
    }

    // Método para añadir una nueva reserva
    public void addReserva(ReservaVO reserva) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            if (conn == null) {
                throw new ExcepcionReserva("No se pudo conectar a la base de datos");
            }

            String sentencia = "INSERT INTO reserva (fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, regimen, fumador, dni_cliente) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sentencia)) {
                pstmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pstmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pstmt.setInt(3, reserva.getNumHabitaciones());
                pstmt.setString(4, reserva.getTipoHabitacion() != null ? reserva.getTipoHabitacion().name() : null);
                pstmt.setString(5, reserva.getRegimen() != null ? reserva.getRegimen().name() : null);
                pstmt.setBoolean(6, reserva.isFumador());
                pstmt.setString(7, reserva.getDniCliente());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reserva añadida con éxito.");
                } else {
                    System.out.println("No se pudo añadir la reserva.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }
    }

    // Método para eliminar una reserva
    public void deleteReserva(int id) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            if (conn == null) {
                throw new ExcepcionReserva("No se pudo conectar a la base de datos");
            }

            String sql = "DELETE FROM reserva WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reserva eliminada con éxito.");
                } else {
                    System.out.println("No se encontró una reserva con ese ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionReserva("No se ha podido realizar la eliminación");
        }
    }

    // Método para editar una reserva
    public void editReserva(ReservaVO reserva) throws ExcepcionReserva {
        try (Connection conn = this.conexion.conectarBD()) {
            if (conn == null) {
                throw new ExcepcionReserva("No se pudo conectar a la base de datos");
            }

            String sql = "UPDATE reserva SET fecha_entrada = ?, fecha_salida = ?, num_habitaciones = ?, " +
                    "tipo_habitacion = ?, regimen = ?, fumador = ?, dni_cliente = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, Date.valueOf(reserva.getFechaEntrada()));
                pstmt.setDate(2, Date.valueOf(reserva.getFechaSalida()));
                pstmt.setInt(3, reserva.getNumHabitaciones());
                pstmt.setString(4, reserva.getTipoHabitacion() != null ? reserva.getTipoHabitacion().name() : null);
                pstmt.setString(5, reserva.getRegimen() != null ? reserva.getRegimen().name() : null);
                pstmt.setBoolean(6, reserva.isFumador());
                pstmt.setString(7, reserva.getDniCliente());
                pstmt.setInt(8, reserva.getId());

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reserva editada con éxito.");
                } else {
                    System.out.println("No se encontró una reserva con ese ID para editar.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionReserva("No se ha podido realizar la edición");
        }
    }

    // Método para contar habitaciones ocupadas por tipo de habitación
    public int countHabitacionesOcupadas(String tipoHabitacion) throws ExcepcionReserva {
        int habitacionesOcupadas = 0;

        try (Connection conn = this.conexion.conectarBD()) {
            if (conn == null) {
                throw new ExcepcionReserva("No se pudo conectar a la base de datos");
            }

            // Consulta para contar habitaciones ocupadas
            String sql = "SELECT COUNT(*) AS total_ocupadas FROM reserva WHERE tipo_habitacion = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, tipoHabitacion); // Establecer el tipo de habitación

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        habitacionesOcupadas = rs.getInt("total_ocupadas"); // Obtener el total
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExcepcionReserva("No se ha podido obtener el conteo de habitaciones ocupadas para el tipo: " + tipoHabitacion);
        }

        return habitacionesOcupadas;
    }


}
