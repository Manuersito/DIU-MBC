package com.example.reservahotel.Modelo.repository.impl;

import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;

    public ReservaRepositoryImpl() {
    }


    public ArrayList<ReservaVO> obtenerReservaCliente(String dni) throws ExcepcionReserva {
        ArrayList<ReservaVO> reservaVOS = new ArrayList<>();

        // Definir la conexión y el PreparedStatement
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM reserva WHERE dni_cliente = ?")) {

            // Establecer el valor del parámetro
            pstmt.setString(1, dni);

            // Ejecutar la consulta
            ResultSet rs = pstmt.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate llegada = rs.getDate("fecha_entrada").toLocalDate();
                LocalDate salida = rs.getDate("fecha_salida").toLocalDate();
                Integer numero = rs.getInt("num_habitaciones");
                String tipo = rs.getString("tipo_habitacion");
                boolean fumador = rs.getBoolean("fumador");
                String regimen = rs.getString("regimen");
                String dni_cliente = rs.getString("dni_cliente");

                // Crear un objeto ReservaVO y añadirlo a la lista
                ReservaVO reservaVO1 = new ReservaVO(id, llegada, salida,numero, tipo, regimen, fumador, dni_cliente);
                reservaVOS.add(reservaVO1);
            }

        } catch (SQLException var6) {
            // Lanzar una excepción si ocurre un error en la base de datos
            throw new ExcepcionReserva("No se ha podido realizar la descarga de los datos");
        }

        return reservaVOS;
    }

    public ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM reserva";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate fechaEntrada = rs.getDate("fecha_entrada").toLocalDate();
                LocalDate fechaSalida = rs.getDate("fecha_salida").toLocalDate();
                int numHabitaciones = rs.getInt("num_habitaciones");
                String tipoHabitacion = rs.getString("tipo_habitacion");
                String regimen = rs.getString("regimen");
                boolean fumador = rs.getBoolean("fumador");
                String dniCliente = rs.getString("dni_cliente");

                ReservaVO reserva = new ReservaVO(id, fechaEntrada, fechaSalida, numHabitaciones, tipoHabitacion, regimen, fumador, dniCliente);
                this.reservas.add(reserva);
            }

            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }
    }

    public void addReserva(ReservaVO reserva) throws ExcepcionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = String.format(
                    "INSERT INTO reserva (fecha_entrada, fecha_salida, num_habitaciones, tipo_habitacion, regimen, fumador, dni_cliente) " +
                            "VALUES ('%s', '%s', %d, '%s', '%s', %b, '%s')",
                    reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getNumHabitaciones(),
                    reserva.getTipoHabitacion(), reserva.getRegimen(), reserva.isFumador(), reserva.getDniCliente()
            );
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la operación");
        }
    }

    public void deleteReserva(int id) throws ExcepcionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM reserva WHERE id = %d", id);
            this.stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la eliminación");
        }
    }

    public void editReserva(ReservaVO reserva) throws ExcepcionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format(
                    "UPDATE reserva SET fecha_entrada = '%s', fecha_salida = '%s', num_habitaciones = %d, " +
                            "tipo_habitacion = '%s', regimen = '%s', fumador = %b, dni_cliente = '%s' WHERE id = %d",
                    reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getNumHabitaciones(),
                    reserva.getTipoHabitacion(), reserva.getRegimen(), reserva.isFumador(), reserva.getDniCliente(), reserva.getId()
            );
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExcepcionReserva("No se ha podido realizar la edición");
        }
    }
}
