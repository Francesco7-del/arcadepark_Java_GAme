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


/**
* Questa è la tavola Tetris rappresentata da una matrice (ALTEZZA per LARGHEZZA) di
 * Quadrati.
 *
 * Il quadrato in alto a sinistra è a (0,0). Il quadrato in basso a destra è a (HEIGHT -1,
 * LARGHEZZA -1).
 *
 * Dato un quadrato in (x, y), il quadrato a sinistra è in (x-1, y) il quadrato
 * sotto è a (x, y + 1)
 *
 * Ogni quadrato ha un colore. Un quadrato bianco è VUOTO; qualsiasi altro colore significa questo
 * il punto è occupato (cioè un pezzo non può spostarsi su / verso una casella occupata). UN
 * la griglia rimuoverà anche le righe completamente piene.
 * 
 * 
 */
public class Grid  implements Disegna{
	private final Square[][] board;
	private final Square[][] holdBoard;
	
	
	// Larghezza e altezza della griglia in numero di quadrati
	public static final int HEIGHT = 20;
	public static final int HOLD_HEIGHT = 5;
	public static final int WIDTH = 10;
	public static final int HOLD_WIDTH = 5;
	private static final int BORDER = 5;
	private static final int HOLD_BORDER = 3;
	public static final int LEFT = 100; // posizione del pixel a sinistra della griglia
	public static final int HOLD_LEFT = 550;
	public static final int TOP = 50;// posizione dei pixel nella parte superiore della griglia

	public static final Color EMPTY = Color.WHITE;
	
	public Tetris disp;

	/**
	 *Crea la griglia
	 * @param d 
	 */
	public Grid(Tetris d) {
		board = new Square[HEIGHT][WIDTH];
		holdBoard=new Square[HOLD_HEIGHT][HOLD_WIDTH];
		disp=d;
		
		// mette i quadrati nel tabellone
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				board[row][col] = new Square(this, row, col, EMPTY, false);

			}
		}

		for (int row = 0; row < HOLD_HEIGHT; row++) {
			for (int col = 0; col < HOLD_WIDTH; col++) {
				holdBoard[row][col] = new Square(this, row, col, EMPTY, false);

			}
		}

		
		
	}

	/**
	 * Restituisce vero se la posizione (riga, colonna) sulla griglia è occupata
	 * 
	 * @param row
	 *            la riga nella griglia
	 * @param col
	 *           la colonna nella griglia
     * @return 
	 */
	public boolean isSet(int row, int col) {
		return !board[row][col].getColor().equals(EMPTY);
	}

	/**
	* Cambia il colore del quadrato nella posizione specificata
        *
        * @param row
        * la riga del quadrato nella griglia
        * @param col
        * la colonna del quadrato nella griglia
        * @param c
        * il colore per impostare il quadrato
        * @throws IndexOutOfBoundsException
        *se riga <0 || riga> = LARGHEZZA || col <0 || col> = ALTEZZA
	 */
	public void set(int row, int col, Color c) {
		board[row][col].setColor(c);
		
	}
	
	

	/**
	* Controlla e rimuove tutte le righe piene di quadrati.
*
* Se una riga continua viene trovata e rimossa, tutte le righe sopra di essa vengono spostate in basso e
* la riga superiore impostata su vuota
	 */
        
        private void removeRow(int r)
        {
        	
        		
        	
           // cambia il colore di quella riga in bianco
            for (int col = 0; col < WIDTH; col++) {
                            set(r,col,EMPTY);
            }

            // sposta il resto della cosa verso il basso
            for (int row = r-1; row >= 0; row--) {
                for (int col = 0; col < WIDTH; col++) {
                    if(isSet(row,col))
                    {
                        Color c = board[row][col].getColor();
                        board[row][col].setColor(EMPTY);
                        board[row+1][col].setColor(c);  
                    }

                }
            }
            
            
        }


        public void checkRows() {
            int col,row;
            for (row = 0; row< HEIGHT; row++) {
                for( col = 0; col < WIDTH; col++) {
                    if(!isSet(row,col)) break;
                }
                if(col == WIDTH) // viene trovata una riga
                {
                    removeRow(row);
                  disp.updateScore();
                }
            } 

        }

	/**
	* Disegna la griglia nel contesto grafico dato
     * @param g
	 */
        
        @Override
	public void draw(Graphics g) {
  // disegna i bordi come rettangoli: sinistra, destra in blu e poi in basso in rosso
		g.setColor(Color.BLUE);
		g.fillRect(LEFT - BORDER, TOP, BORDER, HEIGHT * Square.HEIGHT);
		g.fillRect(LEFT + WIDTH * Square.WIDTH, TOP, BORDER, HEIGHT
				* Square.HEIGHT);
		
		g.fillRect(HOLD_LEFT - HOLD_BORDER, TOP, HOLD_BORDER, HOLD_HEIGHT * Square.HEIGHT);
		g.fillRect(HOLD_LEFT + HOLD_WIDTH * Square.WIDTH, TOP, HOLD_BORDER, HOLD_HEIGHT
				* Square.HEIGHT);
		
		
		g.setColor(Color.RED);
		g.fillRect(LEFT - BORDER, TOP + HEIGHT * Square.HEIGHT, WIDTH
				* Square.WIDTH + 2 * BORDER, BORDER);
		
		g.fillRect(HOLD_LEFT - HOLD_BORDER, TOP + HOLD_HEIGHT * Square.HEIGHT, HOLD_WIDTH
				* Square.WIDTH + 2 * HOLD_BORDER, HOLD_BORDER);

		// disegna tutti i quadrati nella griglia
// svuota prima quelli (per evitare di mascherare le linee nere dei pezzi che sono già caduti)
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
		
		for (int r = 0; r < HOLD_HEIGHT; r++) {
			for (int c = 0; c < HOLD_WIDTH; c++) {
				if (holdBoard[r][c].getColor().equals(EMPTY)) {
					holdBoard[r][c].draw(g);
				}
			}
		}
		
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (!board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
		
		for (int r = 0; r < HOLD_HEIGHT; r++) {
			for (int c = 0; c < HOLD_WIDTH; c++) {
				if (!holdBoard[r][c].getColor().equals(EMPTY)) {
					holdBoard[r][c].draw(g);
				}
			}
		}


		
	}

	///////////////////////////////////
	
	
	public void holdPiece() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void drawGameover(Graphics g) {
    }

    
}