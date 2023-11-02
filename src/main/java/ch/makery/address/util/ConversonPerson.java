package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ConversonPerson {

    //Contructor predeterminado
    public ConversonPerson() {
    }

    /**
     * Convierte un objeto PersonVO a un objeto Person
     * @param personVO Persona a convertir
     * @return objeto Person
     * @author Daniel Gómez
     */
    public Person convertirPersona(PersonVO personVO){
        Person person = new Person();
        person.setId(personVO.getCod());
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setStreet(personVO.getStreet());
        person.setPostalCode(personVO.getPostalCode());
        person.setCity(personVO.getCity());
        person.setBirthday(personVO.getBirthday());
        return person;
    }
    /**
     * Convierte un objeto Person a un objeto PersonVO
     * @param person Persona a convertir
     * @return objeto PersonVO
     * @author Daniel Gómez
     */
    public PersonVO convertirPersonaVO(Person person){
        PersonVO personVO = new PersonVO();
        personVO.setCod(person.getId());
        personVO.setFirstName(person.getFirstName());
        personVO.setLastName(person.getLastName());
        personVO.setStreet(person.getStreet());
        personVO.setPostalCode(person.getPostalCode());
        personVO.setCity(person.getCity());
        personVO.setBirthday(person .getBirthday());
        return personVO;
    }

    /**
     * Convierte la lista de objetos PersonVO a una lista de objetos Person
     * @param listaPersonVO
     * @return lista de objetos Person
     * @author Daniel Gómez
     */
    public ArrayList<Person> convertirLista(ArrayList<PersonVO> listaPersonVO){
        ArrayList<Person> listaPerson = new ArrayList<Person>();
        Person person = new Person();
        for(int i=0;i<listaPersonVO.size();i++){
           person = convertirPersona(listaPersonVO.get(i));
           listaPerson.add(i, person);
        }
      return listaPerson;
    }
}
