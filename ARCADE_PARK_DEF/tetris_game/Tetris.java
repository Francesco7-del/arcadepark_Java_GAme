/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCADE_PARK_DEF.tetris_game;


/**
 * Crea e controlla il gioco Tetris.
 * 
 *
 *
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class Tetris extends JPanel {

	private final Game game;
	private final Boolean isPaused;
	private JButton button;
	private final JButton buttonReplay;
	private static JFrame f;
	public JLabel score;
	public int s;
	public boolean gameOver;
	private final JLabel timerLabel;
	private final JLabel labelLevel;
	private int level=1;
	private final EventController ec;
	private double pieceMoveTime=0.7;
	/**
	 * Imposta le parti per il gioco, il display e il controllo utente di Tetris
	 */
	public Tetris() {
		
		
		game = new Game(this);
		isPaused=false;
		s=0;
		gameOver=false;
		
		
		
		
		f=new JFrame("Tetris Game");
		f.getContentPane().add(this);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(680, 530);
                f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		
	
		setBackground(Color.white);
		setLayout(null);
		
		timerLabel = new JLabel("5 : 00");
		timerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timerLabel.setBounds(332, 167, 161, 40);
		add(timerLabel);
		
		
		GameTimer gameTimer=new GameTimer(game, timerLabel, this);
		
		JButton button = new JButton("START");
		button.setBounds(377, 23, 103, 71);
		add(button);
		
		
		buttonReplay = new JButton("REPLAY");
		buttonReplay.setBounds(377, 83, 103, 71);
		add(buttonReplay);
		
		
		buttonReplay.setEnabled(false);
		
		
		score = new JLabel("0");
		score.setFont(new Font("Chalkduster", Font.BOLD, 50));
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setBounds(332, 287, 148, 64);
		add(score);
		
		
		
		
		JLabel lblYourScore = new JLabel(" Score:");
		lblYourScore.setFont(new Font("Chalkduster", Font.BOLD, 30));
		lblYourScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourScore.setBounds(300, 237, 246, 51);
		add(lblYourScore);
		
		
	
		
	
		 ec = new EventController(game, button,  gameTimer);
		
		
		ec.timer.stop();
		f.addKeyListener(ec);
		
		
		labelLevel = new JLabel("Level 1");
		labelLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		labelLevel.setHorizontalAlignment(SwingConstants.CENTER);
		labelLevel.setBounds(220, 22, 100, 30);
		add(labelLevel);
		
		
		
		
		
		
	
		
	}
	/**
	 *Aggiorna il display
	 */
	public void update() {
		repaint();	
		
		
	}
	
	
	public void increaseLevel() {
		level++;
		JLabel levelUp = new JLabel("LEVEL UP!!!");
		levelUp.setFont(new Font("Chalkduster", Font.BOLD, 50));
		levelUp.setHorizontalAlignment(SwingConstants.CENTER);
		levelUp.setBounds(100, 237, 346, 151);
		f.add(levelUp);
		
		
		if (pieceMoveTime>0.4) {
			pieceMoveTime-=0.05;
		}
		ec.setPieceMoveTime(pieceMoveTime);
		labelLevel.setText("Level "+level);
	}
	
	
	public void updateScore() {
		s=s+100;
		if (s>=1000) {
			score.setFont(new Font("Chalkduster", Font.BOLD, 40) );
		}
		else if (s>=3000) {
			score.setFont(new Font("Chalkduster", Font.BOLD, 30));
		}
		else if (s>=10000) {
			score.setFont(new Font("Chalkduster", Font.BOLD, 20));
		}
		score.setText(s+"");
	}

	
	
	
	
	
	/**
	 * disegna componenti game over 
	 */
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g);
		if (game.isGameOver()) {
			
	
			g.setFont(new Font("Palatino", Font.BOLD, 40));
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER ", 80, 300);
			
			gameOver=true;
			

			
			buttonReplay.setEnabled(true);
			ReplayHandler replayHandler= new ReplayHandler();
			
			buttonReplay.addActionListener(replayHandler);
			
		}
	}

	
}