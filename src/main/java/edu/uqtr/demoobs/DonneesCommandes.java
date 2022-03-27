package edu.uqtr.demoobs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Stocke les données sur l'ensemble des commandes
 */
public class DonneesCommandes {

    /**
     * Données de commandes
     */
    private static ObservableList<Commande> commandes;

    /**
     * Crée les données pour les commandes
     */
    private static void creerCommandes() {
        commandes = FXCollections.observableArrayList();

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

    }

    /**
     * Retourne la liste observable des commandes
     * @return la liste des commandes
     */
    public static ObservableList<Commande> getListeCommandes() {
        if(commandes == null) {
            creerCommandes();
        }

        return commandes;
    }

}
