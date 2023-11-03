package ch.makery.address.model;

import ch.makery.address.model.repository.PersonRepository;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
public class Agenda {
    PersonRepository impl;
    IntegerProperty nLista = new SimpleIntegerProperty(); //Numero total de personas

    //Contructor predeterminado
    public Agenda(){

    }
    //Devuelve la lista de personas obtenidas por el repositorio
    public ArrayList<PersonVO> listarPersonas() throws ExcepcionPerson {
            return impl.obtenerListaPersonas();
    }

    //Crea una persona
    public void crearPersona(PersonVO personVO) throws ExcepcionPerson{
        impl.addPerson(personVO);
    }

    //Edita una persona
    public void editarPersona(PersonVO personVO) throws  ExcepcionPerson {
        impl.editPerson(personVO, personVO.getCod());
    }

    //Elimina una persona
    public void eliminarPersona(int id) throws ExcepcionPerson {
        impl.deletePerson(id);
    }

    //Devuelve el ultimo id de la tabla
    public int obtenerUltimoId() throws ExcepcionPerson {
        return impl.lastId();
    }

    public void incrementarN() {
       int antiguoValor = nLista.get();
       antiguoValor ++;
       nLista.set(antiguoValor);
    }

    public void decrementarN() {
        int antiguoValor = nLista.get();
        antiguoValor --;
        nLista.set(antiguoValor);
    }

    public IntegerProperty nListaProperty() {
        return nLista;
    }

    public void setnLista(int nLista) {
        this.nLista.set(nLista);
    }

    public void setImpl(PersonRepositoryImpl impl) {
        this.impl = impl;
    }
}
