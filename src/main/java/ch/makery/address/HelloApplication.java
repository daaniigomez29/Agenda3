package ch.makery.address;

import ch.makery.address.controller.PersonOverviewController;
import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.model.repository.impl.PersonRepositoryImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        try{
        String fecha = "20/12/2023";
            DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate f = LocalDate.parse(fecha, d);
            PersonVO p1 = new PersonVO("Manolo", "si", "no", 111, "Sevilla", f);

            PersonRepositoryImpl im = new PersonRepositoryImpl();


            im.editPerson(p1, 2);
        } catch (ExcepcionPerson e){
            e.imprimirMensaje();
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}