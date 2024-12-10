package com.example.reservahotel.Modelo.repository.impl;

import com.example.reservahotel.Modelo.ClienteVO;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientRepositoryImpl {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> personas;
    private ClienteVO persona;

    public ClientRepositoryImpl() {
    }

    public ArrayList<ClienteVO> ObtenerListaPersonas() throws ExcepcionCliente {
        ArrayList<ClienteVO> personas = new ArrayList<>();

        try {
            Connection conn = this.conexion.conectarBD();
            System.out.println("Conexión establecida: " + (conn != null));

            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM cliente";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");

                ClienteVO persona = new ClienteVO(dni, nombre, apellidos, direccion, localidad, provincia);
                personas.add(persona);
            }

            System.out.println("Número de clientes obtenidos: " + personas.size());

            this.conexion.desconectarBD(conn);
            return personas;
        } catch (SQLException e) {
            throw new ExcepcionCliente("No se ha podido realizar la operación: " + e.getMessage());
        }
    }



    public void addPersona(ClienteVO m) throws ExcepcionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = String.format(
                    "INSERT INTO cliente (dni, nombre, apellidos, direccion, localidad, provincia) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                    m.getDNI(), m.getFirstName(), m.getLastName(), m.getStreet(), m.getCity(), m.getState()
            );
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExcepcionCliente("No se ha podido realizar la operación");
        }
    }

    public void deletePersona(String dni) throws ExcepcionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("DELETE FROM cliente WHERE dni = '%s'", dni);
            this.stmt.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExcepcionCliente("No se ha podido realizar la eliminación");
        }
    }

    public void editPersona(ClienteVO clienteVO) throws ExcepcionCliente {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format(
                    "UPDATE cliente SET nombre = '%s', apellidos = '%s', direccion = '%s', localidad = '%s', provincia = '%s' WHERE dni = '%s'",
                    clienteVO.getFirstName(), clienteVO.getLastName(), clienteVO.getStreet(), clienteVO.getCity(), clienteVO.getState(), clienteVO.getDNI()
            );
            this.stmt.executeUpdate(sql);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExcepcionCliente("No se ha podido realizar la edición");
        }
    }
}
