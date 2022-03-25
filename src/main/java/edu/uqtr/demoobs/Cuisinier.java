package edu.uqtr.demoobs;

/**
 * Cuisinier dans le restaurant
 */
public class Cuisinier {

    /**
     * Le nom du cuisinier.
     */
    private String nom;

    /**
     * Crée un nouveau cuisinier.
     *
     * @param nom le nom du cuisinier.
     */
    public Cuisinier(String nom) {
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
