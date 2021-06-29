package ARCADE_PARK_DEF.space;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//terzo alieno
public class Alien3 extends Foe {
    private BufferedImage body;
    private boolean left = true;

    {
        try{
            body = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\alien3.png"));
        }catch(IOException e){

        }
    }
//settaggi terzo alieno
    public Alien3(int x){
        super.setBody(body);
        super.setPosition(new Vector(x, -20));
        super.setValues(150,150, 25);
        super.setBounds();
        super.setRail(120);
        super.setShootFrequency(1000);
    }

    @Override
    public void pattern() {
    
    }
//sparo
    @Override
    public void shoot(){
        if(isAlive() && isTouchable()) {
            Vector velocity = new Vector(Game.ship.getPosition().getX() - getPosition().getX(),
                    Game.ship.getPosition().getY() - (getPosition().getY() + getHeight()/2 + 10));
            velocity = new Vector(velocity.getX() / 40, velocity.getY() / 40);
            Game.bullets.add(new Bullet(getPosition().getX(), getPosition().getY() + getHeight()/2 + 10, velocity, true));
        }
    }


}
