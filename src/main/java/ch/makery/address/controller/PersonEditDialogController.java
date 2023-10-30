package ch.makery.address.controller;

import ch.makery.address.model.Agenda;
import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.util.ConversonPerson;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import org.w3c.dom.Text;

public class PersonEditDialogController {
    int id;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;
    @FXML
    ProgressBar pb;
    @FXML
    Label porcentaje;
    Agenda agenda;
    ConversonPerson conversonPerson;
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;
    private int tamano;

    @FXML
    private void initialize() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            conversonPerson = new ConversonPerson();
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
           }
            okClicked = true;
            dialogStage.close();
        }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage += "Nombre no válido\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Apellido no válido\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Calle no válida\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "Código postal no válido\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código postal no válido (Debe de ser un número)\n";
            }
        }
        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Ciudad no vaĺida\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Fecha de nacimiento no válida\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Fecha de nacimiento no válida. usa el formato dd.mm.yyyy\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid Fields");
                    alert.setHeaderText("Please correct invalid fields");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
            return false;
        }
    }
    public void setAgenda(Agenda agenda){
        this.agenda = agenda;
    }

    public void setPorcentaje(int tamano){
        this.tamano = tamano;
        DoubleProperty progress = new SimpleDoubleProperty();
        progress.set((double) tamano/50);
        porcentaje.setText(String.valueOf(tamano) + "/50");
        pb.setProgress(progress.get());
    }

}
