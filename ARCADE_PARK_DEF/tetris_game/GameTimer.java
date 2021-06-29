/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCADE_PARK_DEF.tetris_game;

/**
 *
 * @author User
 */
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
//tempo del gioco
public class GameTimer{
	
	private int secondsPassed=0;
	private int displaySecond=0;
	private int currMin=4;
	private final Game game;
	private Timer timer;
	private final TimerTask task;
	private final Tetris tetris;
	private final JLabel timerLabel;
	private double i=1.0;
	public GameTimer(Game game, JLabel timerLabel, Tetris tetris) {
		this.game=game;
		this.timerLabel=timerLabel;
		 timer= new Timer();
		 this.tetris=tetris;
		 
		 task = new TimerTask() {
		 
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				secondsPassed++;
				if (displaySecond>59) {
					displaySecond=0;
					currMin--;
				}
				displaySecond++;
				
				if (currMin==0 && displaySecond>30) {
				timerLabel.setForeground(Color.red);
				}
				else {
					timerLabel.setForeground(Color.black);
				}
				
					if (displaySecond>50) {
						timerLabel.setText("0"+currMin +" : "+ "0"+(60-displaySecond)+"");
					}
					
					
					else {
					timerLabel.setText("0"+currMin +" : "+(60-displaySecond)+"");
					}
				
				
				
				
				if ( Integer.parseInt(tetris.score.getText())>=i*2000) {
					tetris.increaseLevel();		
					timer=new Timer();
					secondsPassed=0;
					displaySecond=0;
					currMin=4;
					i=i+1.25;
				}
					else if(secondsPassed>60*5-1) {
					game.setIsOver(true);
					timer.cancel();
					}
					
					
				}
			
			
		};
		
		
		
	}
	
	//stati del gioco
	public void start() {
		
		timer.scheduleAtFixedRate(task, 1000, 1000);
		
	}
	
	public void pause() {
		timer.cancel();
		
	}
	
	public void resume() {
		timer.schedule(task, 1000);
	}

	public int getSecondsPassed(){
		return secondsPassed;
	}
	
	
	
	
	
}