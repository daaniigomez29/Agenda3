package ch.makery.address.model.repository;

import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> obtenerListaPersonas() throws ExcepcionPerson;
    void addPerson(PersonVO p1) throws ExcepcionPerson;
    void deletePerson(Integer cod) throws ExcepcionPerson;
    void editPerson(PersonVO p1, Integer cod) throws ExcepcionPerson;
    int lastId() throws ExcepcionPerson;
}
