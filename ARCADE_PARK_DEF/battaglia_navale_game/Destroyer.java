
package ARCADE_PARK_DEF.battaglia_navale_game;


import java.awt.Color;
import java.awt.event.ActionEvent;


import javax.swing.JButton;

//distruttore
public class Destroyer extends Ship {

    private final int size;

    public Destroyer() {
        this.setName("DISTRUTTORE");
        Destroyer.super.setShipSize(3);
        size = Destroyer.super.getShipSize();
        init();
    }
//imposta grandezza e colori
    private void init() {
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].setBackground(Color.darkGray);
        }
        for (int i = 0; i < size; i++) {
            this.getBtn()[i].addActionListener((ActionEvent e) -> {
                JButton bt = ((JButton) e.getSource());
                if (bt.getBackground() == Color.DARK_GRAY) {
                    Destroyer.super.setPressed(true);
                } else if (bt.getBackground() == Color.YELLOW) {
                    Destroyer.super.setPressed(false);
                }
                System.out.println("DISTRUTTORE");
            });
        }
    }


}
