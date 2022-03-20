package edu.uqtr.demoobs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Gestionnaire de l'application Pizzardo.
 */
public class PizzardoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PizzardoApplication.class.getResource("preparation-commandes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080 , 720);
        stage.setTitle("Préparation des commandes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}