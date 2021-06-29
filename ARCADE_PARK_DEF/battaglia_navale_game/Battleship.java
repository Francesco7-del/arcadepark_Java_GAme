
package ARCADE_PARK_DEF.battaglia_navale_game;


import java.awt.Color;
import java.awt.event.ActionEvent;


import javax.swing.JButton;

//corazzata
public class Battleship extends Ship  {

    private final int size;

    public Battleship() {
        this.setName("BATTAGLIA NAVALE");
        Battleship.super.setShipSize(4);
        size = Battleship.super.getShipSize();
        init();
    }
//imposta la grandezza e i colori
    private void init() {
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].setBackground(Color.darkGray);
        }
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].addActionListener((ActionEvent e) -> {
                JButton bt = ((JButton) e.getSource());
                if (bt.getBackground() == Color.DARK_GRAY) {
                    Battleship.super.setPressed(true);
                } else if (bt.getBackground() == Color.YELLOW) {
                    Battleship.super.setPressed(false);
                }
                System.out.println("Corazzata");
            });
        }

    }


}
