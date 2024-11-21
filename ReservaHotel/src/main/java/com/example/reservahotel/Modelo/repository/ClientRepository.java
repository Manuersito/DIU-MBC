package com.example.reservahotel.Modelo.repository;

import com.example.reservahotel.Modelo.ClienteVO;

import java.util.ArrayList;

public interface ClientRepository {
    ArrayList<ClienteVO> ObtenerListaPersonas() throws ExcepcionCliente;

    void addPersona(ClienteVO var1) throws ExcepcionCliente;

    void deletePersona(String var1) throws ExcepcionCliente;

    void editPersona(ClienteVO var1) throws ExcepcionCliente;

}
