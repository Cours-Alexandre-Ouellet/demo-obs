package edu.uqtr.demoobs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ItemCommande {

    private ItemMenu item;

    private final SimpleIntegerProperty quantite;

    public ItemCommande(ItemMenu item) {
        this.item = item;
        quantite = new SimpleIntegerProperty(0);
    }

    public String toString() {
        return item.toString() + "   " + quantite.getValue().toString() + " X";
    }

    public ItemMenu getItem() {
        return item;
    }

    public final int getQuantite() {
        return quantite.get();
    }

    public final void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

}
