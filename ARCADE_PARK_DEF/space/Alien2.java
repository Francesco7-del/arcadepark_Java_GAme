package ARCADE_PARK_DEF.space;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//secondo alieno
public class Alien2 extends Foe {
    private BufferedImage body;
    private boolean left = true;

    {
        try{
            body = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\alien2.png"));
        }catch(IOException e){

        }
    }
//settaggi secondo alieno
    public Alien2(int x){
        super.setBody(body);
        super.setPosition(new Vector(x, -20));
        super.setValues(80,80, 10);
        super.setBounds();
        super.setBulletType(FOE_BULLET);
        super.setRail(150);
        super.setShootFrequency(1500);
    }


    @Override
    public void pattern() {
        if(left){
            move(FOE_LEFT);
            if(getPosition().getX() <= 50) {
                left = false;
            }
            }else{
                move(FOE_RIGHT);
                if(getPosition().getX() >= Game.width - 50){
                    left = true;
                }
            }
        }

   


    }

