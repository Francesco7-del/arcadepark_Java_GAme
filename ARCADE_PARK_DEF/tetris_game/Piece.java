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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
// interfaccia del pezzo
public interface Piece {

	
	
	 void draw(Graphics g);
	 void move(Direction direction);
	 Point[] getLocations();
	 Color getColor();
	boolean canMove(Direction direction);
	void rotate();
	void hardDrop(Direction down);
	void drawInHold(Graphics g);
	void holdPiece();
	
	
	
}