package ARCADE_PARK_DEF.breakout_game.entities;

import ARCADE_PARK_DEF.Disegna;
import ARCADE_PARK_DEF.breakout_game.Game;
import ARCADE_PARK_DEF.breakout_game.handlers.Input;
import java.awt.Color;
import java.awt.Graphics;



public class Paddle extends Entity implements Disegna
{
    private boolean left;
    private boolean right;
    //costruttore
    public Paddle()
    {
        left = false;
        right = false;

        x = Game.WIDTH/2 - 22;
        y = Game.HEIGHT - 20;

        width = 44; 
        height = 7;
    }
    //gestisce l'input
    public void HandleInput()
    {
        if(Game.input.isDown(Input.LEFT))
        {
            left = true;
        }
        if(Game.input.isDown(Input.RIGHT))
        {
            right = true;
        }

        if(Game.input.isReleased(Input.LEFT))
        {
            left = false;
        }
        if(Game.input.isReleased(Input.RIGHT))
        {
            right = false;
        }
    }
public void update()
    {
        if(left)
        {
            x -= 4;
            if(x < 10)
            {
                x = 10;
            }
        }
        if(right)
        {
            x += 4;

            if(x + width > Game.WIDTH - 10)
            {
                x = Game.WIDTH - 10 - width;
            }
        }
    }

        //colori racchetta 
  

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(61, 64, 72));
        g.fillRect((int)x+1, (int)y+1, width, height);
        g.setColor(new Color(255,0,0));
        g.fillRect((int)x, (int)y, width, height);    }
}