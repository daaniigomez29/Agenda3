package ch.makery.address;

import ch.makery.address.controller.BirthdayStatisticsController;
import ch.makery.address.controller.PersonEditDialogController;
import ch.makery.address.controller.PersonOverviewController;
import ch.makery.address.controller.RootLayoutController;
import ch.makery.address.model.Agenda;
import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import ch.makery.address.util.ConversonPerson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
   private Agenda agenda = new Agenda(); //Instancia de agenda
    private PersonRepositoryImpl impl = new PersonRepositoryImpl(); //Implementación del repositorio
    ConversonPerson p = new ConversonPerson(); //Instancia para convertir personas.
    private ObservableList<Person> personData = FXCollections.observableArrayList(); //Lista que contiene los datos de todas las personas para la Vista

    /**
     * Añade a la ObervableList todos las personas recogidas del repositorio
     * @author Daniel Gómez
     * @version 1.0
     */
    public MainApp(){
        personData.addAll(addList());
    }

    /**
     * Transforma la lista de PersonVO recogida del repositorio a una lista de Person para añadirla al ObservableList
     * @return listaPerson
     * @author Daniel Gómez
     * @version 1.0
     */
    public ArrayList<Person> addList(){
        agenda.setImpl(impl);
        ArrayList<PersonVO>listaPersonVO = new ArrayList<>();
        ArrayList<Person>listaPerson = new ArrayList<>();
        try{
            listaPersonVO = agenda.listarPersonas();
        } catch (ExcepcionPerson e){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setHeaderText("Error");
          alert.setTitle("Error");
          alert.setContentText("Error al listar las personas.");
          alert.showAndWait();
        }
        listaPerson = p.convertirLista(listaPersonVO);
        return listaPerson;
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }

    /**
     * Inicia la aplicación
     * @param primaryStage
     * @author Daniel Gómez
     * @version 1.0
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        this.primaryStage.getIcons().add(new Image("file:resources/images/agenda.png"));

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Inicia el root layout.
     * @author Daniel Gómez
     * @version 1.0
     */
    public void initRootLayout() {
        try {
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Muestra la ventana donde se encuentra la lista de personas en en root layout
     * Shows the person overview inside the root layout.
     * @author Daniel Gómez
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAgenda(agenda);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Devuelve el main stage.
     * @return primaryStage
     * @author Daniel Gómez
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Comienza el programa
     * @param args
     * @author Daniel Gómez
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Genera la ventana para editar o crear una persona
     * @param person Persona editada o creada
     * @return true/false dependiendo si ha habido exito al crear o editar una persona
     * @author Daniel Gómez
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar persona");
            dialogStage.initModality(Modality.NONE);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            controller.setAgenda(agenda);
            try{
                controller.setPorcentaje(agenda.listarPersonas().size());
            } catch (ExcepcionPerson e){

            }
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Muestra las estadísticas de los cumpleaños
     * @author Daniel Gómez
     */
    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}