package com.example.reservahotel.Util;

import com.example.reservahotel.Cliente;

import com.example.reservahotel.Modelo.ClienteVO;
import com.example.reservahotel.Modelo.ReservaVO;
import com.example.reservahotel.Modelo.repository.impl.regimen;
import com.example.reservahotel.Modelo.repository.impl.tipo_hab;
import com.example.reservahotel.Reserva;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaUtil {

    public  Reserva fromReservaVOToReserva(ReservaVO reservaVO) {
        if (reservaVO == null) {
            return null;
        }

        return new Reserva(
                reservaVO.getId(),
                reservaVO.getFechaEntrada(),  // Convierte LocalDate a String
                reservaVO.getFechaSalida(),   // Convierte LocalDate a String
                reservaVO.getNumHabitaciones(),
                reservaVO.getTipoHabitacion().toString(),
                reservaVO.getRegimen().toString(),
                reservaVO.isFumador(),
                reservaVO.getDniCliente()
        );
    }

    /**
     * Convierte un objeto Reserva a un objeto ReservaVO.
     *
     * @param reserva El objeto Reserva a convertir.
     * @return Un objeto ReservaVO.
     */
    public ReservaVO fromReservaToReservaVO(Reserva reserva) {
        int id = reserva.getId();
        LocalDate fechaLlegada = (reserva.getFechaEntrada());
        LocalDate fechaSalida = (reserva.getFechaSalida());
        tipo_hab tipoHabitacionEnum = tipo_hab.valueOf(reserva.getTipoHabitacion().toUpperCase().replace(" ", "_"));
        int nHabitaciones = reserva.getNumHabitaciones();
        boolean fumador = reserva.isFumador();
        regimen regimenEnum = regimen.valueOf(reserva.getRegimen().toUpperCase());
        String dniCliente = reserva.getDniCliente();

        return new ReservaVO(id, fechaLlegada, fechaSalida, nHabitaciones, tipoHabitacionEnum, regimenEnum, fumador, dniCliente);
    }

    public  ArrayList<Reserva> fromReservaVOListToReservaList(ArrayList<ReservaVO> reservaVOList) {
        ArrayList<Reserva> reservaList = new ArrayList<>();
        if (reservaVOList != null) {
            for (ReservaVO reservaVO : reservaVOList) {
                reservaList.add(fromReservaVOToReserva(reservaVO));
            }
        }
        return reservaList;
    }

    public  ArrayList<ReservaVO> fromReservaListToReservaVOList(ArrayList<Reserva> reservaList) {
        ArrayList<ReservaVO> reservaVOList = new ArrayList<>();
        if (reservaList != null) {
            for (Reserva reserva : reservaList) {
                reservaVOList.add(fromReservaToReservaVO(reserva));
            }
        }
        return reservaVOList;
    }


    public static ArrayList<Reserva> getReservas(ArrayList<ReservaVO> reservaVOList) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        for (ReservaVO reservaVO : reservaVOList) {
            Reserva reserva = new Reserva();
            reserva.setId(reservaVO.getId());
            reserva.setFechaEntrada(reservaVO.getFechaEntrada());
            reserva.setFechaSalida(reservaVO.getFechaSalida());
            reserva.setNumHabitaciones(reservaVO.getNumHabitaciones());
            reserva.setTipoHabitacion(reservaVO.getTipoHabitacion().toString());
            reserva.setRegimen(reservaVO.getRegimen().toString());
            reserva.setFumador(reservaVO.isFumador());
            reserva.setDniCliente(reservaVO.getDniCliente());

            reservas.add(reserva);
        }
        return reservas;
    }

    public ArrayList<ReservaVO> getReservasVO(ArrayList<Reserva> listaReservas) {
        ArrayList<ReservaVO> listaReservasVO = new ArrayList<>();
        for (Reserva reserva : listaReservas) {
            ReservaVO reservaVO = convertirReservaAReservaVO(reserva);
            listaReservasVO.add(reservaVO);
        }
        return listaReservasVO;
    }

    // Método para convertir un objeto Reserva a un objeto ReservaVO
    public ReservaVO convertirReservaAReservaVO(Reserva reserva) {
        int id = reserva.getId();
        LocalDate fechaLlegada = reserva.getFechaEntrada();
        LocalDate fechaSalida = reserva.getFechaSalida();
        tipo_hab tipoHabitacionEnum = tipo_hab.valueOf(reserva.getTipoHabitacion().toUpperCase().replace(" ", "_"));
        int nHabitaciones = reserva.getNumHabitaciones();

        regimen regimenEnum = regimen.valueOf(reserva.getRegimen().toUpperCase());
        boolean fumador = reserva.isFumador();
        String dniCliente = reserva.getDniCliente();

        return new ReservaVO(id, fechaLlegada, fechaSalida, nHabitaciones, tipoHabitacionEnum, regimenEnum,fumador, dniCliente);
    }

}
