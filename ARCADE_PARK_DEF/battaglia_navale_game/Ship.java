
package ARCADE_PARK_DEF.battaglia_navale_game;




import ARCADE_PARK_DEF.Disegna;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;


import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
* Rende utili i JButtons nel JPanel assegnando loro un ActionListener.
 * Questa è anche la classe della super nave.
 */
public abstract class Ship extends JPanel  implements Disegna {

    private JButton[] btn;
    private final Color color;
    private boolean shipChecked;
    private boolean pressed;
    private boolean rotateShip = false;
    private int size;
    private Graphics g;
    public Ship() {
        shipChecked = false;
        color = Color.lightGray;
        draw(g);
    }

//eventi mouse e rotazione
    public void setButtonsEnabled(boolean boo) {
        for (int i = 0; i < 5; i++) {
            btn[i].setBackground(color);
            btn[i].setEnabled(boo);
        }
    }
    
    //metodi 'navi'

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public JButton[] getBtn() {
        return btn;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setRotateShip(boolean rotateShip) {
        this.rotateShip = rotateShip;
    }

   public boolean isRotateShip() {
        return rotateShip;
    }

    public int getShipSize() {
        return size;
    }

    public void setShipSize(int size) {
        this.size = size;
    }

    @Override
    public void drawGameover(Graphics g) {
    }

    
   
    @Override
    public void draw(Graphics g) {
this.setLayout(new GridBagLayout());
        btn = new JButton[5];

        for (int i = 0; i < 5; i++) {
            btn[i] = new JButton();
            btn[i].setPreferredSize(new Dimension(40, 40));
            btn[i].setBackground(color);
            Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
            btn[i].setBorder(border);
            this.add(btn[i]);
        }

        for (int i = 0; i < 5; i++) {
            final int j = i;
            btn[i].addActionListener(e -> {
                if (btn[j].getBackground() == Color.darkGray && !shipChecked) {
                    for (int k = 0; k < 5; k++) {
                        if (btn[k].getBackground() == Color.darkGray) {
                            btn[k].setBackground(Color.YELLOW);
                        }
                    }
                    shipChecked = true;
                } else if (btn[j].getBackground() == Color.YELLOW && shipChecked) {
                    for (int k = 0; k < 5; k++) {
                        if (btn[k].getBackground() == Color.YELLOW) {
                            btn[k].setBackground(Color.darkGray);
                        }
                    }
                    shipChecked = false;
                }
            });
        }    
    
    }

    
     
    
    
    
    
        
        
      
      
    

}
