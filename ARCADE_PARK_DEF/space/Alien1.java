package ARCADE_PARK_DEF.space;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//primo alieno
public class Alien1 extends Foe {
    private BufferedImage body;

    {
        try{
        body = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\alien1.png"));
        }catch(IOException e){

     }
    }
//settaggi primo alieno
    public Alien1(int x){
        super.setBody(body);
        super.setPosition(new Vector (x, -20));
        super.setValues(50,50, 5);
        super.setBounds();
        super.setBulletType(FOE_BULLET);
        super.setRail(100);
        super.setShootFrequency(1000);
    }



    @Override
    public void pattern() {

    }





}
