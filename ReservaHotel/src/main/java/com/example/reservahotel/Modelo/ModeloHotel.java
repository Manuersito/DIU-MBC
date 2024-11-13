package com.example.reservahotel.Modelo;

import com.example.reservahotel.Modelo.repository.ClientRepository;
import com.example.reservahotel.Cliente;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Modelo.repository.impl.ClientRepositoryImpl;
import com.example.reservahotel.Reserva;
import com.example.reservahotel.Util.ClientUtil;

import java.util.ArrayList;

public class ModeloHotel {
    ArrayList<Cliente> clientes;
    ArrayList<Reserva> reservas;
    ArrayList<ClienteVO> clientesVO;
    ArrayList<ReservaVO> reservasVO;
    ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();

    ClientUtil clientUtil;
    private final int MAX_PERSONS = 50;

    public ModeloHotel() {
        this.clientUtil = new ClientUtil();
    }

    public void setPersonRepository(ClientRepositoryImpl clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ArrayList<ClienteVO> getPersons() throws ExcepcionCliente {

        ArrayList<ClienteVO> persons = clientRepository.ObtenerListaPersonas();

        return persons;
    }


    public ArrayList<Cliente> mostrar() throws ExcepcionCliente {
        ArrayList<ClienteVO> listaClienteVO=getPersons();
        ArrayList<Cliente> listaCliente=new ArrayList<>();
        listaCliente=ClientUtil.getClientes(listaClienteVO);

        return listaCliente;
    }

    public void nuevoPerson(ClienteVO clienteVO) throws ExcepcionCliente {
        clientRepository.addPersona(clienteVO);
    }

    public void actualizarPerson(ClienteVO personVO) throws ExcepcionCliente {
        clientRepository.editPersona(personVO);
    }

    public void eliminarPerson(String personId) throws ExcepcionCliente {
        clientRepository.deletePersona(personId);
    }



}
