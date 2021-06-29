package ARCADE_PARK_DEF.space;
//vettori
public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
//sostituzione
    public void replace(Vector v){
        this.x = v.getX();
         this.y = v.getY();
    }
//aggiunge
    public void add(Vector v){
        this.x += v.getX();
        this.y += v.getY();
    }

    public void substract(Vector v){
        this.x = v.getX() - this.x;
        this.y = v.getY() - this.y;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
