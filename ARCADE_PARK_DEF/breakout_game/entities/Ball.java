package ARCADE_PARK_DEF.breakout_game.entities;


import ARCADE_PARK_DEF.Disegna;
import ARCADE_PARK_DEF.breakout_game.Game;
import ARCADE_PARK_DEF.breakout_game.gamestate.PlayState;
import java.awt.Color;
import java.awt.Graphics;


public class Ball extends Entity implements Disegna
{	
    //costruttore
	public Ball()
	{
		x = Game.WIDTH/2 - 5;
		y = (Game.HEIGHT * 3)/4;
		
		width = 5;
		height = 5;
		
		dx = 1;
		dy = -1;
	}
	//modifica velocità pallina
	public void update(double speed)
	{
		boolean reverseYflag = false;
		boolean reverseXflag = false;
		//direzione palla asse x
		x += dx*speed;
		
		if(x+width >= Game.WIDTH-10 || x <= 10)
		{
			reverseX();
		}
		
		for(int i = 0; i < PlayState.ROWS; i++)
		{
			for(int j = 0; j < PlayState.COLS; j++)
			{
				if(PlayState.bricks[i][j].getState())
				{
					if(intersects(PlayState.bricks[i][j]))
					{
						if(!reverseXflag)
						{
							reverseX();
						}
						reverseXflag = true;
						PlayState.bricks[i][j].kill();
					}
				}
			}
		}
		//direzione palla asse y
		y += dy*speed;
		
		if(y <= 10)
		{
			reverseY();
		}
		
		for(int i = 0; i < PlayState.ROWS; i++)
		{
			for(int j = 0; j < PlayState.COLS; j++)
			{
				if(PlayState.bricks[i][j].getState())
				{
					if(intersects(PlayState.bricks[i][j]))
					{
						if(!reverseYflag)
						{
							reverseY();
						}
						reverseYflag = true;
						PlayState.bricks[i][j].kill();
					}
				}
			}
		}
		
		if(intersects(PlayState.player))
		{
			reverseY();
			y = PlayState.player.getY() - 10;
		}
	}
	
	public void reverseX()
	{
		dx *= -1;
	}
	
	public void reverseY()
	{
		dy *= -1;
	}

    @Override
    public void drawGameover(Graphics g) {
        }

    @Override
    public void draw(Graphics g) {
                g.setColor(new Color(61, 64, 72));
		g.fillOval((int)x+1, (int)y+1, width, height);
		g.setColor(new Color(29, 0, 252));
		g.fillOval((int)x, (int)y, width, height);    }
}
