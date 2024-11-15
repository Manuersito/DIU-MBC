package com.example.reservahotel.Modelo;

import com.example.reservahotel.Modelo.repository.ClientRepository;
import com.example.reservahotel.Cliente;
import com.example.reservahotel.Modelo.repository.ExcepcionCliente;
import com.example.reservahotel.Modelo.repository.ExcepcionReserva;
import com.example.reservahotel.Modelo.repository.impl.ClientRepositoryImpl;
import com.example.reservahotel.Modelo.repository.impl.ReservaRepositoryImpl;
import com.example.reservahotel.Reserva;
import com.example.reservahotel.Util.ClientUtil;
import com.example.reservahotel.Util.ReservaUtil;

import java.util.ArrayList;

public class ModeloHotel {
    ArrayList<Cliente> clientes;
    ArrayList<Reserva> reservas;
    ArrayList<ClienteVO> clientesVO;
    ArrayList<ReservaVO> reservasVO;
    ClientRepositoryImpl clientRepository = new ClientRepositoryImpl();
    ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
    ReservaUtil reservaUtil;
    ClientUtil clientUtil;
    private final int MAX_PERSONS = 50;

    public ModeloHotel() {
        this.clientUtil = new ClientUtil();
        this.reservaUtil = new ReservaUtil();
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


    public ArrayList<Reserva> mostrarReservas(String dni_cliente) {
        try {
            reservasVO = reservaRepository.obtenerReservaCliente(dni_cliente);
            reservas = reservaUtil.fromReservaVOListToReservaList(reservasVO);
        } catch (ExcepcionReserva e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public ArrayList<ReservaVO> getReservas(String dni) throws ExcepcionCliente {
        return reservaRepository.obtenerReservaCliente(dni);
    }


}
