/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCADE_PARK_DEF.tetris_game;



import ARCADE_PARK_DEF.Move;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import javax.swing.JRadioButton;
import javax.swing.Timer;

public class EventController extends KeyAdapter implements ActionListener , Move {

	private final Game game; // partita corrente: griglia e pezzo corrente
	public Timer timer;
	
	public boolean timerStatus=false;
	private  double PIECE_MOVE_TIME = 0.8; // aspetta 0,8 s ogni volta

	private boolean gameOver;
	private double delay;
	private final JButton button;
	private JRadioButton soundButton;
	/**
	* Crea un EventController per gestire eventi chiave e timer.
        *
        * 
        * @param gameTimer 
	 */
	public EventController(Game game, JButton b,  GameTimer gameTimer) {
		this.game = game;
		gameOver = false;
		button=b;
		
		button.addActionListener(new PauseController(button, this, soundButton, gameTimer));
		
		
		
		delay = 1000 * PIECE_MOVE_TIME; // in millisecondi
		timer = new Timer((int) delay, this);
		timer.setCoalesce(true); //se più eventi sono in sospeso, raggruppali in 1 evento		 
		 
		timer.start();
		
		
	
		
	}

	
	
	public void setPieceMoveTime(double a) {
		PIECE_MOVE_TIME=a;
		delay = 1000 * PIECE_MOVE_TIME; // in milliseconds
		timer.setDelay((int) delay);
	}
	/**
	* Risponde alla pressione di tasti .
        *
        * Attualmente risponde solo alla barra spaziatrice e al tasto q (uit)
	 */
        @Override
	public void keyPressed(KeyEvent e) {
		
		if (!gameOver) {
			
			if(timerStatus) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				moveD();
				
				break;
            case KeyEvent.VK_LEFT:
            	moveL() ;
				break;
            case KeyEvent.VK_RIGHT:
               moveR() ;
				break;
			case KeyEvent.VK_UP:
				moveU();
				
				break;
			case KeyEvent.VK_SPACE:
				handleHardDrop();
				break;
			case KeyEvent.VK_C:
				holdPiece();
				break;
				
			}
			}
		}
	}
	
	
	
	
	

	private void holdPiece() {
		// TODO Auto-generated method stub
		
		
	}



	private void handleHardDrop() {
		// TODO Auto-generated method stub
		game.hardDrop(Direction.DOWN);
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
		
	}



	/** Aggiorna periodicamente il gioco in base a un evento timer */
        @Override
	public void actionPerformed(ActionEvent e) {
		handleMove(Direction.DOWN);
	}

	/**
	 * Aggiorna il gioco muovendoti nella direzione indicata
	 */
	private void handleMove(Direction direction) {
		game.movePiece(direction);
		gameOver = game.isGameOver();
		if (gameOver)
			timer.stop();
	}

    @Override
    public void moveL() {
        	handleMove(Direction.LEFT);
    }

    @Override
    public void moveR() {
        	handleMove(Direction.RIGHT);
    }

    @Override
    public void moveD() {
        handleMove(Direction.DOWN);
    }

    @Override
    public void moveU() {
        game.rotatePiece();
			gameOver=game.isGameOver();
				if(gameOver)
					timer.stop();
    }
}
