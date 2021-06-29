package ARCADE_PARK_DEF.space;



import javax.imageio.ImageIO;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity {
    private Vector position;
    private Vector velocity;
    private Vector center;
    private int height;
    private int width;
    private int hp;
    private int touchedCountdown = 5;
    private int deathFrame = 0;
    private BufferedImage body;
    private boolean alive;
    private boolean touched;
    private Rectangle2D bounds;
    private BufferedImage explosion1 ;
    private BufferedImage explosion2;
    private BufferedImage explosion3;
    private BufferedImage explosion4;
    private BufferedImage explosion5;
    private BufferedImage explosion6;
    private BufferedImage explosion7;


    {
        try {
            BufferedImage explosion = ImageIO.read(new File("C:\\Users\\User\\Desktop\\aaaa\\aracade_park\\space\\images\\explosion.png"));
            explosion1 = explosion.getSubimage(0,0, 192,192);
            explosion2 = explosion.getSubimage(192,0,192,192);
            explosion3 = explosion.getSubimage(384,0,192,192);
            explosion4 = explosion.getSubimage(576,0,192,192);
            explosion5 = explosion.getSubimage(768,0,192,192);
            explosion6 = explosion.getSubimage(0,192,192,192);
            explosion7 = explosion.getSubimage(192,192,192,192);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entity(){

    }
//corpo
    public BufferedImage getBody(){
        return body;
    }
//setto il corpo
    public void setBody(BufferedImage body){
        this.body = body;
    }
//posizione
    public void setPosition(Vector position){
        this.position = position;
    }
//velocità
    public void setVelocity(Vector v){
        this.velocity = velocity;
    }
//imposto i valori
    public void setValues(int width, int height, int hp){
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.alive = true;
    }
    //invincibilità
    public void setInvincibility(){
        this.hp = 10000;
    }
//proiettile
    public void setBounds(){
        this.bounds = new Rectangle2D.Double(position.getX() - width/2 , position.getY() - height/2, width - 5, height);
    }

    public void setBounds(Rectangle2D bound){
        this.bounds = bounds;
    }

    public void updateBounds(){
        bounds.setFrame(position.getX() - width/2, position.getY() - height/2, width - 5, height);
    }

    public Rectangle2D getBounds(){
        return bounds;
    }

//posizione
    public Vector getPosition(){
        return position;
    }
//larghezza
    public int getWidth(){
        return width;
    }
//altezza
    public int getHeight(){
        return height;
    }
//è vivo 
    public boolean isAlive(){return alive;}

//movimenti
    public void move(Vector v){
        position.add(v);
        updateBounds();
    }
//animazione 
    public void deathAnimation(){
        if(deathFrame > 42){
            Game.entities.remove(this);
            try{
                Game.foes.remove(this);
            }catch(Exception e){

            }
        }
        deathFrame++;
     

    }
//preso 
    public void touched(){
        touched = true;
        hp--;
        if(this instanceof  Ship){
            HPBar.loseHP();
        }
        System.out.println("Vite rimanenti = "+hp);
        if(hp <= 0){
            System.out.println("MORTO ");
            alive = false;
            body = explosion1;
            deathAnimation();
        }
    }

    public boolean isTouched(){
        return touched;
    }

//non preso
    public void unTouched(){
        touchedCountdown--;
        if(touchedCountdown <= 0){
            touched = false;
            touchedCountdown = 5;
        }
    }


}
