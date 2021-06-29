

package ARCADE_PARK_DEF.snake__game.panel;



import ARCADE_PARK_DEF.Disegna;
import ARCADE_PARK_DEF.Move;
import ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions;
import ARCADE_PARK_DEF.snake__game.model.Directions;
import ARCADE_PARK_DEF.snake__game.model.DogObjectModel;

import ARCADE_PARK_DEF.snake__game.model.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class BoardPanel extends JPanel implements ActionListener,Runnable,Move,Disegna {

    private Thread thread;
    private final int dotSize;
    static final int SCREEN_WIDTH = 600;
    static final int WIDTH = 300;
    private final DogObjectModel dogObjectModel;
    private final Random random;

    private Snake snake;

    private boolean isPlaying;
    private final Timer timer;
    private int applecount = 0;

    /**
     * Costruttore
     *
     * @param boardSettingsOptions
     */
    public BoardPanel(final BoardSettingsOptions boardSettingsOptions) {
        dotSize = boardSettingsOptions.getDotSize();
        dogObjectModel = new DogObjectModel(new ImageIcon(boardSettingsOptions.getAppleImageLocation()).getImage());
        random = new Random();
        snake = new Snake(new ImageIcon(boardSettingsOptions.getSnakeDotImageLocation()).getImage(),
                dotSize, boardSettingsOptions.getAllDotsNumber(), getWidth() / 2);
        isPlaying = true;
        timer = new Timer(100, this);

        addKeyListener(new FieldKeyListener());


        setSize(new Dimension(boardSettingsOptions.getWindowSizePerDimension(), boardSettingsOptions.getWindowSizePerDimension()));
        setPreferredSize(getSize());
        setBackground(Color.black);
        setFocusable(true);
        setVisible(true);

        startGame();
    }

    public void start() {
        try {
            thread = new Thread(this);
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * inizia il gioco
     */

    private void startGame() {
        isPlaying = true;
        timer.start();

        snake = new Snake(
                snake.getSnakeDotImage(),
                dotSize,
                snake.getMaxDotsNumber(),
                getWidth() / 2);
        start();

    }

    //    Random mette l'immagine del serpente in board
    private void randomDOGCoords() {
        dogObjectModel.setX(random.nextInt(getWidth() / dotSize) * dotSize);
        dogObjectModel.setY(random.nextInt(getHeight() / dotSize) * dotSize);
    }
//collisioni
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (isPlaying
                && !isBadCollision()) {

            if (isAppleCollision()) {
                thread.stop();
                snake.incSize();
                applecount++;
                start();
            }

            snake.move_();
        }

        repaint();
    }
 
    
    
    private boolean isBadCollision() {
        return isSnakeCollision() || isBorderCollision();
    }

    private boolean isSnakeCollision() {
        for (int i = snake.getSize(); i > 0; i--) {
            if (i > 4
                    && snake.getX(0) == snake.getX(i)
                    && snake.getY(0) == snake.getY(i)) {

                isPlaying = false;
                return true;
            }
        }

        return false;
    }

    private boolean isBorderCollision() {
        if (snake.getX(0) >= getWidth()
                || snake.getX(0) < 0
                || snake.getY(0) >= getHeight()
                || snake.getY(0) < 0) {

            isPlaying = false;
            return true;
        }

        return false;
    }

    private boolean isAppleCollision() {
        return snake.getX(0) == dogObjectModel.getX()
                && snake.getY(0) == dogObjectModel.getY();
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        if (isPlaying) {
            draw(gr);
            drawApple(gr);
        } else {
            timer.stop();
          
          drawGameover(gr);
        }
    }



    //    Display Imagini
    private void drawApple(Graphics gr) {
        gr.drawImage(dogObjectModel.getImage(), dogObjectModel.getX(), dogObjectModel.getY(), this);
        gr.setColor(Color.red);
        gr.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(gr.getFont());
        gr.drawString("Score: " + applecount, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applecount)) / 2, gr.getFont().getSize());
    }

    //    fine gioco
    
   

    @Override
    public void drawGameover(Graphics gr) {
         gr.setColor(Color.red);
        gr.setFont(new Font("Ink Free", Font.BOLD, 20));
        gr.drawString("GAME OVER!!!", WIDTH / 2, getHeight() /3);
        gr.setColor(Color.white);
        gr.setFont(new Font("Ink Free", Font.BOLD, 20));
        
        gr.drawString("RIPROVA! PREMI INVIO!!!", WIDTH / 2, getHeight() / 2);

        gr.setColor(Color.red);
        gr.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(gr.getFont());
        gr.drawString("Score: " + applecount, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applecount)) / 2, gr.getFont().getSize());
        applecount = 0;
        thread.stop();
   
    }
       
    
    
    @Override
    public void run() {
        try {
            for(int i = 6; i > 0; i--) {
                randomDOGCoords();
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveL() {
         if(!snake.moveRi()){
           snake.setMovingDirection(Directions.LEFT);
         
         }
    }

    @Override
    public void moveR() {
      if(!snake.moveLeft())
      {
          snake.setMovingDirection(Directions.RIGHT);
            
      }   
      
    }

    @Override
    public void moveD() {
        if(!snake.moveUp()){
              snake.setMovingDirection(Directions.DOWN);
            
        }
    
    }

    @Override
    public void  moveU() {
     if(!snake.moveDown()){
              snake.setMovingDirection(Directions.UP);
            }
       
    }

    @Override
    public void draw(Graphics g) {
        for (int snakeDot = 0; snakeDot < snake.getSize(); snakeDot++) {
            g.drawImage(snake.getSnakeDotImage(), snake.getX(snakeDot), snake.getY(snakeDot), this);
        }
    }

    

    

    

    //movimenti
    private class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ev) {
            super.keyPressed(ev);
            int key = ev.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
              moveL();
            } else if (key == KeyEvent.VK_RIGHT) {
              moveR();
            } else if (key == KeyEvent.VK_UP ) {
                moveU();
            } else if (key == KeyEvent.VK_DOWN) {
                moveD();
            }else if (key == KeyEvent.VK_ENTER && !isPlaying) {
                startGame();
            }
        }
    }
}
