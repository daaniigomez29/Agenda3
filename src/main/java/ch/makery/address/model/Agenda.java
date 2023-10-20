package ch.makery.address.model;

import ch.makery.address.model.repository.impl.PersonRepositoryImpl;

import java.util.ArrayList;

public class Agenda {
    PersonRepositoryImpl impl;
    ArrayList<PersonVO> lista;

    public Agenda(){

    }

    public ArrayList<PersonVO> listarPersonas() throws ExcepcionPerson {
        return impl.obtenerListaPersonas();
    }



    public void setImpl(PersonRepositoryImpl impl) {
        this.impl = impl;
    }
}
