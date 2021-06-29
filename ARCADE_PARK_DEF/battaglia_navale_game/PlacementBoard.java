
package ARCADE_PARK_DEF.battaglia_navale_game;

import ARCADE_PARK_DEF.Disegna;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import static javax.swing.BorderFactory.createTitledBorder;

public class PlacementBoard extends JPanel implements Disegna{

    private final int boardRow;
    private final int boardCol;

    private ArrayList<JButton> yellowedCells, yellowCellsToRemove;
    private final Ship aircraftCarrier;
    private final Ship battleship;
    private final Ship submarine;
    private final Ship destroyer;
    private final Ship patrolBoat;
    private JButton[][] cellArray;
    private final JButton startGameBtn;
    private Graphics g;
    /**
     * Rende sia il tabellone del giocatore che quello del computer tramite init () con
     * sono funzioni / costruttori ecc.
     *
     * @param boardRow Integer Variabile per il numero di righe della scheda.
     * @param boardCol Integer Variabile per il numero di colonne della bacheca.
     * @param aircraftCarrier Nuovo oggetto AircraftCarrier per rappresentare il file
     * Nave portaerei.
     * @param battleship Nuovo oggetto corazzata per rappresentare la portaerei
     * Nave.
     * @param submarine Nuovo oggetto sottomarino per rappresentare la nave sottomarina.
     * @param patrolBoat Nuovo oggetto PatrolBoat per rappresentare la motovedetta
     * Nave.
     * @param destroyer Nuovo oggetto Destroyer per rappresentare la nave distruttore.
     * @param startGameBtn Il JButton Start
     */
    //scheda di posizionamento
    public PlacementBoard(int boardRow, int boardCol, Ship aircraftCarrier, Ship battleship, Ship submarine, Ship patrolBoat, Ship destroyer, JButton startGameBtn) {
        this.boardCol = boardCol;
        this.boardRow = boardRow;
        this.aircraftCarrier = aircraftCarrier;
        this.battleship = battleship;
        this.submarine = submarine;
        this.patrolBoat = patrolBoat;
        this.destroyer = destroyer;
        this.startGameBtn = startGameBtn;
       draw(g);
    }
//il pannello del giocatore

    @Override
    public void draw(Graphics g) {
yellowedCells = new ArrayList<>();
        yellowCellsToRemove = new ArrayList<>();
        TitledBorder tl = createTitledBorder(new EmptyBorder(50, 50, 25, 25), "IL TUO PANNELLO");
        tl.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(tl);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        cellArray = new JButton[boardRow][boardCol];
        for (int i = 0; i < boardRow; i++) {
            c.gridy = i;
            for (int j = 0; j < boardCol; j++) {
                final int k = i;
                final int l = j;
                cellArray[i][j] = new JButton();
                cellArray[i][j].setPreferredSize(new Dimension(45, 45));
                cellArray[i][j].setBackground(Color.CYAN);
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                cellArray[i][j].setBorder(border);
                c.gridx = j;
                //eventi mouse
                cellArray[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            leftClick(k, l);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        previewShip(k, l);
                    }

                    public void mouseExited(MouseEvent e) {
                        cleanBoard();
                    }
                });
                this.add(cellArray[i][j], c);
            }
        }

        /**
         * Il listener di azioni ha: 
         * 1. Non farlo
         * cambia la selezione della nave mentre ne viene selezionata un'altra. 
         * 2.
         * Non sovrascrivere quando si posiziona ogni nave. È possibile selezionare una sola nave
         */
        //quando selezioni le barche
        for (int i = 0; i < 5; i++) {
            aircraftCarrier.getBtn()[i].addActionListener(e -> {
                if (((JButton) e.getSource()).getBackground() == Color.YELLOW) {
                    setPanelButtonEnable(aircraftCarrier, true);
                } else {
                    setPanelButtonEnable(aircraftCarrier, false);
                }

            });
            battleship.getBtn()[i].addActionListener(e -> {
                if (((JButton) e.getSource()).getBackground() == Color.YELLOW) {
                    setPanelButtonEnable(battleship, true);
                } else {
                    setPanelButtonEnable(battleship, false);
                }

            });
            submarine.getBtn()[i].addActionListener(e -> {
                if (((JButton) e.getSource()).getBackground() == Color.YELLOW) {
                    setPanelButtonEnable(submarine, true);
                } else {
                    setPanelButtonEnable(submarine, false);
                }
            });
            destroyer.getBtn()[i].addActionListener(e -> {
                if (((JButton) e.getSource()).getBackground() == Color.YELLOW) {
                    setPanelButtonEnable(destroyer, true);
                } else {
                    setPanelButtonEnable(destroyer, false);
                }
            });
            patrolBoat.getBtn()[i].addActionListener(e -> {
                if (((JButton) e.getSource()).getBackground() == Color.YELLOW) {
                    setPanelButtonEnable(patrolBoat, true);
                } else {
                    setPanelButtonEnable(patrolBoat, false);
                }
            });
        }    
    }
    /**
     *
     * @param e Rappresenta ogni oggetto della nave.
     * @param boo La variabile booleana per ogni nave selezionata deve essere verde
     * o rosso.
     */
    private void setPanelButtonEnable(Ship e, boolean boo) {
        for (int k = 0; k < 5; k++) {
            battleship.getComponents()[k].setEnabled(boo);
            submarine.getComponents()[k].setEnabled(boo);
            destroyer.getComponents()[k].setEnabled(boo);
            aircraftCarrier.getComponents()[k].setEnabled(boo);
            patrolBoat.getComponents()[k].setEnabled(boo);
            if (e != null && !boo) {
                e.getComponents()[k].setEnabled(true);
            }
        }

    }

    /**
   * Evidenzia in verde ogni nave (con greenPreview) quando è selezionata e spostata
     * Utilizza l'estensionecheckBorders () per verificare se la nave può essere piazzata.
     *
     * @param i
     * @param j
     */
    private void previewShip(int i, int j) {
        // crea l'anteprima
        if (aircraftCarrier.isPressed() && checkBorders(i, j, aircraftCarrier.getShipSize(), aircraftCarrier.isRotateShip())) {
            greenPreview(aircraftCarrier, i, j);
        } else if (battleship.isPressed() && checkBorders(i, j, battleship.getShipSize(), battleship.isRotateShip())) {
            greenPreview(battleship, i, j);
        } else if (submarine.isPressed() && checkBorders(i, j, submarine.getShipSize(), submarine.isRotateShip())) {
            greenPreview(submarine, i, j);
        } else if (destroyer.isPressed() && checkBorders(i, j, destroyer.getShipSize(), destroyer.isRotateShip())) {
            greenPreview(destroyer, i, j);
        } else if (patrolBoat.isPressed() && checkBorders(i, j, patrolBoat.getShipSize(), patrolBoat.isRotateShip())) {
            greenPreview(patrolBoat, i, j);
        }
    }

    /**
     
     * Evidenzia in verde la nave selezionata.
     *
     * @param e Ogni oggetto della nave.
     * @param i Numero di righe
     * @param j Numero di colonne
     */
    private void greenPreview(Ship e, int i, int j) {
        if (e.isRotateShip()) {
            for (int k = 0; k < e.getShipSize(); k++) {
                cellArray[i + k][j].setBackground(Color.GREEN);
            }
        } else {
            for (int k = 0; k < e.getShipSize(); k++) {
                cellArray[i][j + k].setBackground(Color.GREEN);
            }
        }
    }

    /**
  * Ruota la nave E evidenzia la nave selezionata in rosso se sovrascrive
     * un'altra nave che è già stata piazzata.
     *
     * @param row Numero di righe
     * @param col Numero di colonne
     * @param length Dimensioni di ogni nave selezionata.
     * @param rotateShip True se la nave è verticale o False se la nave lo è
     * orizzontalmente.
     * @return Restituisce booleano True o False a seconda della posizione della nave. Se
     * è legale da inserire restituirà true e se ne sovrascrive un altro
     * nave la nave selezionata diventa rossa e restituisce falso.
     *
     * La nave non viene posizionata finché il posizionamento stesso non è legale.
     */
    private boolean checkBorders(int row, int col, int length, boolean rotateShip) {
        if (rotateShip) {

            if (row + length - 1 < boardRow) {
                for (int z = 0; z < length; z++) {
                    if (yellowedCells.contains(cellArray[row + z][col])) {
                       // Evidenzia la nave selezionata in rosso. Restituisce False. (Riga)
                        for (int k = 0; k < length; k++) {
                            cellArray[row + k][col].setBackground(Color.RED);
                        }
                        return false;
                    }
                }
                return true;
            } else {
                for (int z = row; z < boardRow; z++) {
                    cellArray[z][col].setBackground(Color.RED);
                }
            }
        } else {

            if (col + length - 1 < boardCol) {
                for (int z = 0; z < length; z++) {
                    // Evidenzia la nave selezionata in rosso. Restituisce False. (Colonna)
                    if (yellowedCells.contains(cellArray[row][col + z])) {
                        for (int k = 0; k < length; k++) {
                            cellArray[row][col + k].setBackground(Color.RED);
                        }
                        return false;
                    }
                }
                return true;
            } else {
                for (int z = col; z < boardCol; z++) {
                    cellArray[row][z].setBackground(Color.RED);
                }
            }
        }
        return false;
    }

    /**
    * Imposta la nave nella plancia ShipPlacement del giocatore, quando l'utente fa clic
     * la nave già selezionata nel tabellone.
     *
     * @param row
     * @param col
     * @param shipLength Ottiene la lunghezza dell'oggetto della nave dalla classe della nave.
     * @param isRotate True se la nave è verticale, False se la nave lo è
     * orizzontalmente.
     * @param shipName Ottiene il nome di ogni oggetto della nave dalla classe della nave.
     */
    private void setShip(int row, int col, int shipLength, boolean isRotate, String shipName) {
        if (isRotate) {
            for (int k = 0; k < shipLength; k++) {
                cellArray[row + k][col].setBackground(Color.DARK_GRAY);
                yellowedCells.add(cellArray[row + k][col]);
                cellArray[row + k][col].setName(shipName);
            }
        } else {
            for (int k = 0; k < shipLength; k++) {
                cellArray[row][col + k].setBackground(Color.DARK_GRAY);
                yellowedCells.add(cellArray[row][col + k]);
                cellArray[row][col + k].setName(shipName);
            }
        }
        // in modo che non possa posizionare una barca sopra l'altra all'interno del tabellone
        for (JButton b : yellowedCells) {
            b.setEnabled(true);
        }
    }

    /**
    * Incorpora alcune funzioni quando fai clic su una nave dal file
     * Pannello ShipPlacement per cancellare il tabellone, evidenziare la nave selezionata
     * con un po 'di colore e inserisci il mouse con la nave selezionata nel file
     * tavola.
     *
     * Rimuove gli ActionListeners della nave dal pannello della nave - removeActions ();
     * Per evidenziare in giallo ogni nave selezionata
     * Per posizionare la nave già selezionata sul tabellone di posizionamento della nave finale.
     *
     * Ottiene il colore di sfondo del lato sinistro. Se è lightGray abilita il pulsante Start JButton.
     * Seleziona la nave dal lato sinistro solo se selezioni una parte della nave.
     * Rende la nave non posizionabile se c'è un'altra nave sulla strada.
     *
     * @param i Numero di righe
     * @param j Numero di colonne
     */
    private void leftClick(int i, int j) {
        if (aircraftCarrier.isPressed() && checkBorders(i, j, aircraftCarrier.getShipSize(), aircraftCarrier.isRotateShip())) {
            setShip(i, j, aircraftCarrier.getShipSize(), aircraftCarrier.isRotateShip(), aircraftCarrier.getName());
            setRotateShipFalse(aircraftCarrier);
            removeActions(aircraftCarrier);
            yellowedCells.removeAll(yellowCellsToRemove);
            yellowCellsToRemove.clear();
            if (startGameBtn.isEnabled()) {
                return;
            }
        } else if (battleship.isPressed() && checkBorders(i, j, battleship.getShipSize(), battleship.isRotateShip())) {
            setShip(i, j, battleship.getShipSize(), battleship.isRotateShip(), battleship.getName());
            setRotateShipFalse(battleship);
            removeActions(battleship);
            yellowedCells.removeAll(yellowCellsToRemove);
            yellowCellsToRemove.clear();
            if (startGameBtn.isEnabled()) {
                return;
            }
        } else if (submarine.isPressed() && checkBorders(i, j, submarine.getShipSize(), submarine.isRotateShip())) {
            setShip(i, j, submarine.getShipSize(), submarine.isRotateShip(), submarine.getName());
            setRotateShipFalse(submarine);
            removeActions(submarine);
            yellowedCells.removeAll(yellowCellsToRemove);
            yellowCellsToRemove.clear();
            if (startGameBtn.isEnabled()) {
                return;
            }
        } else if (destroyer.isPressed() && checkBorders(i, j, destroyer.getShipSize(), destroyer.isRotateShip())) {
            setShip(i, j, destroyer.getShipSize(), destroyer.isRotateShip(), destroyer.getName());
            setRotateShipFalse(destroyer);
            removeActions(destroyer);
            yellowedCells.removeAll(yellowCellsToRemove);
            yellowCellsToRemove.clear();
            if (startGameBtn.isEnabled()) {
                return;
            }
        } else if (patrolBoat.isPressed() && checkBorders(i, j, patrolBoat.getShipSize(), patrolBoat.isRotateShip())) {
            setShip(i, j, patrolBoat.getShipSize(), patrolBoat.isRotateShip(), patrolBoat.getName());
            setRotateShipFalse(patrolBoat);
            removeActions(patrolBoat);
            yellowedCells.removeAll(yellowCellsToRemove);
            yellowCellsToRemove.clear();
            if (startGameBtn.isEnabled()) {
                return;
            }
        }

        yellowedCells.removeAll(yellowCellsToRemove);
        yellowCellsToRemove.clear();

        Color c = aircraftCarrier.getBtn()[0].getBackground();
        Color c1 = submarine.getBtn()[0].getBackground();
        Color c2 = destroyer.getBtn()[0].getBackground();
        Color c3 = battleship.getBtn()[0].getBackground();
        Color c4 = patrolBoat.getBtn()[0].getBackground();
        Color col = Color.lightGray;

        if ((c1 == col && c2 == col && c3 == col && c4 == col && c == col) && !startGameBtn.isEnabled()) {
            startGameBtn.setEnabled(true);

        } else if (startGameBtn.isEnabled() && cellArray[i][j].getBackground() != Color.CYAN) {
            if (yellowedCells.contains(cellArray[i][j]) && cellArray[i][j].isEnabled()) {
                switch (cellArray[i][j].getName()) {
                    case "AircraftCarrier":
                        for (JButton b : yellowedCells) {
                            if (b.getName().equals("AircraftCarrier")) {
                                yellowCellsToRemove.add(b);
                            }
                        }
                        cleanBoard();
                        aircraftCarrier.setPressed(true);
                        break;
                    case "Battleship":
                        for (JButton b : yellowedCells) {
                            if (b.getName().equals("Battleship")) {
                                yellowCellsToRemove.add(b);
                            }
                        }
                        cleanBoard();
                        battleship.setPressed(true);
                        break;
                    case "Destroyer":
                        for (JButton b : yellowedCells) {
                            if (b.getName().equals("Destroyer")) {
                                yellowCellsToRemove.add(b);
                            }
                        }
                        cleanBoard();
                        destroyer.setPressed(true);
                        break;
                    case "Submarine":
                        for (JButton b : yellowedCells) {
                            if (b.getName().equals("Submarine")) {
                                yellowCellsToRemove.add(b);
                            }
                        }
                        cleanBoard();
                        submarine.setPressed(true);
                        break;
                    case "PatrolBoat":
                        for (JButton b : yellowedCells) {
                            if (b.getName().equals("PatrolBoat")) {
                                yellowCellsToRemove.add(b);
                            }
                        }
                        cleanBoard();
                        patrolBoat.setPressed(true);
                        break;
                }
                previewShip(i, j);
            }
            
            for (JButton b : yellowedCells) {
                b.setEnabled(false);
            }
        }
    }

    /**
     * "Pulisce" il pannello ShipPlacement dalla nave selezionata con il mouse.
     */
    private void cleanBoard() {
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                if (yellowedCells.isEmpty() || !yellowedCells.contains(cellArray[i][j])) {
                    cellArray[i][j].setBackground(Color.CYAN);
                    for (JButton b : yellowedCells) {
                        b.setBackground(Color.DARK_GRAY);
                    }
                    for (JButton b : yellowCellsToRemove) {
                        b.setBackground(Color.YELLOW);
                    }
                }
            }
        }
    }

    /**
     *
     * @param e
     */
    private void setRotateShipFalse(Ship e) {
        aircraftCarrier.setRotateShip(false);
        battleship.setRotateShip(false);
        submarine.setRotateShip(false);
        destroyer.setRotateShip(false);
        patrolBoat.setRotateShip(false);
        for (int k = 0; k < 5; k++) {
            aircraftCarrier.getBtn()[k].setEnabled(true);
            battleship.getBtn()[k].setEnabled(true);
            submarine.getBtn()[k].setEnabled(true);
            destroyer.getBtn()[k].setEnabled(true);
            patrolBoat.getBtn()[k].setEnabled(true);
        }
        if (e != null) {
            e.setPressed(false);
            e.setButtonsEnabled(false);
        }
    }

    public ArrayList<JButton> getYellowedCells() {
        return yellowedCells;
    }

    public JButton[][] getCellArray() {
        return cellArray;
    }

    /**
     * Rimuove tutti gli ActionListeners dalla nave posizionata.
     * @param e Ogni oggetto della nave selezionato.
     */
    private void removeActions(Ship e) {
        for (int i = 0; i < 5; i++) {
            for (ActionListener al : e.getBtn()[i].getActionListeners()) {
                e.getBtn()[i].removeActionListener(al);
            }
        }
    }

    @Override
    public void drawGameover(Graphics g) {
    }

}
