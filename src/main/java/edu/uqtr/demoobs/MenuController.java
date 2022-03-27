package edu.uqtr.demoobs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    /**
     * Gère le thème actif dans le menu.
     */
    @FXML
    private ToggleGroup theme;

    /**
     * Élément racine de la fenêtre.
     */
    @FXML
    private Parent racine;

    public void setRacine(Parent racine){
        this.racine = racine;
        theme.selectedToggleProperty().addListener(new ChangementThemeListener(racine));
    }

    /**
     * Ouvre une fenêtre modale pour ajouter une nouvelle commande. La fenêtre reste sur le dessus tant qu'elle
     * n'a pas été résolue.
     */
    @FXML
    private void ajouterCommande() {
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(PizzardoApplication.class.getResource("ajout-commande.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 325, 700);
            stage.setTitle("Ajouter une commande");
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.show();
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Impossible de charger l'interface demandée. Contactez un administrateur.");
            alert.getButtonTypes().add(ButtonType.OK);
        }
    }

}
