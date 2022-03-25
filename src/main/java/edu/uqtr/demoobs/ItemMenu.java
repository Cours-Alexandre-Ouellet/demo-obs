package edu.uqtr.demoobs;

/**
 * Un item du menu du restaurant.
 */
public class ItemMenu {

    /**
     * Le nom de l'item sur le menu.
     */
    private String nom;

    /**
     * Crée un nouvel item de menu.
     * @param nom le nom de l'item.
     */
    public ItemMenu(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public String getNom() {
        return nom;
    }

}
