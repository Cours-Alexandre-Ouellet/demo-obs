package edu.uqtr.demoobs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Contrôleur de l'interface de préparation de commande.
 */
public class PreparationCommandeController {

    /**
     * Liste des cuisiniers dans le système.
     */
    ArrayList<Cuisinier> cuisiniers;

    /**
     * Liste des commandes dans le système.
     */
    ArrayList<Commande> commandes;

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
     * Crée un nouveau contrôleur de gestion des commandes.
     */
    public PreparationCommandeController() {
        cuisiniers = new ArrayList<>();
        commandes = new ArrayList<>();

        genererDonnees();
    }

    /**
     * Crée des données temporaires pour le système.
     */
    private void genererDonnees() {
        // Première commande
        ArrayList<ItemMenu> itemCommande1 = new ArrayList<ItemMenu>();
        itemCommande1.add(new ItemMenu("Pizza"));
        itemCommande1.add(new ItemMenu("Frites (Large)"));
        Calendar receptionCommande1 = Calendar.getInstance();
        receptionCommande1.set(2022, 3, 23, 17, 30);

        commandes.add(new Commande("1254-124", receptionCommande1, itemCommande1));

        // Seconde commande
        ArrayList<ItemMenu> itemCommande2 = new ArrayList<ItemMenu>();
        itemCommande2.add(new ItemMenu("Poutine"));
        itemCommande2.add(new ItemMenu("Soda (Moyen)"));
        Calendar receptionCommande2 = Calendar.getInstance();
        receptionCommande2.set(2022, 3, 23, 9, 2);

        commandes.add(new Commande("1254-125", receptionCommande2, itemCommande2));

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

        /*
         * Alternative en lambda à la ligne précédente :
         * listeCommandes.getSelectionModel().selectedItemProperty().addListener((v, o, n) -> afficherCommande(n));
         */

        avancementCommande.setProgress(0.0);
    }

    /**
     * Met à jour les champs pour afficher le contenu d'une commande.
     *
     * @param commande la commande à afficher.
     */
    private void afficherCommande(Commande commande) {
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