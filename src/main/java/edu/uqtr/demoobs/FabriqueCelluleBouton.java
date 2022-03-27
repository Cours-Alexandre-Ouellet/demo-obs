package edu.uqtr.demoobs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class FabriqueCelluleBouton implements Callback<TableColumn<ItemCommande, Void>, TableCell<ItemCommande, Void>> {

    public enum Operation {
        INCREMENT,
        DECREMENT
    }

    private Operation operation;

    private String symbole;

    public FabriqueCelluleBouton(String symbole, Operation operation) {
        this.symbole = symbole;
        this.operation = operation;
    }

    @Override
    public TableCell<ItemCommande, Void> call(TableColumn<ItemCommande, Void> param) {
        return new TableCell<ItemCommande, Void>() {

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Button bouton = creerBouton();

                    setGraphic(bouton);
                }
            }

            private Button creerBouton() {
                Button bouton = new Button(symbole);
                bouton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ItemCommande itemCommande = getTableView().getItems().get(getIndex());

                        if (operation == Operation.INCREMENT) {
                            incrementerQuantite(itemCommande);
                        } else {
                            decrementerQuantite(itemCommande);
                        }

                        getTableView().refresh();
                    }
                });

                return bouton;
            }

            private void incrementerQuantite(ItemCommande item) {
                item.setQuantite(item.getQuantite() + 1);
            }

            private void decrementerQuantite(ItemCommande item) {
                if(item.getQuantite() > 0) {
                    item.setQuantite(item.getQuantite() - 1);
                }
            }
        };
    }
}
