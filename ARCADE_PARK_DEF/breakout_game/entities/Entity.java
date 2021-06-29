package ARCADE_PARK_DEF.breakout_game.entities;

import java.awt.Rectangle;

///classe astratta contenenti le coordinte
public abstract class Entity 
{
	public double x;
	public double y;
	 public double dx;
	public  double dy;
	public int width;
	public int height;
	
	public Entity() { }
	
	public int getX() { return (int)x; }
	public int getY() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	//interseca
	public boolean intersects(Entity e)
	{
		Rectangle r1 = getRectangle();
		Rectangle r2 = e.getRectangle();
		
		return r1.intersects(r2);
	}
	//rettangoli
	public Rectangle getRectangle()
	{
		return new Rectangle((int)x, (int)y, width, height);
	}
	//posizione
	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
        //vettore
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	
        
}
