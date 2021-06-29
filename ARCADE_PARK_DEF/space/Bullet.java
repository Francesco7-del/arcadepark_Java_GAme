package ARCADE_PARK_DEF.space;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//proiettile
public class Bullet {
    private final Vector position;
    private Vector velocity;
    private boolean foe = false;
    private BufferedImage body;
    private Rectangle2D bounds;
    {
        try {
            body = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\bullet.png"));
        } catch (IOException e) {
          
        }
    }
//velocità e posizione proiettile
    public Bullet(int x, int y, Vector v, boolean foe){
        this.position = new Vector(x,y);
        this.velocity = v;
        this.bounds = new Rectangle2D.Double(position.getX(), position.getY(), 10,10);
        this.foe = foe;
    }
//movimenti
    public void move(){
        this.position.add(velocity);
        updateBounds();
    }
//colore
    public void paint(Graphics g){
        g.drawImage(body,this.position.getX(), this.position.getY()-5, 10,10, null );
    }
//velocità
    public void setVelocity(Vector v){
        velocity = v;
    }
//proiettile nemico
    public boolean isFoeBullet(){
        return foe;
    }
//posizione
    public Vector getPosition(){
        return position;
    }
//corpo 
    public void setBody(BufferedImage body){
        this.body = body;
    }
//confine
    public void setBounds(Rectangle2D bounds){
        this.bounds = bounds;
    }
//aggiorna confine
    public void updateBounds(){
        bounds.setFrame(position.getX(), position.getY()-5, 10,10);
    }
//prende i confini
    public Rectangle2D getBounds(){
        return bounds;
    }

}
