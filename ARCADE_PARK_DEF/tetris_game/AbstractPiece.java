
package ARCADE_PARK_DEF.tetris_game;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class AbstractPiece implements Piece {

	
	
	private  boolean ableToMove; // pezzo 

	protected Square[] square; 
	// composto da  PIECE_COUNT 
	private final Grid grid; // dove si trova il blocco attuale

	
	
	
	private static final int PIECE_COUNT = 4;
	
	
	
	
	public AbstractPiece(int r, int c, Grid g) {
		// TODO Auto-generated constructor stub
		grid = g;
		
		square = new Square[PIECE_COUNT];
		
		
		ableToMove = true;
		
		
	
	}

	
	/**
	 * Disegna il pezzo nel contesto grafico dato
     * @param g
	 */
        @Override
	public void draw(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
			
		}
	}

        @Override
	public void drawInHold(Graphics g) {
		
		
	}

	
	
	/**
	 * Se possibile, sposta il pezzo, Blocca il pezzo se non può spostarsi verso il basso
         
	 * 
	 * @param direction
	 *            direzione del movimento
	 */
        @Override
	public void move(Direction direction) {
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// se non siamo riusciti a muoverci, controlla se siamo in fondo
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}
	
        @Override
	public void holdPiece() {
		grid.holdPiece();
		
	}
	
	/**
	* Ruota il pezzo
	*/
        @Override
	public void rotate()
	{
		
		if (canRotate()) {
			for (int i=0; i<PIECE_COUNT; i++) {
				square[i].rotate((int) getLocations()[1].getX(),(int) getLocations()[1].getY());
			}
			
		}
		
		///////////////////////////////////////////////////////
		
		
		
		
				
		}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Restituisce le coordinate della griglia (riga, colonna) occupate da questo pezzo
         *
         * @return un array di (row, col) Points
	 */
        @Override
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}
	
	
	
	
	
	/**
	 * Restituisci il colore di questo pezzo
     * @return 
	 */
        @Override
	public Color getColor() {
		// tutti i quadrati di questo pezzo hanno lo stesso colore
		return square[0].getColor();
	}

	
	
	
	
	
	/**
	 * Se questo pezzo può muoversi nella direzione data
	 * 
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		// Ogni quadrato deve essere in grado di muoversi in quella direzione
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}
	
	//può ruotare
	public  boolean canRotate() {
		if (!ableToMove)
			return false;
		
		boolean answer= true;
		
		for (int i=0; i< PIECE_COUNT; i++) {
			answer=answer && square[i].canRotate();
		}
		
		
		return answer;
		
		
		
	}
	
//caduta
        @Override
	public void hardDrop(Direction down) {
		
		while(canMove(Direction.DOWN)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(Direction.DOWN);
			
		}
		
		
		
		
	}
	
}