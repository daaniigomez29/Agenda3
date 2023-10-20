package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ConversonPerson {
    public ConversonPerson() {
    }

    public Person convertirPersona(PersonVO personVO){
        Person person = new Person();
        person.setFirstName(personVO.getFirstName());
        person.setLastName(personVO.getLastName());
        person.setStreet(personVO.getStreet());
        person.setPostalCode(personVO.getPostalCode());
        person.setCity(personVO.getCity());
        person.setBirthday(personVO.getBirthday());
        return person;
    }

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
