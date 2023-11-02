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
    DoubleProperty nLista = new SimpleDoubleProperty(); //Numero total de personas
    IntegerProperty n = new SimpleIntegerProperty(); //Numero Label porcentaje

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

    public void incrementarN() throws ExcepcionPerson {
       double antiguoValor = nLista.get();
       antiguoValor += 1;
       nLista.set(antiguoValor);
       n.set((int)antiguoValor);
    }

    public void setProgreso(int tamano, ProgressBar pb, Label porcentaje){
        n = new SimpleIntegerProperty(tamano);
        nLista = new SimpleDoubleProperty();
        porcentaje.setText(String.valueOf(n.getValue()) + "/50");
        pb.progressProperty().bind(nLista);
        nLista.set((double) tamano/50);
    }

    public void setImpl(PersonRepositoryImpl impl) {
        this.impl = impl;
    }
}
