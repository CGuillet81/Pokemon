package fr.eni.papeterie.ihm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> {
                    GUI app = new GUI();
                    app.setVisible(true);
                    }
        );
    }
}
