package edu.uqtr.demoobs;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Commande passée par un client du restaurant.
 */
public class Commande {

    /**
     * Items commandés sur le menu.
     */
    private ArrayList<ItemMenu> itemsMenu;

    /**
     * Cuisinier assigné à la commande.
     */
    private Cuisinier responsable;

    /**
     * Le numéro assigné à la commande.
     */
    private String numero;

    /**
     * La date et l'heure de réception de la commande.
     */
    private Calendar reception;

    /**
     * Indique si la commande est terminée de préparer.
     */
    private boolean terminee;

    /**
     * Crée une nouvelle commande qui doit être préparée par un cuisinier à déterminer.
     *
     * @param numero    le numéro de la commande.
     * @param reception la date et l'heure de réception.
     * @param itemsMenu les items commandés.
     */
    public Commande(String numero, Calendar reception, ArrayList<ItemMenu> itemsMenu) {
        this.numero = numero;
        this.reception = reception;
        this.itemsMenu = itemsMenu;

        responsable = null;
        terminee = false;
    }

    /**
     * Accède à la date et l'heure de réception de la commande.
     *
     * @return la date et l'heure de réception de la commande.
     */
    public Calendar getReception() {
        return reception;

    }

    /**
     * Accède à la liste des items commandés.
     *
     * @return la liste des items commandés.
     */
    public ArrayList<ItemMenu> getItems() {
        return itemsMenu;
    }

    @Override
    public String toString() {
        return numero;
    }
}
