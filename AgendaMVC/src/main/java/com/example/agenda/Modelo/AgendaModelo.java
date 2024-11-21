package com.example.agenda.Modelo;

import com.example.agenda.Modelo.repository.PersonRepository;
import com.example.agenda.Modelo.repository.impl.ConexionJDBC;
import com.example.agenda.Modelo.repository.impl.PersonRepositoryImpl;
import com.example.agenda.Person;
import com.example.agenda.util.PersonUtil;

import java.util.ArrayList;

public class AgendaModelo {

PersonRepository personRepository = new PersonRepositoryImpl();
PersonUtil personUtil;
private final int MAX_PERSONS = 50;

public AgendaModelo() {
    this.personUtil = new PersonUtil();
}

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ArrayList<PersonVO> getPersons() throws ExcepcionPerson{

        ArrayList<PersonVO> persons = personRepository.ObtenerListaPersonas();

        return persons;
    }


    public ArrayList<Person> mostrar() throws ExcepcionPerson {
        ArrayList<PersonVO> listaPersonsVO=getPersons();
        ArrayList<Person> listaPersonas=new ArrayList<>();
        listaPersonas=personUtil.getPersonas(listaPersonsVO);

        return listaPersonas;
    }

    public void nuevoPerson(PersonVO personVO) throws ExcepcionPerson {
        personRepository.addPersona(personVO);
    }

    public void actualizarPerson(PersonVO personVO) throws ExcepcionPerson {
        personRepository.editPersona(personVO);
    }

    public void eliminarPerson(int personId) throws ExcepcionPerson {
        personRepository.deletePersona(personId);
    }



}
