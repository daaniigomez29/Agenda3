package ch.makery.address.model;

import ch.makery.address.model.repository.impl.PersonRepositoryImpl;

import java.util.ArrayList;

public class Agenda {
    PersonRepositoryImpl impl;
    private ArrayList<PersonVO> personas;
    public Agenda(){

    }

    private ArrayList<PersonVO> listaPersonas() throws ExcepcionPerson {
   personas = impl.obtenerListaPersonas();
   return personas;
    }
}
