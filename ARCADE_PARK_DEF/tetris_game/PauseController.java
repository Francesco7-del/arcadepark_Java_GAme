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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class PauseController implements ActionListener {

	private Boolean isPaused;
	public Boolean isStarted=false;
	private final JButton button;
	private final EventController ec;
	
	private final GameTimer gameTimer;
	//privato Game game;
	public PauseController(JButton b, EventController e, JRadioButton r, GameTimer gameTimer) {
		// TODO Auto-generated constructor stub
		button =b;
		ec=e;
		
		this.gameTimer=gameTimer;
		
		button.addKeyListener(ec);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		// se inizia
		if(!isStarted) {
			isStarted=true;
			button.setText("||");
			isPaused=false;
			
			ec.timer.start();
			gameTimer.start();
			ec.timerStatus=true;
		}
		
		//se è in pausa
		else {
		if (!isPaused ) {
			button.setText("|>");
			isPaused=true;
			
			ec.timer.stop();
			//gameTimer.pause();
//			SoundButton.setEnabled(true);
			ec.timerStatus=false;
			
			}
			//riprende
		else if(isPaused){
			button.setText("||");
			isPaused=false;
			ec.timer.start();
			
			ec.timerStatus=true;
			
		}
		
		
		
			
			
		
		}
		
	}

}