
package ARCADE_PARK_DEF.battaglia_navale_game;


import ARCADE_PARK_DEF.Disegna;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PlayerDetails extends JFrame implements Disegna {

    private JPanel txtPanel, btnPanel;
    private JTextField txt;
    private JButton btn;
    private GridLayout lay;
    private JLabel label;
    private ShipPlacement shipPlacementFrame;
    private Graphics g;
    
    public PlayerDetails() {
 
        draw(g);
    }
//inserisci il  nome giocatore
 

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
    lay = new GridLayout(3, 0);
        btnPanel = new JPanel(new GridBagLayout());
        txtPanel = new JPanel(new GridBagLayout());

        label = new JLabel("INSERISCI IL TUO NOME E PREMI -> 'OK'", JLabel.CENTER);

        txt = new JTextField("");
        txt.setPreferredSize(new Dimension(200, 20));
        txtPanel.add(txt);

        btn = new JButton("OK");

        btn.addActionListener((ActionEvent e) -> {
            if (txt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "NESSUN TESTO INSERITO!");
            } else {
                this.dispose();
                shipPlacementFrame = new p(txt.getText(), 10, 10);
            }
        });
        this.getRootPane().setDefaultButton(btn);
        btnPanel.add(btn);

        this.setLayout(lay);
        this.add(label);
        this.add(txtPanel);
        this.add(btnPanel);
        this.setResizable(false);
        this.setMinimumSize(new Dimension(400, 150));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("DETTAGLI GIOGATORE ");
        this.setVisible(true);
    }

}

