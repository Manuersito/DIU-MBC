package com.example.manuelbernaldezcarrascojavafx.Modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import com.example.manuelbernaldezcarrascojavafx.Moneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.manuelbernaldezcarrascojavafx.ConversorUtil;

import java.util.ArrayList;

public class ConversoModelo {


    ArrayList<MonedaVO> monedas = new ArrayList<>();
    private MonedaRepositoryImpl monedaRepository;

    public ArrayList<Moneda> setMonedas() throws ExcepcionMoneda {
        monedas= monedaRepository.ObtenerListaMonedas();
        return ConversorUtil.ToMoneda(monedas);
    }

    public void setMonedaRepository(MonedaRepositoryImpl monedaRepository) throws ExcepcionMoneda {

        this.monedaRepository = monedaRepository;
    }



    public void eliminarMoneda(int codigo) throws ExcepcionMoneda {
        monedaRepository.deleteMoneda(codigo);
    }
}
