package edu.uqtr.demoobs;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Fabrique de cellules de liste pour les commandes.
 * <p>
 * À côté du nom de la commande, on affiche l'état (affecté ou non de la commande).
 */
public class FabriqueCelluleCommande implements Callback<ListView<Commande>, ListCell<Commande>> {

    /**
     * Méthode appeleée pour générer les items de la liste des commandes.
     *
     * @param liste la liste des commandes.
     * @return la liste des cellules des commandes.
     */
    @Override
    public ListCell<Commande> call(ListView<Commande> liste) {
        return new ListCell<>() {
            public void updateItem(Commande commande, boolean empty) {
                super.updateItem(commande, empty);
                if (empty || commande == null) {    // Ligne vide ou commande nulle (évite des Exception)
                    setText(null);
                } else {
                    // On construit la chaîne, puis setText permet de l'afficher
                    String estAffectee = commande.getResponsable() == null ? "(Non affectée)" : "(Affectée)";
                    setText(commande.getNumero() + " " + estAffectee);
                }
            }
        };
    }

}
