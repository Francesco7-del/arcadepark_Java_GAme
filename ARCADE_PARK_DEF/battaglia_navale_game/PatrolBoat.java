
package ARCADE_PARK_DEF.battaglia_navale_game;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//patrolboat
public class PatrolBoat extends Ship {

    private int size;

    public PatrolBoat() {
        this.setName("PatrolBoat");
        PatrolBoat.super.setShipSize(2);
        size = PatrolBoat.super.getShipSize();
        init();
    }
//imposta grandezza e colore
    private void init() {
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].setBackground(Color.darkGray);
        }
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton bt = ((JButton) e.getSource());
                    if (bt.getBackground() == Color.DARK_GRAY) {
                        PatrolBoat.super.setPressed(true);
                    } else if (bt.getBackground() == Color.YELLOW) {
                        PatrolBoat.super.setPressed(false);
                    }
                    System.out.println("PatrolBoat");
                }
            });
        }
    }


}
