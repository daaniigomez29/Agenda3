package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;

import java.util.ArrayList;

public class Agenda {
    PersonRepository impl;

    public Agenda(){

    }

    public ArrayList<PersonVO> listarPersonas() throws ExcepcionPerson {
            return impl.obtenerListaPersonas();
    }

    public void crearPersona(PersonVO personVO) throws ExcepcionPerson{
        impl.addPerson(personVO);
    }

    public void editarPersona(PersonVO personVO) throws  ExcepcionPerson {
        impl.editPerson(personVO, personVO.getCod());
    }

    public void eliminarPersona(int id) throws ExcepcionPerson {
        impl.deletePerson(id);
    }

    public int obtenerUltimoId() throws ExcepcionPerson {
        return impl.lastId();
    }

    public void setImpl(PersonRepositoryImpl impl) {
        this.impl = impl;
    }
}
