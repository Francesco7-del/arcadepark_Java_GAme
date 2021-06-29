package ARCADE_PARK_DEF.space;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {
    JLabel title = new JLabel("THE SPACE ");
    JLabel newGame = new JLabel("GIOCA");
    JLabel gunShop = new JLabel("GARAGE ");
       Font font = new Font("monospaced", Font.BOLD,  30);
    Font font2 = new Font("monospaced", Font.BOLD, 20);
    Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
//menu
    public Menu() {
        this.setLayout(new GridLayout(4, 1));
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setFont(font);
        this.title.setForeground(Color.WHITE);
        this.newGame.setHorizontalAlignment(JLabel.CENTER);
        this.newGame.setFont(font);
        this.newGame.setForeground(Color.WHITE);
        this.gunShop.setHorizontalAlignment(JLabel.CENTER);
        this.gunShop.setFont(font);
        this.gunShop.setForeground(Color.WHITE);
        this.add(title);
        this.add(newGame);
        this.add(gunShop);
        this.getContentPane().setBackground(Color.BLACK);
//eventi mouse
        this.newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                Game game = new Game();
                game.init();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                newGame.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                newGame.setBorder(null);
            }
        });

        this.gunShop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                Garage garage = new Garage();
                garage.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                gunShop.setBorder(border);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                gunShop.setBorder(null);
            }
        });

      
    }

}
