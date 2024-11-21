package com.example.reservahotel.Util;

import com.example.reservahotel.Cliente;

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
                reservaVO.getFechaEntrada().toString(),  // Convierte LocalDate a String
                reservaVO.getFechaSalida().toString(),   // Convierte LocalDate a String
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
        LocalDate fechaLlegada = LocalDate.parse(reserva.getFechaEntrada());
        LocalDate fechaSalida = LocalDate.parse(reserva.getFechaSalida());
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

}
