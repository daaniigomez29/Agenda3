package ch.makery.address.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class PersonVO {
        Integer cod;
        private String firstName;
        private String lastName;
        private String street;
        private Integer postalCode;
        private String city;
        private LocalDate birthday;
        private int id;


        /**
         * Constructor predeterminado.
         */
    public PersonVO(){

    }

    /**
     * Constructor con datos
     * @param id id de la persona
     * @param firstName Nombre
     * @param lastName Apellidos
     * @param street Calle
     * @param postalCode Código postal
     * @param city Ciudad
     * @param birthday Cumpleaños
     * @author Daniel Gómez
     */
        public PersonVO(int id, String firstName, String lastName, String street, Integer postalCode, String city, LocalDate birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.birthday = birthday;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
