package ARCADE_PARK_DEF.breakout_game.handlers;

import ARCADE_PARK_DEF.Disegna;
import ARCADE_PARK_DEF.breakout_game.Game;
import ARCADE_PARK_DEF.breakout_game.gamestate.GameState;
import ARCADE_PARK_DEF.breakout_game.gamestate.PlayState;
import ARCADE_PARK_DEF.breakout_game.gamestate.TitleState;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GSM  implements Disegna
{
	private final Game game;
	
	private GameState gameState;
	public static final int PLAY = 0;
	public static final int TITLE = 1;
	//costruttore
	public GSM(Game game)
	{
		this.game = game;
		setState(TITLE);
	}
	
	public Game getGame()
	{
		return game;
	}
	//imposta lo stato
	public void setState(int i)
	{
		if(gameState != null)
		{
			gameState.dispose();
		}
		if(i == PLAY)
		{
			gameState = new PlayState(this);
		}
		if(i == TITLE)
		{
			gameState = new TitleState(this);
		}
	}
	//aggiorna
	public void update() 
	{
		gameState.update();
		gameState.handleInput();
	}
	
        
      
    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g1) {

		g1.setColor(Color.black);
		g1.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		gameState.render((Graphics2D) g1); 
    
    }
}
