package ARCADE_PARK_DEF.space;
//costanti e instanziamento Vector



public interface Constants {
   int SPEED = 6;
 Vector LEFT = new Vector(-SPEED,0);
 Vector RIGHT = new Vector(SPEED,0);
 Vector UP = new Vector(0,-SPEED);
 Vector DOWN = new Vector(0,SPEED);
 Vector STOP = new Vector(0,0);
 Vector FOE_LEFT = new Vector(-3,0);
 Vector FOE_RIGHT = new Vector(3,0);
Vector FOE_DOWN = new Vector(0,1);
Vector SHIP_BULLET = new Vector(0, -15);
Vector FOE_BULLET = new Vector(0, 15);
   

    
}
