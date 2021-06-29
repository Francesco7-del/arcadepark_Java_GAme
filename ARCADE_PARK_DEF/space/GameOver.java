package ARCADE_PARK_DEF.space;

import ARCADE_PARK_DEF.Disegna;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameOver extends JPanel implements Disegna {
    private final JLabel label = new JLabel("GAME OVER");
    private final JLabel label2 = new JLabel("premi SPACE per tornare al menù!!");
    private Graphics  g ;
    Font font = new Font("monospaced", Font.BOLD,  30);
    Font font2 = new Font("monospaced", Font.BOLD, 20);
    private final int fade = 0;
//scritta game over
    public GameOver(){
        this.setLayout(new GridLayout(2, 1));
        this.setSize(800, 600);
        this.setVisible(true);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label2.setHorizontalAlignment(JLabel.CENTER);
        this.label.setFont(font);
        this.label2.setFont(font2);
        this.add(label);
        this.add(label2);
        this.setBackground(new Color(0,0,0,0));
        new Timer(20, fadeIn).start();
    }

    AbstractAction fadeIn = new AbstractAction() {
               @Override
               public void actionPerformed(ActionEvent actionEvent) {
                    drawGameover( g);
               }
        
        
    };

    @Override
    public void drawGameover(Graphics g) {
    
            if(fade >= 100){
                Thread.currentThread().interrupt();
                return;
            }
                setBackground(Color.BLACK);
                label.setForeground(new Color(255,255,255));
                label2.setForeground(new Color(255,255,255));
                repaint();
                revalidate();
               
            }


    @Override
    public void draw(Graphics g) {
    }

 
}
