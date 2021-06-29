
package ARCADE_PARK_DEF.battaglia_navale_game;


import ARCADE_PARK_DEF.Disegna;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;


public class TheGame extends JFrame implements Disegna{

    private final  String playerName;
    private final int boardRow;
    private final int boardCol;
    private final ArrayList<JButton> yellowCells;
    private ArrayList<JButton> playersCellsPressed;
    private final JButton[][] cellArray;
    private PlayerFinalBoard playersBoard, computersBoard;
    private Graphics g;
//costruttore
    public TheGame(int boardRow, int boardCol, String playerName, ArrayList<JButton> yellowCells, JButton[][] cellArray) {
        this.playerName = playerName;
        this.boardRow = boardRow;
        this.boardCol = boardCol;
        this.yellowCells = yellowCells;
        this.cellArray = cellArray;
      draw(g);
    }

   
    private void computersTurn() {
        if (checkWinner() == 0) {
            int row = ThreadLocalRandom.current().nextInt(0, boardRow);
            int col = ThreadLocalRandom.current().nextInt(0, boardCol);
            while (true) {
                if (!playersCellsPressed.contains(playersBoard.getCellArray()[row][col])) {
                    break;
                }
                row = ThreadLocalRandom.current().nextInt(0, boardRow);
                col = ThreadLocalRandom.current().nextInt(0, boardCol);
            }
            playersCellsPressed.add(playersBoard.getCellArray()[row][col]);
            if (playersBoard.getCellArray()[row][col].getBackground() == Color.DARK_GRAY) {
                playersBoard.getCellArray()[row][col].setBackground(Color.RED);
            } else {
                playersBoard.getCellArray()[row][col].setBackground(Color.white);
            }

        }
        //se vince il giocatore
        if (checkWinner() == 1) {
            JOptionPane.showMessageDialog(this, playerName + " VINCE !");
            this.dispose();
        }
        //se vince il computer
        if (checkWinner() == 2) {
            JOptionPane.showMessageDialog(this, "COMPUTER VINCE!");
            this.dispose();
        }
    }
    
    private int checkWinner() {
        boolean computerWin = true;
        boolean playerWin = true;

        for (JButton b : computersBoard.getCells()) {
            if (b.getBackground() == Color.CYAN) {
                playerWin = false;
                break;
            }
        }
        for (JButton b : playersBoard.getCells()) {
            if (b.getBackground() == Color.DARK_GRAY) {
               
                computerWin = false;
                break;
            }
        }

        if (computerWin) {
            return 2;
        } else if (playerWin) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public void draw(Graphics g) {
 playersCellsPressed = new ArrayList<JButton>();
        playersBoard = new PlayerFinalBoard(playerName, boardRow, boardCol, yellowCells, cellArray);
        computersBoard = new PlayerFinalBoard("Computer", boardRow, boardCol, new ArrayList<JButton>(), new JButton[0][0]);

        
        for (int i = 0; i < boardRow; i++) {
            for (int j = 0; j < boardCol; j++) {
                playersBoard.getCellArray()[i][j].setEnabled(false);
                computersBoard.getCellArray()[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JButton b = (JButton) e.getSource();
                        if (b.isEnabled()) {
                            b.setBackground(Color.GREEN);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        JButton b = (JButton) e.getSource();
                        if (b.isEnabled()) {
                            b.setBackground(Color.CYAN);
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JButton b = (JButton) e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (computersBoard.getCells().contains(b)) {
                                b.setBackground(Color.RED);
                            } else {
                                b.setBackground(Color.WHITE);
                            }
                            computersTurn();
                            b.removeMouseListener(this);
                        }
                    }
                });
            }
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setRightComponent(computersBoard);
        splitPane.setLeftComponent(playersBoard);
        splitPane.setEnabled(false);
        
        JPanel btnsPanel = new JPanel(new BorderLayout());
       
        btnsPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        this.add(splitPane);
        this.add(btnsPanel, BorderLayout.SOUTH);
        this.pack();
        this.setResizable(false);
        this.setPreferredSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("BATTAGLIA NAVALE");
        this.setVisible(true);    }
}
