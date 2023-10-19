package ch.makery.address.controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import ch.makery.address.MainApp;

    /**
     * The controller for the root layout. The root layout provides the basic
     * application layout containing a menu bar and space where other JavaFX
     * elements can be placed.
     *
     * @author Marco Jakob
     */
    public class RootLayoutController {

        // Reference to the main application
        private MainApp mainApp;

        /**
         * Is called by the main application to give a reference back to itself.
         *
         * @param mainApp
         */
        public void setMainApp(MainApp mainApp) {
            this.mainApp = mainApp;
        }

        @FXML
        private void handleShowBirthdayStatistics() {
            mainApp.showBirthdayStatistics();
        }

}
