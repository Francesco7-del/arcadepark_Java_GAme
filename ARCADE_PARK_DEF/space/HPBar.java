package ARCADE_PARK_DEF.space;

import ARCADE_PARK_DEF.Disegna;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HPBar extends JPanel  implements Disegna{
    public static int hp;
    private BufferedImage heart;

    {
        try{
            heart = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\heart.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public HPBar(int hp){
        HPBar.hp = hp;
    }

    public void drawHPBar(Graphics g){
       
    }


    public static void loseHP(){
        HPBar.hp--;
    }

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
        int x = 20;
        for(int i = 0; i<HPBar.hp ; i++){
            g.drawImage(heart, 20 + i*x,50,15,15,null);
        }    }
}
