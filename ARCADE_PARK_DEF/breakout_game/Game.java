package ARCADE_PARK_DEF.breakout_game;


import ARCADE_PARK_DEF.Disegna;
import ARCADE_PARK_DEF.Move;
import ARCADE_PARK_DEF.breakout_game.handlers.GSM;
import ARCADE_PARK_DEF.breakout_game.handlers.Input;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


import javax.swing.JPanel;



public class Game extends JPanel implements Runnable, KeyListener , Move, Disegna
{
	public static int level = 4;
	public static int lives = 3;
	
	
	// Dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	
	// Game Thread
	private Thread thread;
	private boolean running;
      
	private final int FPS = 60;
	private final long targetTime = 1000 / FPS;
	
	// Image
	private BufferedImage image;
	private Graphics2D g;
	
	// Gamestate Manager
	private GSM gsm;
	
	// Input
	public static Input input;
	
	public Game()
	{
		
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();

	}
	
        @Override
	public void addNotify() 
	{
		super.addNotify();
		if(thread == null) 
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;	
		gsm = new GSM(this);
		input = new Input();
	}
	
        @Override
	public void run() 
	{
		init();
                long start;
		long elapsed;
		long wait;

                
                
		// Game Loop

                while(running) 
		{
			
                    
			start = System.nanoTime();
			
			update();
			draw_();
                     
                        Graphics g2 = getGraphics();
			draw(g2);
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try 
			{
				Thread.sleep(wait);
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
               
        } 
   
	
        private void update()
	{
		gsm.update();
		input.update();
	}
	private void draw_() 
	{
               // Disegna dis =  gsm ;
               // dis.draw(g);
	gsm.draw(g);
	}
	
	
	
        @Override
	public void keyTyped(KeyEvent key) {}
        @Override
      
	public void keyPressed(KeyEvent key) 
	{
            
                if(key.getKeyCode() == KeyEvent.VK_UP) {moveU();};
		if(key.getKeyCode() == KeyEvent.VK_DOWN) {moveD();};
		if(key.getKeyCode() == KeyEvent.VK_LEFT) {moveL();};
		if(key.getKeyCode() == KeyEvent.VK_RIGHT) {moveR();}
		if(key.getKeyCode() == KeyEvent.VK_SPACE) Game.input.setKey(Input.SPACE, true);
		if(key.getKeyCode() == KeyEvent.VK_ENTER) Game.input.setKey(Input.ENTER, true);
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) Game.input.setKey(Input.ESCAPE, true);
	}
        @Override
	public void keyReleased(KeyEvent key) 
	{
		if(key.getKeyCode() == KeyEvent.VK_UP) Game.input.setKey(Input.UP, false);
		if(key.getKeyCode() == KeyEvent.VK_DOWN) Game.input.setKey(Input.DOWN, false);
		if(key.getKeyCode() == KeyEvent.VK_LEFT) Game.input.setKey(Input.LEFT, false);
		if(key.getKeyCode() == KeyEvent.VK_RIGHT) Game.input.setKey(Input.RIGHT, false);
		if(key.getKeyCode() == KeyEvent.VK_SPACE) Game.input.setKey(Input.SPACE, false);
		if(key.getKeyCode() == KeyEvent.VK_ENTER) Game.input.setKey(Input.ENTER, false);
		if(key.getKeyCode() == KeyEvent.VK_ESCAPE) Game.input.setKey(Input.ESCAPE, false);
	}

    @Override
    public void moveL() {
        Game.input.setKey(Input.LEFT, true);
    }

    @Override
    public void moveR() {
        Game.input.setKey(Input.RIGHT, true);
    
    }

    @Override
    public void moveD() {
        Game.input.setKey(Input.DOWN, true);
    }

    @Override
    public void moveU() {
        Game.input.setKey(Input.UP, true);
        

    }

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
             		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		        g.dispose(); 
             
    }
    
    

}