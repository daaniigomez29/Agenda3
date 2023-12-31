package ch.makery.address.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {
        private final StringProperty firstName;
        private final StringProperty lastName;
        private final StringProperty street;
        private final IntegerProperty postalCode;
        private final StringProperty city;
        private final ObjectProperty<LocalDate> birthday;
        private int id;

        /**
         * Constructor predeterminado.
         */
        public Person() {
            this(null, null);
        }

    /**
     * Constructor con datos.
     * @param firstName Nombre
     * @param lastName Apellidos
     * @author Daniel Gómez
     */
    public Person(String firstName, String lastName) {
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);

            // Some initial dummy data, just for convenient testing.
            this.street = new SimpleStringProperty("some street");
            this.postalCode = new SimpleIntegerProperty(12345);
            this.city = new SimpleStringProperty("some city");
            this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 3, 21));
        }

        public int getId(){
            return id;
        }

        public void setId(int id) {
        this.id = id;
        }

    public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String firstName) {
            this.firstName.set(firstName);
        }

        public StringProperty firstNameProperty() {
            return firstName;
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String lastName) {
            this.lastName.set(lastName);
        }

        public StringProperty lastNameProperty() {
            return lastName;
        }

        public String getStreet() {
            return street.get();
        }

        public void setStreet(String street) {
            this.street.set(street);
        }

        public StringProperty streetProperty() {
            return street;
        }

        public int getPostalCode() {
            return postalCode.get();
        }

        public void setPostalCode(int postalCode) {
            this.postalCode.set(postalCode);
        }

        public IntegerProperty postalCodeProperty() {
            return postalCode;
        }

        public String getCity() {
            return city.get();
        }

        public void setCity(String city) {
            this.city.set(city);
        }

        public StringProperty cityProperty() {
            return city;
        }

        public LocalDate getBirthday() {
            return birthday.get();
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday.set(birthday);
        }

        public ObjectProperty<LocalDate> birthdayProperty() {
            return birthday;
        }
}
