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

/**
 * Un pezzo a forma di L nel gioco Tetris.
 *
 * Questo pezzo è composto da 4 quadrati nella seguente configurazione:
 *
 * Mq <br>
 * Mq <br>
 * Mq Mq <br>
 *
 * Il pezzo di gioco "galleggia sopra" la griglia. Le coordinate (row, col) sono le
 * posizione del quadrato centrale sul lato all'interno della griglia
 * 
 * 
 */
public class BarShape extends AbstractPiece {
	
	
	
	
	/**
	* Crea un pezzo a forma di L. Vedere la descrizione della classe per l'ubicazione effettiva di r
* e C
*
* @param r
* posizione della riga per questo pezzo
* @param c
* posizione della colonna per questo pezzo
* @param g
* la griglia per questo pezzo di gioco
	 * 
	 */
	public BarShape(int r, int c, Grid g) {
		
		super(r,c,g);

		// Crea i quadrati
		square[0] = new Square(g, r + 1, c, Color.cyan, true);  
		square[1] = new Square(g, r+1, c+1, Color.cyan, true);      
		square[2] = new Square(g, r + 1, c+2, Color.cyan, true);  
		square[3] = new Square(g, r + 1, c + 3, Color.cyan, true);
		
	}

	
	

	

	
}
