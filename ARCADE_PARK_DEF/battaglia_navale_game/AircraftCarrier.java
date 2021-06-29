/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCADE_PARK_DEF.battaglia_navale_game;



import java.awt.Color;

import javax.swing.JButton;


public class AircraftCarrier extends Ship {

    
    private final int size;
    // costruttore 'portarei'
    public AircraftCarrier() {
        this.setName("Aircraft");
        AircraftCarrier.super.setShipSize(5);
        size = AircraftCarrier.super.getShipSize();
        init();
    }
//imposta i  colori
    private void init() {
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].setBackground(Color.darkGray);
        }
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].addActionListener(e -> {
                JButton bt = ((JButton) e.getSource());
                if (bt.getBackground() == Color.DARK_GRAY) {
                    AircraftCarrier.super.setPressed(true);
                } else if (bt.getBackground() == Color.YELLOW) {
                    AircraftCarrier.super.setPressed(false);
                }
                System.out.println("Aircraft");
            });
        }
    }
}
