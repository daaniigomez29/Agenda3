package ch.makery.address.model.repository;

import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {

    /**
     * Obtiene la lista de personas que existen en la base de datos.
     * @return Array de personasVO.
     * @throws ExcepcionPerson Lanza excepción al ocurrir un error en la base de datos.
     * @author Daniel Gómez
     */
    ArrayList<PersonVO> obtenerListaPersonas() throws ExcepcionPerson;

    /**
     * Añade una persona a la base de datos.
     * @param p1 objeto PersonVO a añadir.
     * @throws ExcepcionPerson Lanza excepcion si ocurre un error en la base de datos.
     * @author Daniel Gómez
     */
    void addPerson(PersonVO p1) throws ExcepcionPerson;

    /**
     * Elimina una persona de la base de datos.
     * @param cod id de la persona que va a ser eliminada.
     * @throws ExcepcionPerson Lanza excepcion si ocurre un error en la base de datos.
     * @author Daniel Gómez
     */
    void deletePerson(Integer cod) throws ExcepcionPerson;

    /**
     * Edita una persona de la base de datos.
     * @param p1 Objeto persona que queremos editar.
     * @param cod Id de la persona que queremos editar.
     * @throws ExcepcionPerson Lanza excepcion si ocurre un error en la base de datos.
     * @author Daniel Gómez
     */
    void editPerson(PersonVO p1, Integer cod) throws ExcepcionPerson;

    /**
     * Obtiene el último id de la tabla Personas.
     * @return último id de la tabla.
     * @throws ExcepcionPerson Lanza excepcion si ocurre un error en la base de datos.
     * @author Daniel Gómez
     */
    int lastId() throws ExcepcionPerson;
}
