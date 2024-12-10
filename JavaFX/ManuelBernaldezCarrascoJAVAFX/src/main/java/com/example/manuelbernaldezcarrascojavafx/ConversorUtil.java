package com.example.manuelbernaldezcarrascojavafx;

import Modelo.MonedaVO;

import java.util.ArrayList;

public class ConversorUtil {



    public static ArrayList<Moneda> ToMoneda(ArrayList<MonedaVO> lista) {
        if (lista == null) {
            return null;
        } else {
            ArrayList<Moneda> monedas = new ArrayList<>();
            for (MonedaVO monedaVO : lista) {
                monedas.add(new Moneda(monedaVO.getNombre(), monedaVO.getMultiplicador(), monedaVO.getCodigo()));
            }
            return monedas;
        }
    }
    // cambiará una Reserva a ReservaVO
    public static MonedaVO ToMonedaVO(Moneda moneda) {
        MonedaVO monedaVO= new MonedaVO(moneda.getNombre(),moneda.getMultiplicador(),moneda.getCodigo());
        return monedaVO;
    }
}