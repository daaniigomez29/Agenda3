package ch.makery.address.util;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ConversonPerson {

    ArrayList<Person> listaConvertida;
public ObservableList<Person> ConversonPerson(ObservableList<Person> person, ArrayList<PersonVO> personVO){
    for (int i=0;i<personVO.size();i++){
        person.get(i).setFirstName(personVO.get(i).getFirstName());
        person.get(i).setLastName(personVO.get(i).getLastName());
        person.get(i).setStreet(personVO.get(i).getStreet());
        person.get(i).setPostalCode(personVO.get(i).getPostalCode());
        person.get(i).setCity(personVO.get(i).getCity());
        person.get(i).setBirthday(personVO.get(i).getBirthday());
    }
    return person;
}

}
