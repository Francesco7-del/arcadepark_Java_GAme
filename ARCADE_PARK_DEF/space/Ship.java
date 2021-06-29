package ARCADE_PARK_DEF.space;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship extends Entity {
    private BufferedImage body;
    private int width;
    private int height;
    {
        try{
            body = GarageWindow.chosenShip.getBody();
            width = GarageWindow.chosenShip.getWidth();
            height = GarageWindow.chosenShip.getHeight();
        }catch(Exception e){
            try{
                body = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\ship1.png"));
                width = 50;
                height = 50;
            }catch(IOException a){
                a.printStackTrace();
            }
        }
    }

    public Ship(int x,int y){

        setBody(body);
        setPosition(new Vector(x,y));
        setVelocity(new Vector(0,0));
        setValues(width,height,5);
        setBounds();
    }

    public Bullet shoot(){
        return new Bullet(this.getPosition().getX(), this.getPosition().getY() - getHeight()/2 - 10, Constants.SHIP_BULLET, false);
    }






}
