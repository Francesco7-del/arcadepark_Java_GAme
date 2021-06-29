
package ARCADE_PARK_DEF.battaglia_navale_game;


import ARCADE_PARK_DEF.Disegna;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import static javax.swing.BorderFactory.createTitledBorder;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public final class PlayerFinalBoard extends JPanel implements Disegna  {

    private final String name;
    private final int boardRow;
    private final int boardCol;
    private JButton[][] cellArray, temp;
    private final ArrayList<JButton> cells;
    private ArrayList<JButton> cells1;
    private Graphics g;
    //pannello finale del giocatore
    public PlayerFinalBoard(String name, int boardRow, int boardCol, ArrayList<JButton> cells, JButton[][] temp) {
        this.name = name;
        this.boardRow = boardRow;
        this.boardCol = boardCol;
        this.cells = cells;
        this.temp = temp;
       draw(g);
    }

   /* private void init() {
      
    }*/

    private void setShips() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (cells.contains(temp[i][j])) {
                    cellArray[i][j].setBackground(Color.DARK_GRAY);
                    cells1.add(cellArray[i][j]);
                }
            }
        }
    }
    
    /**
     * Imposta casualmente le navi per il lato computer.
     * @param name Il nome di ogni nave.
     * @param shipSize Le dimensioni della nave.
     */
    private void setRandomShip(String name, int shipSize) {
        int verticalOrHorizontal = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        int row = ThreadLocalRandom.current().nextInt(0, boardRow);
        int col = ThreadLocalRandom.current().nextInt(0, boardCol);
        boolean boo = true;
        while (boo) {
            if (checkBorders(row, col, shipSize, verticalOrHorizontal)) {
                boo = false;
                if (verticalOrHorizontal == 1) {
                    for (int k = 0; k < shipSize; k++) {
                        cells.add(cellArray[row + k][col]);
                        cellArray[row + k][col].setName(name);
                    }
                } else if (verticalOrHorizontal == 0) {
                    for (int k = 0; k < shipSize; k++) {
                        cells.add(cellArray[row][col + k]);
                        cellArray[row][col + k].setName(name);
                    }
                }
            } else {
                row = ThreadLocalRandom.current().nextInt(0, boardRow);
                col = ThreadLocalRandom.current().nextInt(0, boardCol);
            }
        }
    }
    
    /**
     * Funziona come un "input" per setRandomShip (). Ha un input diverso per ogni nave.
     */
    private void setRandomShips() {
        setRandomShip("AircraftCarrier", 5);
        setRandomShip("Battleship", 4);
        setRandomShip("Destroyer", 3);
        setRandomShip("Submarine", 3);
        setRandomShip("PatrolBoat", 2);
    }

    private boolean checkBorders(int row, int col, int length, int VerticalOrHorizontal) {
        if (VerticalOrHorizontal == 1) {
            if (row + length - 1 < boardRow) {
                for (int z = 0; z < length; z++) {
                    if (cells.contains(cellArray[row + z][col])) {
                        return false;
                    }
                }
                return true;
            }
        } else if (VerticalOrHorizontal == 0) {
            //controlla il bordo
            if (col + length - 1 < boardCol) {
                for (int z = 0; z < length; z++) {
                    if (cells.contains(cellArray[row][col + z])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public ArrayList<JButton> getCells() {
        if (name.equals("Computer")) {
            return cells;
        } else {
            return cells1;
        }
    }

    public JButton[][] getCellArray() {
        return cellArray;
    }

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
  cells1 = new ArrayList<JButton>();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        TitledBorder titledBorder = createTitledBorder(new EmptyBorder(50, 50, 25, 50), name + "");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(titledBorder);
        cellArray = new JButton[boardRow][boardCol];
        for (int i = 0; i < boardRow; i++) {
            c.gridy = i;
            for (int j = 0; j < boardCol; j++) {
                cellArray[i][j] = new JButton();
                cellArray[i][j].setPreferredSize(new Dimension(40, 40));
                cellArray[i][j].setBackground(Color.CYAN);
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                cellArray[i][j].setBorder(border);
                c.gridx = j;
                this.add(cellArray[i][j], c);
            }
        }
        
        
        if (name.equals("Computer")) {
            setRandomShips();
            /**
             * Visualizza il lato computer della scheda come suggerimento nella console. 
             */

        } else {
            setShips();
        }    
    
    }
}
