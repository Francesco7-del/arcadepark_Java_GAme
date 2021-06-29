package ARCADE_PARK_DEF.breakout_game.gamestate;

import ARCADE_PARK_DEF.breakout_game.Game;
import ARCADE_PARK_DEF.breakout_game.handlers.GSM;
import java.awt.Graphics2D;

//classe astratta utilizzata per il titolo e per giocare

public abstract class GameState 
{
	public GSM gsm;
	public Game game;
	
	public GameState(GSM gsm) 
	{
		this.gsm = gsm;
		game = gsm.getGame();
	}
	
	public abstract void handleInput();
	public abstract void update();
	public abstract void render(Graphics2D g);
	public abstract void dispose();
}
