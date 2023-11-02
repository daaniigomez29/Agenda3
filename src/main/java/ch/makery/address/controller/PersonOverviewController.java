package ch.makery.address.controller;

import ch.makery.address.MainApp;
import ch.makery.address.model.Agenda;
import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import ch.makery.address.util.ConversonPerson;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    Button btnDelete;
    private MainApp mainApp;
    Agenda agenda;
    ConversonPerson conversonPerson;
    PersonVO personVO;
    public  PersonOverviewController(){
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));

        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    public void deletePerson() {
        Person person;
        person = personTable.getSelectionModel().getSelectedItem();
        int index = personTable.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            personTable.getItems().remove(index);
            try{
                agenda.eliminarPersona(person.getId());
            } catch (ExcepcionPerson e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No eliminada");
                alert.setHeaderText("Persona no eliminada");
                alert.setContentText("Error al eliminar una persona");
                alert.showAndWait();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sin seleccionar");
            alert.setHeaderText("Persona no seleccionada");
            alert.setContentText("Por favor seleccione a una persona de la tabla");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() throws ExcepcionPerson {
        Person tempPerson = new Person();
        conversonPerson = new ConversonPerson();
        PersonVO personVO = new PersonVO();

        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            try {
                mainApp.getPersonData().add(tempPerson);
                personVO = conversonPerson.convertirPersonaVO(tempPerson);
                agenda.crearPersona(personVO);
                tempPerson.setId(agenda.obtenerUltimoId());
            } catch (ExcepcionPerson e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No creada");
                alert.setHeaderText("Persona no creada");
                alert.setContentText("Error al crear una persona");
                alert.showAndWait();
            }
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        personVO = new PersonVO();
        conversonPerson = new ConversonPerson();
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                try {
                    personVO = conversonPerson.convertirPersonaVO(selectedPerson);
                    agenda.editarPersona(personVO);
                    showPersonDetails(selectedPerson);
                } catch (ExcepcionPerson e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("No editada");
                    alert.setHeaderText("Persona no editada");
                    alert.setContentText("Error al editar una persona");
                    alert.showAndWait();
                }
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No Seleccion");
                    alert.setHeaderText("Persona no seleccionada");
                    alert.setContentText("Por favor selecciona a una persona de la tabla.");
                    alert.showAndWait();
        }
    }



    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }
}
