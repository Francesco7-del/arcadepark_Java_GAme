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
 *Un quadrato sulla nostra griglia Tetris o un quadrato nel nostro pezzo di gioco Tetris
 * 
 * 
 */
public class Square  implements Disegna{
	private Grid grid; //l'ambiente in cui si trova questo quadrato

	private int row, col; // la posizione della griglia di questa quadrato

	private boolean ableToMove; // vero se questo quadrato può muoversi

	private Color color; // il colore di questo quadrato

	// le possibili direzioni di movimento sono definite dalla classe di gioco

	// dimensioni dei quadrati
	public static final int WIDTH = 20;
	public static final int HOLD_WIDTH = 5;
	public static final int HEIGHT = 20;
	public static final int HOLD_HEIGHT = 5;
	/**
	* Crea un quadrato
*
* @param g
* la griglia per questo quadrato
* @param row
* la riga di questo quadrato nella griglia
* @param col
* la colonna di questo quadrato nella griglia
* @param c
* il colore di questo quadrato
* @param mobile
* vero se questo quadrato può muoversi
*
* @throws IllegalArgumentException
* se riga e colonna non sono all'interno della griglia
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);
		
		

		// inizializza le variabili di istanza
		grid = g;
		this.row = row;
		this.col = col;
		color = c;
		ableToMove = mobile;
	}

	/**
	 *Restituisce la riga per questo quadrato
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Restituisce la colonna per questo quadrato
	 */
	public int getCol() {
		return col;
	}
	
	
	public void setRow(int a) {
		row=a;
	}
	
	public void setCol(int a) {
		col=a;
	}
	
	

	/**
	 * Restituisce vero se questo quadrato può spostarsi di 1 punto nella direzione d
*
* @param direction
* la direzione per verificare l'eventuale movimento
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		boolean move = true;
		// se la direzione data è bloccata, non possiamo muoverci
                // ricorda di controllare i bordi della griglia
		switch (direction) {
		case DOWN:
			if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
				move = false;
			break;

		// attualmente non supporta il controllo di SINISTRA o DESTRA
// MODIFICA in modo che ritorni correttamente se può spostarsi a sinistra oa destra
		case LEFT:
                        if(col==0 || grid.isSet(row, col-1)) 
                            move=false;
                        break;
		case RIGHT:
                       
						if(col==WIDTH-1 || grid.isSet(row, col+1)) 
							move=false;
                        break;
		}
		return move;
	}

	public boolean canRotate() {
		// TODO Auto-generated method stub
		if (!ableToMove)
			return false;
		
		boolean move=true;
		
		if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col)) {
			move = false;
		}
		
		if(col==0 || grid.isSet(row, col)  ||  col==(Grid.WIDTH-1) || grid.isSet(row, col)  ) {
            move=false;
		}
		
		
		return move;
		
		
	}
	
	
	
	/**
	 * Se possibile, sposta questa casella nella direzione data
*
* Il quadrato non si muoverà se la direzione è bloccata o se lo è
* impossibilitato a muoversi.
*
* Se tenta di spostarsi verso il BASSO e non ci riesce, la casella è congelata e non può
* spostati più
*
* @param direction
* la direzione in cui muoversi
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
			case DOWN:
				row++;
				break;
            case LEFT:
                 col--;
                 break;
            case RIGHT:
                 col++;         
                 break;
			}
		}
	}

	
	//rotazioni
	public void rotate(int a, int b) {
		if (canRotate()) {
			boolean canRotate= true;
			
			int index1RowPos=a;
			int index1ColPos=b;

			
				
					
					int finalRow=this.row;
					int finalCol=this.col;
					
					
					int newRow = index1RowPos;
					int newCol=index1ColPos;
					
					if (this.getRow()==index1RowPos-1) {
						newCol=index1ColPos+1;
						
					}
					
					if (this.getCol()==index1ColPos-1) {
						newRow=index1RowPos-1;
					}
					
					if (this.getRow()==index1RowPos+1) {
						newCol=index1ColPos-1;
						
					}
					
					if (this.getCol()==index1ColPos+1) {
						newRow=index1RowPos+1;
					}
					
					if (this.getCol()==index1ColPos+2) {
						newRow=index1RowPos+2;
					}
					
					if (this.getCol()==index1ColPos-2) {
						newRow=index1RowPos-2;
					}
					
					if (this.getRow()==index1RowPos+2) {
						newCol=index1ColPos-2;
						
					}
					
					if (this.getRow()==index1RowPos+2) {
						newCol=index1ColPos-2;
						
					}
					
					
					if ( canRotate()) {
						
						finalRow=newRow;
						finalCol=newCol;
					}
					
					
					
					
					
					this.setRow(finalRow);
					this.setCol(finalCol);
					
				
					
		}
			
			
		
		
	}
	
	
	
	/**
	 * Cambia il colore di questo quadrato
*
* @param c
* il nuovo colore
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Ottiene il colore di questo quadrato
	 */
	public Color getColor() {
		return color;
	}

	/**
	 *Disegna questo quadrato nel contesto grafico dato
	 */
       
        @Override
	public void draw(Graphics g) {

		// calcola la coordinata in alto a sinistra (x, y) di questo quadrato
		int actualX = Grid.LEFT + col * WIDTH;
		int actualHoldX = Grid.HOLD_LEFT + col*HOLD_WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		int actualHoldY = Grid.TOP + row * HOLD_HEIGHT;
		
		
		
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		g.fillRect(actualHoldX, actualHoldY, HOLD_WIDTH, HOLD_HEIGHT);
		
		// bordo nero (se non vuoto)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
		
		
		
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualHoldX, actualHoldY, HOLD_WIDTH, HOLD_HEIGHT);
		}
	
		
	}

    @Override
    public void drawGameover(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	

	
	
}