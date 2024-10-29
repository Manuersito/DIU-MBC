package com.example.agenda.util;


import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Person;

import java.util.ArrayList;

public class PersonUtil {
    public static ArrayList<Person> getPersonas(ArrayList<PersonVO> personasVO){
        ArrayList<Person> personas = new ArrayList<>();
        for (PersonVO personVO : personasVO) {
            Person person = new Person();

            person.setFirstName(personVO.getFirstName());
            person.setLastName(personVO.getLastName());
            person.setStreet(personVO.getStreet());
            person.setCity(personVO.getCity());
            person.setPostalCode(personVO.getPostalCode());
            person.setBirthday(personVO.getBirthday());

            personas.add(person);
        }
        return personas;
    }

    public static ArrayList<PersonVO> getPersonasVO(ArrayList<Person> personas){
        ArrayList<PersonVO> personasVO = new ArrayList<>();
        for (Person person : personas) {
            PersonVO personVO = new PersonVO(
                    person.getFirstName(),
            person.getLastName(),
            person.getStreet(),
            person.getCity(),
            person.getPostalCode(),
            person.getBirthday()
            );



            personasVO.add(personVO);
        }
        return personasVO;
    }



}
