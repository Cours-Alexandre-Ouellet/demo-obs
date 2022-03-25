package edu.uqtr.demoobs;

import java.util.Random;

public class GenerateurNumeroCommande {

    private int groupe;

    private int sequence;

    private static GenerateurNumeroCommande instance;

    private GenerateurNumeroCommande() {
        sequence = new Random().nextInt(0, 500);
        groupe = new Random().nextInt(1000, 9999);
    }

    public static String prochainNumero() {
        if (instance == null) {
            instance = new GenerateurNumeroCommande();
        }

        return instance.getProchainNumero();
    }

    private String getProchainNumero() {
        incrementerNumero();
        return Integer.toString(groupe) + "-" + formatterNumeroSequence(sequence);
    }

    private static String formatterNumeroSequence(int numero) {
        String valeurFormattee = Integer.toString(numero);

        if (numero < 100) {
            valeurFormattee = "0" + valeurFormattee;
        }
        if (numero < 10) {
            valeurFormattee = "0" + valeurFormattee;
        }


        return valeurFormattee;
    }

    private void incrementerNumero() {
        sequence ++;

        if(sequence > 999) {
            sequence = 0;
            groupe++;
        }
    }

}
