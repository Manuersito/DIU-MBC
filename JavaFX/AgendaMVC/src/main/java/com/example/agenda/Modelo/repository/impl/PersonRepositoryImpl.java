package com.example.agenda.Modelo.repository.impl;

import com.example.agenda.Modelo.ExcepcionPerson;
import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Modelo.repository.PersonRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PersonRepositoryImpl implements PersonRepository{
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    public PersonRepositoryImpl() {
    }

    public ArrayList<PersonVO> ObtenerListaPersonas() throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM person";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String f = rs.getString("firstName");
                String l = rs.getString("lastName");
                String st = rs.getString("street");
                String c = rs.getString("city");
                Integer pc = rs.getInt("postalCode");
                LocalDate bd = rs.getDate("birthday").toLocalDate();
                Integer i = rs.getInt("Id");
                this.persona = new PersonVO(f, l, st, c, pc, bd);
                this.persona.setId(i);
                this.personas.add(this.persona);
            }

            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    public void addPersona(PersonVO m) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO person (firstName, lastName, street, city, postalCode, birthday) VALUES ('" + m.getFirstName() + "','" + m.getLastName() + "','" + m.getStreet() + "','" + m.getCity() + "','" + m.getPostalCode() + "','" + m.getBirthday() +"');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionPerson("No se ha podido realizar la operación");
        }
    }

    public void deletePersona(Integer idPersona) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM person WHERE Id = %d", idPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido realizar la eliminación");
        }
    }

    public void editPersona(PersonVO personVO) throws ExcepcionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format( "UPDATE person SET firstName = '%s', lastName = '%s', street = '%s', city = '%s', postalCode = %d, birthday = '%s' WHERE Id = %d", personVO.getFirstName(), personVO.getLastName(), personVO.getStreet(), personVO.getCity(), personVO.getPostalCode(), personVO.getBirthday(), personVO.getId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionPerson("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExcepcionPerson {
        int lastPersonaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT Id FROM person ORDER BY Id DESC LIMIT 1"); registro.next(); lastPersonaId = registro.getInt("Id")) {
            }

            return lastPersonaId;
        } catch (SQLException var5) {
            throw new ExcepcionPerson("No se ha podido realizar la busqueda del ID");
        }
    }
}
