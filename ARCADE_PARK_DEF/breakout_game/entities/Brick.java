package ARCADE_PARK_DEF.breakout_game.entities;

import ARCADE_PARK_DEF.Disegna;

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends Entity implements Disegna
{
	public boolean isAlive;
	private boolean collidable;
	private boolean flash;
	private int counter;
	//costruttore
	public Brick(double x, double y)
	{
		this.x = x;
		this.y = y;
		
		width = 20;
		height = 10;
		
		collidable = true;
		isAlive = true;
		flash = false;
		
		counter = 0;
	}
	
	public void kill()
	{
		collidable = false;
		flash = true;
	}
	
	public boolean getState() { return collidable; }
	

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
	
		if(flash)
		{
			counter++;
			
			if(counter < 2)
				g.setColor(new Color(0, 0, 0));
			else
			g.setColor(new Color(255, 255, 255));
			
			if(counter == 6)
			{
				flash = false;
				isAlive = false;
			}
			g.fillOval((int)x-counter, (int)y-counter, width+counter, height+counter);
		}
		else
		{
			g.setColor(new Color(255, 0, 0));
			g.fillRect((int)x+1, (int)y+1, width, height);
			g.setColor(new Color(201, 179, 179));
			g.fillRect((int)x, (int)y, width, height);
		}    }
}
