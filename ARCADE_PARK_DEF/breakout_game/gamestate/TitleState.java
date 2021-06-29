package ARCADE_PARK_DEF.breakout_game.gamestate;

import ARCADE_PARK_DEF.breakout_game.Game;
import ARCADE_PARK_DEF.breakout_game.handlers.GSM;
import ARCADE_PARK_DEF.breakout_game.handlers.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



public class TitleState extends GameState
{
	int counter = 0;
	int state = 0;
        //costruttore
	public TitleState(GSM gsm)
	{
		super(gsm);
	}
	
        @Override
	public void handleInput()
	{
		if(game.input.isPressed(Input.ENTER) || game.input.isPressed(Input.SPACE))
		{
			gsm.setState(GSM.PLAY);
		}
		if(game.input.isPressed(Input.ESCAPE))
		{
			System.exit(0); 
		}
		if(game.input.isPressed(Input.UP))
		{
			Game.level++; 
		}
		if(game.input.isPressed(Input.DOWN))
		{
			Game.level--; 
		}
	}
        
        @Override
	public void update()
	{
		if(counter<30)
		{
			counter++;
		}
		else
		{
			counter = 0;
			state++;
			if(state > 2 )
			{
				state = 0;
			}
		}
	}
        //disegna titolo
        @Override
	public void render(Graphics2D g)
	{
		if(state == 0){g.setColor(Color.ORANGE);};
		if(state == 1){g.setColor(Color.blue);};
		if(state == 2){g.setColor(Color.red);};
		
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		
		g.setFont(new Font("Monospace", Font.PLAIN, 26)); 
		
		g.setColor(new Color(255,255,0));
		g.drawString("BRICK BREAKER", Game.WIDTH/53, Game.HEIGHT/2-5);
		g.drawString("BRICK BREAKER", Game.WIDTH/54, Game.HEIGHT/2-6);
		g.drawString("BRICK BREAKER", Game.WIDTH/55, Game.HEIGHT/2-6);
		
		g.setColor(new Color(0, 0,0));
		g.drawString("BRICK BREAKER", Game.WIDTH/50, Game.HEIGHT/2-5);
	}
        @Override
	public void dispose()
	{
		
	}
}
