package ARCADE_PARK_DEF.space;
//costanti e implemento interfaccia
public abstract class Foe extends Entity implements Constants {
    private Vector bullet_type;
    private int rail;
    private int shootFrequency;
    private boolean touchable;
//tipo di proiettile
    public void setBulletType(Vector v){
        this.bullet_type = v;
    }

//posizione navicella
    public void pop(){
        if(getPosition().getY() < rail){
            move(Constants.FOE_DOWN);
        }else{
            setTouchable();
        }
    }
//modello navicella

    public abstract void pattern();
//posizione colpi
    public void shoot(){
        if(isAlive() && isTouchable()) {
            Game.bullets.add(new Bullet((getPosition().getX() + getWidth() / 2) - 20, (getPosition().getY() + getHeight()/2) + 25, bullet_type, true));
        }
    }
//navicella
    public void setRail(int r){
        this.rail = r;
    }
//se è attaccabile
    public void setTouchable(){
        touchable = true;
        System.out.println("attaccabile");
    }

    public boolean isTouchable(){return touchable;}

//frequenza colpi
    public void setShootFrequency(int shootFrequency){
        this.shootFrequency = shootFrequency;
    }

    public int getShootFrequency(){
        return shootFrequency;
    }
}
