package ARCADE_PARK_DEF.space;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//terzo alieno
public class Alien4 extends Foe{
    private BufferedImage body;
    private boolean left = true;
    private int countDown = 200;

    {
        try{
            body = ImageIO.read(new File("C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\space\\images\\ufo_2.png"));
        }catch(IOException e){

        }
    }
//settaggi quarto alieno
    public Alien4(int x){
        super.setBody(body);
        super.setPosition(new Vector(x, -20));
        super.setValues(206,262, 999 );
        super.setBounds();
        super.setRail(120);
        super.setShootFrequency(1000);
        super.setBounds(new Rectangle2D.Double(getPosition().getX() - 50 , getPosition().getY() - 50, 80, 120));
    }
//movimenti
    @Override
    public void pattern() {
        if(countDown <= 0){
            shootHead();
            countDown = 200;
        }
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
        countDown--;
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
//sparo alla testa
    public void shootHead(){
        if(isAlive() && isTouchable()) {
            Vector velocity = new Vector(Game.ship.getPosition().getX() - getPosition().getX(),
                    Game.ship.getPosition().getY() - (getPosition().getY() + getHeight()/2 + 10));
            velocity = new Vector(velocity.getX() / 80, velocity.getY() / 80);
            Game.bullets.add(new SuperBullet(getPosition().getX(), getPosition().getY() + getHeight()/2 + 20   , velocity, true));
        }

    }

}
