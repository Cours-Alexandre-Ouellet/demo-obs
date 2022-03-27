package edu.uqtr.demoobs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Contrôleur pour l'ajout de commandes dans le système.
 */
public class AjoutCommandeController {

    /**
     * ListView des plats principaux disponibles
     */
    @FXML
    private TableView<ItemCommande> listePlatsPrincipaux;

    /**
     * ListView des accompagnements disponibles
     */
    @FXML
    private TableView<ItemCommande> listeAccompagnements;

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

        // Création des tableaux
        creerTableauItemCommande(listePlatsPrincipaux, genererListeItemCommande(platsPrincipaux));
        creerTableauItemCommande(listeAccompagnements, genererListeItemCommande(accompagnements));
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

    @FXML
    private void ajouter(ActionEvent event) {
        ArrayList<ItemCommande> itemsCommandes = new ArrayList<>();

        // Parcours des tableaux et ajout des éléments dont la quantité est d'au moins 1
        for(ItemCommande item : listePlatsPrincipaux.getItems()) {
            if(item.getQuantite() > 0) {
                itemsCommandes.add(item);
            }
        }
        for(ItemCommande item : listeAccompagnements.getItems()) {
            if(item.getQuantite() > 0) {
                itemsCommandes.add(item);
            }
        }

        commandes.add(new Commande(
                numeroCommande.getText(),
                Calendar.getInstance(),
                itemsCommandes,
                new Adresse(numeroCivique.getText(), rue.getText(), ville.getText())
        ));

        fermer((Node) event.getSource());
    }

    @FXML
    private void annuler(ActionEvent event) {
        fermer((Node) event.getSource());
    }

    /**
     * Ferme la fenêtre.
     *
     * @param element un élément quelconque de la fenêtre à fermer.
     */
    private void fermer(Node element) {
        Stage stage = (Stage) element.getScene().getWindow();
        stage.close();
    }

    /**
     * Crée une liste d'item commande pour une commande vide (quantité de 0).
     *
     * @param items la liste des items à ajouter.
     * @return la liste d'item commandés vide.
     */
    private ArrayList<ItemCommande> genererListeItemCommande(ArrayList<ItemMenu> items) {
        ArrayList<ItemCommande> itemCommandes = new ArrayList<>();

        for (ItemMenu item : items) {
            itemCommandes.add(new ItemCommande(item));
        }

        return itemCommandes;
    }

    /**
     * Crée un table pour afficher des items de commande. Les items de commnade possède une propriété observable
     * entière
     *
     * @param table la table à mettre en forme.
     */
    private void creerTableauItemCommande(TableView<ItemCommande> table, ArrayList<ItemCommande> items) {
        // Colonne pour les noms
        TableColumn<ItemCommande, String> colonneNom = new TableColumn<>("Item");
        colonneNom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ItemCommande, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getItem().getNom());
            }
        });

        // Colonne pour les quantités
        TableColumn<ItemCommande, IntegerProperty> colonneQuantite = new TableColumn<>("Quantité");
        colonneQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        // Ajoute le contenu dans la table
        table.getItems().addAll(items);
        table.getColumns().addAll(colonneNom, colonneQuantite);
    }
}
