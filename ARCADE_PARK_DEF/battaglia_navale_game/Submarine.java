/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCADE_PARK_DEF.battaglia_navale_game;


import java.awt.Color;
import java.awt.event.ActionEvent;


import javax.swing.JButton;

public class Submarine extends Ship {

    private final int size;
//sottomarino
    public Submarine() {
        this.setName("SOTTOMARINO");
        Submarine.super.setShipSize(3);
        size = Submarine.super.getShipSize();
        init();
    }
//colore e grandezza sottomarino
    private void init() {
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].setBackground(Color.darkGray);
        }
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].addActionListener((ActionEvent e) -> {
                JButton bt = ((JButton) e.getSource());
                if (bt.getBackground() == Color.DARK_GRAY) {
                    Submarine.super.setPressed(true);
                } else if (bt.getBackground() == Color.YELLOW) {
                    Submarine.super.setPressed(false);
                }
                System.out.println("SOTTOMARINO");
            });
        }
    }
}
