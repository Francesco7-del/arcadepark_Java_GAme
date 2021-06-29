package ARCADE_PARK_DEF.space;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Garage extends JFrame {
    private final Border border = BorderFactory.createLineBorder(Color.WHITE, 3);
    private final Font font = new Font("monospaced", Font.BOLD,  30);
    private final Font font2 = new Font("monospaced", Font.BOLD, 50);
    private final JLabel title = new JLabel("Garage");
    private final JLabel right = new JLabel("->");
    private final JLabel left = new JLabel("<-");
    private final JLabel back = new JLabel("torna al menù");
    private GarageWindow window = new GarageWindow();

//metodo garage
    public Garage(){
        this.setLayout(new BorderLayout());
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.title.setFont(font);
        this.title.setForeground(Color.WHITE);
        this.add(title, BorderLayout.NORTH);
        this.right.setFont(font2);
        this.right.setForeground(Color.WHITE);
        this.add(right, BorderLayout.EAST);
        this.left.setFont(font2);
        this.left.setForeground(Color.WHITE);
        this.add(left, BorderLayout.WEST);
        this.back.setFont(font);
        this.back.setHorizontalAlignment(JLabel.CENTER);
        this.back.setForeground(Color.WHITE);
        this.add(back, BorderLayout.SOUTH);
        this.add(window, BorderLayout.CENTER);
//scelte del giocatore con il mouse
        this.right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                window.right();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
            }
        });

        this.left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                window.left();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
            }
        });

        this.back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                setVisible(false);
                Menu menu = new Menu();
                menu.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
            }
        });
    }
}
