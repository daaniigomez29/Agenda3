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
    DoubleProperty nLista = new SimpleDoubleProperty();
    DoubleProperty n = new SimpleDoubleProperty();

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

    public void incrementarN() throws ExcepcionPerson {
       double antiguoValor = nLista.get();
       antiguoValor += 1;
       nLista.set(antiguoValor);
    }

    public void setProgreso(int tamano, ProgressBar pb, Label porcentaje){
        DoubleProperty tam = new SimpleDoubleProperty(tamano);
        nLista = new SimpleDoubleProperty();
        porcentaje.setText(String.valueOf(tam.getValue()) + "/50");
        pb.progressProperty().bind(nLista);
        nLista.set((double) tamano/50);
    }

    public void setImpl(PersonRepositoryImpl impl) {
        this.impl = impl;
    }
}
