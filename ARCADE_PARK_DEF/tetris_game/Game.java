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
import ARCADE_PARK_DEF.Disegna;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


/**

* Gestisce il gioco Tetris. Tiene traccia della griglia.
 * Aggiorna il display ogni volta che lo stato del gioco cambia.
 * 
 * 
 */
public class Game  implements Disegna{

	private final Grid grid; // la griglia che compone il tabellone di Tetris
	
	public Tetris display;// l'immagine per il gioco Tetris

	private Piece piece;// il pezzo corrente che sta cadendo

	private boolean isOver; // il gioco è finito?

	/**
	 * Crea gioco Tetris
	 * 
     * @param display
	 */
	public Game(Tetris display) {
		
		this.display = display;
		grid = new Grid(display);
		
		
		int rand = (int ) (Math.random()*7);
		if (rand==0) {
			piece = new ZShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		else if(rand==1) {
        		piece = new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
        }
		else if (rand==2) {
        		piece = new JShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		else if(rand==3) {
			piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		else if (rand==4) {
			piece = new SShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		else if(rand ==5) {
			piece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		else if (rand ==6) {
			piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
		}
		
		
		isOver = false;
	}

	/**
	 *  Disegna lo stato attuale del gioco
	 * 
	 * @param g
	 *            il contesto grafico su cui disegnare
	 */
        @Override
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
		
		
		
		
	}

	/**
	 * Sposta il pezzo nella direzione data
	 * 
     * @param direction
	 * @param the
	 *            direzione in cui muoversi
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			piece.move(direction);
		}
		updatePiece();
        grid.checkRows();
        display.update();
		
                
	}
	
	
	
	   /** Ruota il pezzo*/
    public void rotatePiece()
    {
        if (piece != null) {
            piece.rotate();
                }
        updatePiece();
        grid.checkRows();
        display.update();
    }

    
    
    public void setIsOver(boolean b) {
    	isOver=b;
    }
    
    

	/**
	 * Restituisce vero se il gioco è finito
	 */
	public boolean isGameOver() {
// il gioco finisce se il pezzo occupa lo stesso spazio di un pezzo non vuoto
// parte della griglia. Di solito accade quando viene realizzato un nuovo pezzo
		if (piece == null) {
			return false;
		}

		// controlla se il gioco è già finito
		if (isOver) {
			return true;
		}

		// controlla ogni parte del pezzo
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	
	
	
	
	
	/** Aggiorna il pezzo */
	private void updatePiece() {
		if (piece == null) {
			int rand = (int ) (Math.random()*7);
			if (rand==0) {
				piece = new ZShape(1, Grid.WIDTH / 2 - 1, grid);
			}
			else if(rand==1) {
	        		piece = new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
	        }
			else if (rand==2) {
	        		piece = new JShape(1, Grid.WIDTH / 2 - 1, grid);
			}
			else if(rand==3) {
				piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
			}
			else if (rand==4) {
				piece = new SShape(1, Grid.WIDTH / 2 - 1, grid);
			}
			else if(rand ==5) {
				piece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
			}
			else if (rand ==6) {
				piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
			}
			}		
			
			
			
		

		// imposta le posizioni della griglia corrispondenti al pezzo bloccato
                // e poi rilascia il pezzo
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}

	}

	public void hardDrop(Direction down) {
		// TODO Auto-generated method stub
		if (piece != null) {
			piece.hardDrop(Direction.DOWN);
		}
		updatePiece();
        grid.checkRows();
        display.update();
	}

    @Override
    public void drawGameover(Graphics g) {
    }
        
       

	

}
