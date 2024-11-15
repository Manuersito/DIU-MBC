package com.example.reservahotel.Modelo.repository;

import com.example.reservahotel.Modelo.ReservaVO;
import java.util.ArrayList;

public interface ReservaRepository {
    ArrayList<ReservaVO> obtenerListaReservas() throws ExcepcionReserva;
    ArrayList<ReservaVO> obtenerReservaCliente() throws ExcepcionReserva;


    void addReserva(ReservaVO reserva) throws ExcepcionReserva;

    void deleteReserva(int id) throws ExcepcionReserva;

    void editReserva(ReservaVO reserva) throws ExcepcionReserva;
}
