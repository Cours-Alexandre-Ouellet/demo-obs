package edu.uqtr.demoobs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Contrôleur de l'interface de préparation de commande.
 */
public class PreparationCommandeController {

    /**
     * Liste des cuisiniers dans le système.
     */
    private ArrayList<Cuisinier> cuisiniers;

    /**
     * Liste des commandes dans le système.
     */
    private ObservableList<Commande> commandes;

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

    /**
     * Affichage de la liste des commandes.
     */
    @FXML
    private ListView<Commande> listeCommandes;

    /**
     * Affichage du numéro de la commande.
     */
    @FXML
    private TextField numeroCommande;

    /**
     * Affichage du temps de réception de la commande.
     */
    @FXML
    private TextField champTempsEcoule;

    /**
     * Affichage de la liste des items du menu dans la commande.
     */
    @FXML
    private ListView<ItemMenu> listeItemsMenu;

    /**
     * Affichage de la liste des cuisiniers.
     */
    @FXML
    private ListView<Cuisinier> listeCuisiniers;

    /**
     * Indique l'avancement de la commande vers sont état de complétion.
     */
    @FXML
    private ProgressBar avancementCommande;

    /**
     * [Exercice 1] On a besoin d'une référence sur la commande active
     */
    private Commande commandeActive;

    /**
     * Crée un nouveau contrôleur de gestion des commandes.
     */
    public PreparationCommandeController() {
        cuisiniers = new ArrayList<>();
        commandes = DonneesCommandes.getListeCommandes();

        genererDonnees();
    }

    /**
     * Crée la liste des cuisiniers
      */
    private void genererDonnees() {
        // Liste de cuisiniers
        cuisiniers.add(new Cuisinier("Véronique"));
        cuisiniers.add(new Cuisinier("Charles"));
        cuisiniers.add(new Cuisinier("Élizabeth"));
    }

    /**
     * Initialise les données de l'interface après création des objets JavaFX.
     */
    public void initialize() {
        // Ajout du listener pour le changement de thème
        theme.selectedToggleProperty().addListener(new ChangementThemeListener(racine));

        // Peuplement de la liste des commandes et des cuisiniers
        listeCommandes.getItems().addAll(commandes);
        listeCuisiniers.getItems().addAll(cuisiniers);

        // Observateur sur la liste des commandes
        listeCommandes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {
            @Override
            public void changed(ObservableValue<? extends Commande> observable, Commande oldValue, Commande newValue) {
                afficherCommande(newValue);
            }
        });

        // Avancement de la commande
        avancementCommande.setProgress(0.0);

        // Observateur sur la liste des cuisiniers
        listeCuisiniers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cuisinier>() {
            @Override
            public void changed(ObservableValue<? extends Cuisinier> observable, Cuisinier oldValue, Cuisinier newValue) {
                commandeActive.setResponsable(newValue);

                // Rafraîchissement de l'affichage des commandes
                listeCommandes.refresh();
            }
        });

        // On change la fabrique pour les cellules de la commande
        listeCommandes.setCellFactory(new FabriqueCelluleCommande());

        // Affichage par défaut
        afficherPremiereCommande();

    }

    /**
     * Affiche la première commande si elle existe.
     */
    @FXML
    private void afficherPremiereCommande() {

        if (listeCommandes.getItems().size() > 0) {
            Commande premiereCommande = listeCommandes.getItems().get(0);

            // Sélection dans la liste et mise à jour de l'affichage
            listeCommandes.getSelectionModel().select(premiereCommande);
            afficherCommande(premiereCommande);
        }
    }

    /**
     * Marque une commande comme terminée et met à jour la liste.
     *
     * @param event l'événement
     */
    @FXML
    private void terminerCommande(ActionEvent event) {
        if (commandeActive == null) {
            return;
        }

        commandeActive.setTerminee(true);
        listeCommandes.getItems().remove(commandeActive);
        afficherPremiereCommande();
    }

    /**
     * Met à jour les champs pour afficher le contenu d'une commande.
     *
     * @param commande la commande à afficher.
     */
    private void afficherCommande(Commande commande) {
        // [Exercice 1] Mise à jour de la commande active
        commandeActive = commande;

        // [Exercice 4] Évite un null pointer exception
        if(commande == null) {
            listeItemsMenu.getItems().clear();
            numeroCommande.setText("");
            champTempsEcoule.setText("");
            return;
        }

        // Affichage des champs
        numeroCommande.setText(commande.toString());

        // Trouve le temps entre maintenant et la réception de la commande
        Calendar tempsEcoule = Calendar.getInstance();
        tempsEcoule.setTimeInMillis(Calendar.getInstance().getTimeInMillis()
                - commande.getReception().getTimeInMillis());

        champTempsEcoule.setText(formatterTemps(tempsEcoule.get(Calendar.HOUR_OF_DAY)) + " : " +
                formatterTemps(tempsEcoule.get(Calendar.MINUTE)));

        // Remplissage des items du menu
        listeItemsMenu.getItems().clear();
        listeItemsMenu.getItems().addAll(commande.getItems());

        // Sélectionne le cuisinier actif
        listeCuisiniers.getSelectionModel().select(commandeActive.getResponsable());
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

    /**
     * Formatte l'affichage du temps pour ajouter un 0 devant si la valeur est inférieur à 10.
     *
     * @param temps le nombre à formatter.
     * @return la valeur du temps en format de chaîne de caractères.
     */
    private static String formatterTemps(int temps) {
        if (temps < 10) {
            return "0" + temps;
        }

        return Integer.toString(temps);
    }



}