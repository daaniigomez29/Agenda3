package ch.makery.address.model;

public class ExcepcionPerson extends Exception{
    private String mensaje;

    public ExcepcionPerson() {
    }

    public ExcepcionPerson(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
