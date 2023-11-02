package ch.makery.address.controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;

    /**
     * El controlador para el root layout. Root layout proporciona el layout básico de la aplicación,
     * contiene un menu bar y espacio donde otros elementos de JavaFX pueden ser añadidos.
     * @author Daniel Gómez
     */
    public class RootLayoutController {

        // Referencia a la aplicación principal
        private MainApp mainApp;

        /**
         * Es llamado por la aplicación principal para dar una referencia a si mismo.
         * @param mainApp
         * @author Daniel Gómez
         */
        public void setMainApp(MainApp mainApp) {
            this.mainApp = mainApp;
        }

        @FXML
        private void handleShowBirthdayStatistics() {
            mainApp.showBirthdayStatistics();
        }

}
