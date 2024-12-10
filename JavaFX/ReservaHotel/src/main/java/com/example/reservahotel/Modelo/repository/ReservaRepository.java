package com.example.reservahotel.Modelo.repository;

import com.example.reservahotel.Modelo.ReservaVO;
import java.util.ArrayList;

public interface ReservaRepository {




    // Método modificado para obtener las reservas por DNI del cliente
    ArrayList<ReservaVO> obtenerReservaCliente(String dni_cliente) throws ExcepcionReserva;

    void addReserva(ReservaVO reserva) throws ExcepcionReserva;

    void deleteReserva(int id) throws ExcepcionReserva;

    void editReserva(ReservaVO reserva) throws ExcepcionReserva;
}
