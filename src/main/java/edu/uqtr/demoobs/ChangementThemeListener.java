package edu.uqtr.demoobs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Toggle;

import java.util.ArrayList;

/**
 * Charge le bon thème de couleur selon la valeur sélectionnée. Les valeurs sont définits dans
 * l'énumération Theme
 */
public class ChangementThemeListener implements ChangeListener<Toggle> {

    /**
     * Valeurs de thème possible.
     */
    private enum Theme {
        CLAIR("clair"),
        SOMBRE("sombre");

        /**
         * Valeur du thème utilisée dans les descriptifs de class-style.
         */
        private String valeur;

        /**
         * Crée un nouveau thème en indiquant un valeur de descriptif.
         *
         * @param valeur la valeur décrivant le thème.
         */
        Theme(String valeur) {
            this.valeur = valeur;
        }
    }

    /**
     * Le premier élément de la fenêtre sur laquelle s'applique le changement de thème.
     */
    private Parent racine;

    /**
     * Crée un nouvel objet de changement de thème
     *
     * @param racine la racine de la scène pour laquelle changer le thème
     */
    public ChangementThemeListener(Parent racine) {
        this.racine = racine;
    }

    @Override
    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        Theme ancienTheme = Theme.valueOf(oldValue.getUserData().toString());
        Theme theme = Theme.valueOf(newValue.getUserData().toString());

        changerTheme(racine, ancienTheme.valeur, theme.valeur);
    }

    /**
     * Change les valeurs de style pour prendre en compte le nouveau thème.
     *
     * @param node         l'objet sur lequel faire la modification de style.
     * @param ancienTheme  la valeur de l'ancien thème.
     * @param nouveauTheme la valeur du nouveau thème.
     */
    private void changerTheme(Node node, String ancienTheme, String nouveauTheme) {
        // Liste des modifications (on veut éviter de modifier la liste en cours d'itération)
        ArrayList<String> themesRetires = new ArrayList<>();
        ArrayList<String> themesAjoutes = new ArrayList<>();

        for (String style : node.getStyleClass()) {
            if (style.contains(ancienTheme)) {
                themesRetires.add(style);
                // Modifie le style -> texte-clair => texte-sombre
                themesAjoutes.add(style.replace(ancienTheme, nouveauTheme));
            }
        }

        if (themesRetires.size() > 0) {
            System.out.println(String.join(", ", themesRetires));
            System.out.println(String.join(", ", themesAjoutes));
        }

        // Application des modifications
        node.getStyleClass().removeAll(themesRetires);
        node.getStyleClass().addAll(themesAjoutes);

        // Propagation si nécessaire
        if (node instanceof Parent) {
            Parent parent = (Parent) node;
            for (Node enfant : parent.getChildrenUnmodifiable()) {
                changerTheme(enfant, ancienTheme, nouveauTheme);
            }
        }
    }
}
