package ch.makery.address.model;

import javafx.scene.control.Alert;

public class ExcepcionPerson extends Exception{
    private String mensaje;
    Alert alert;

    public ExcepcionPerson() {
    }

    public ExcepcionPerson(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }

    public ExcepcionPerson(Alert alert){
        this.alert = alert;
    }

}
