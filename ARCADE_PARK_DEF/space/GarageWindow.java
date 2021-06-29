package ARCADE_PARK_DEF.space;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GarageWindow extends JPanel  {
    private BufferedImage ship1;
    private BufferedImage ship2;
    private BufferedImage ship3;
    private BufferedImage ship4;
    private int displayedShip = 0;
    private ArrayList<PlayableShip> images = new ArrayList<>();
    private boolean sliding = false;
    private int xPosition = 90;
    public static PlayableShip chosenShip;
//caricamento immaggini
    {
        try{
            ship1 = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\ship1.png"));
            ship2 = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\ship2.png"));
            ship3 = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\ship3.png"));
            ship4 = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\ship4.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public GarageWindow(){
        images.add(new PlayableShip(ship1, 50,50));
        images.add(new PlayableShip(ship2, 50,100));
        images.add(new PlayableShip(ship3, 100,100));
        images.add(new PlayableShip(ship4, 100,80));
        this.setVisible(true);
        this.setSize(500,500);
     
        this.setBackground(Color.BLACK);
        chosenShip = images.get(displayedShip);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(images.get(displayedShip).getBody(), 90,200,100,100, null);
    }
//movimenti a destra
    public void right(){
        if(displayedShip <= 0){
            displayedShip = images.size() - 1;
        }else{
            displayedShip--;
        }
        chosenShip = images.get(displayedShip);
        repaint();
    }
//movimenti a sinistra
    public void left(){
        if(displayedShip >= images.size() - 1){
            displayedShip = 0;
        }else{
            displayedShip++;
        }
        chosenShip = images.get(displayedShip);
        repaint();
    }

  
}
