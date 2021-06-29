

package ARCADE_PARK_DEF.snake__game.model;


import java.awt.*;

/**
 * Funzione Snake e raccolta dati dalla classe di incapsulamento
 */

public class Snake  {

    private final Image snakeDotImage;
    private final int dotSize;
    private int size;
    private final int[] xs;
    private final int[] ys;

    private Directions directions;

    public Snake(Image snakeDotImage,
                 final int dotSize,
                 final int maxSize,
                 final int startX) {

        this.snakeDotImage = snakeDotImage;
        this.dotSize = dotSize;
        size = 3;

        xs = new int[maxSize];
        ys = new int[xs.length];
        initCoords(startX);

        directions = Directions.RIGHT;
    }

    private void initCoords(int startX) {
        for (int dotIndex = 0; dotIndex < getSize(); dotIndex++) {
            xs[dotIndex] = startX - dotIndex * dotSize;
            ys[dotIndex] = startX;
        }
    }

    public Image getSnakeDotImage() {
        return snakeDotImage;
    }

    public int getSize() {
        return size;
    }

    public void incSize() {
        size++;
    }

    public int getX(int index) {
        return xs[index];
    }

    public int getY(int index) {
        return ys[index];
    }

    public void setX(int index, int value) {
        xs[index] = value;
    }

    public void setY(int index, int value) {
        ys[index] = value;
    }

    //Movimento SErpente
    public void move_() {
        moveTail();
        moveHead();
    }

    //Movimento coda
    private void moveTail() {
        for (int i = getSize(); i > 0; i--) {
            setX(i, getX(i - 1));
            setY(i, getY(i - 1));
        }
    }

    //movimento testa
    private void moveHead() {
        if (moveLeft()) {
            xs[0] -= dotSize;
        } else if (moveRi()) {
            xs[0] += dotSize;
        } else if (moveUp()) {
            ys[0] -= dotSize;
        } else if (moveDown()) {
            ys[0] += dotSize;
        }
    }

    public void setMovingDirection(Directions direction) {
        directions = direction;
    }

    //prende la direzione SINISTRA
    
    public boolean moveLeft() {
        return directions == Directions.LEFT;
    }

    //prende la direzione SU
    
    public boolean moveUp() {
        return directions == Directions.UP;
    }

    //prende la direzione DESTRA

    public boolean moveRi() {
        return directions == Directions.RIGHT;
    }

    //prende la direzione GIU
    
    public boolean moveDown() {
        return directions == Directions.DOWN;
    }

    public int getMaxDotsNumber() {
        return xs.length;
    }

    
}
