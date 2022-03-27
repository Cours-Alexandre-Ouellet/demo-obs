package edu.uqtr.demoobs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur pour l'ajout de commandes dans le système.
 */
public class AjoutCommandeController {

    /**
     * ListView des plats principaux disponibles
     */
    @FXML
    private ListView<ItemMenu> listePlatsPrincipaux;

    /**
     * ListView des accompagnements disponibles
     */
    @FXML
    private ListView<ItemMenu> listeAccompagnements;

    /**
     * Numéro de la commande (assigné automatiquement)
     */
    @FXML
    private TextField numeroCommande;

    /**
     * Numéro de civique de l'endroit où l'on livre
     */
    @FXML
    private TextField numeroCivique;

    /**
     * Nom de rue de l'endroit où l'on livre
     */
    @FXML
    private TextField rue;

    /**
     * Numéro de ville de l'endroit où l'on livre
     */
    @FXML
    private TextField ville;

    /**
     * Liste des plats principaux
     */
    private ArrayList<ItemMenu> platsPrincipaux;

    /**
     * Liste des accompagnements
     */
    private ArrayList<ItemMenu> accompagnements;

    /**
     * Référence vers la liste des commandes dans le système.
     */
    private ObservableList<Commande> commandes;

    public AjoutCommandeController() {
        commandes = DonneesCommandes.getListeCommandes();

        initialiserDonnees();
    }

    @FXML
    private void initialize() {
        numeroCommande.setText(GenerateurNumeroCommande.prochainNumero());
    }


    /**
     * Initialise les données des listes d'items du menu
     */
    private void initialiserDonnees() {
        ItemMenu[] itemsPlatsPrincipaux = new ItemMenu[]
        {
            new ItemMenu("Pizza toute garnie"),
            new ItemMenu("Pizza aux ananas"),
            new ItemMenu("Poutine"),
            new ItemMenu("Guédille")
        };

        platsPrincipaux = new ArrayList<>();
        platsPrincipaux.addAll(List.of(itemsPlatsPrincipaux));

        ItemMenu[] itemsAccompagnement = new ItemMenu[]
        {
            new ItemMenu("Frites (petite)"),
            new ItemMenu("Frites (moyenne)"),
            new ItemMenu("Frites (large)"),
            new ItemMenu("Rondelles d'oignon")
        };

        accompagnements = new ArrayList<>();
        accompagnements.addAll(List.of(itemsAccompagnement));
    }
}
