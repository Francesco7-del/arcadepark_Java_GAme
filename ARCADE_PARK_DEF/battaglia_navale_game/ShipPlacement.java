
package ARCADE_PARK_DEF.battaglia_navale_game;




import ARCADE_PARK_DEF.Disegna;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



public class ShipPlacement extends JFrame implements Disegna{


    private final int boardRow;
    private final int boardCol;
    private final String playerName;
    private PlacementBoard boardPanel;
    private TheGame theGameFrame;
    private Graphics g;
//piazzamento navi
    public ShipPlacement(String playerName, int boardRow, int boardCol) {
        this.playerName = playerName;
        this.boardCol = boardCol;
        this.boardRow = boardRow;
        draw(g);
    }

 

    @Override
    public void drawGameover(Graphics g) {
    }

    @Override
    public final void draw(Graphics g) {
        Ship aircraftCarrier = new AircraftCarrier();
        Ship battleship = new Battleship();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();
        Ship patrolBoat = new PatrolBoat();

        // scritta in alto
        JLabel topLabel = new JLabel("INSERISCI LE BARCHE ....PREMI START QUANDO SEI PRONTO!", JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.TOP);
        topLabel.setVerticalTextPosition(JLabel.TOP);

        //ruota la barca
        JButton rotateShipBtn = new JButton("RUOTA BARCA");
        rotateShipBtn.addActionListener(e -> {
            if (aircraftCarrier.isRotateShip()) aircraftCarrier.setRotateShip(false);
            else aircraftCarrier.setRotateShip(true);

            if (submarine.isRotateShip()) submarine.setRotateShip(false);
            else submarine.setRotateShip(true);

            if (destroyer.isRotateShip()) destroyer.setRotateShip(false);
            else destroyer.setRotateShip(true);

            if (battleship.isRotateShip()) battleship.setRotateShip(false);
            else battleship.setRotateShip(true);

            if (patrolBoat.isRotateShip()) patrolBoat.setRotateShip(false);
            else patrolBoat.setRotateShip(true);

        });
        JButton startGameBtn = new JButton("Start ");
        startGameBtn.setEnabled(false);
        startGameBtn.addActionListener(e -> {
            this.dispose();
            theGameFrame=new TheGame(boardRow, boardCol, playerName, boardPanel.getYellowedCells(), boardPanel.getCellArray());
        });
        JPanel btnsPanel = new JPanel(new BorderLayout());
        btnsPanel.add(rotateShipBtn, BorderLayout.WEST);
        btnsPanel.add(startGameBtn, BorderLayout.EAST);
        btnsPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        
        JPanel shipPanel = new JPanel(new GridLayout(5, 1, 0, 10));
    

        
        shipPanel.add(aircraftCarrier);
        shipPanel.add(battleship);
        shipPanel.add(submarine);
        shipPanel.add(destroyer);
        shipPanel.add(patrolBoat);
        

        Border shipBorder = new EmptyBorder(150, 50, 150, 50);
        TitledBorder shipBorderTitle = BorderFactory.createTitledBorder(shipBorder, "BARCHE ");
        shipBorderTitle.setTitleJustification(TitledBorder.CENTER);
        shipPanel.setBorder(shipBorderTitle);

        boardPanel = new PlacementBoard(boardRow, boardCol, aircraftCarrier, battleship, submarine, patrolBoat, destroyer, startGameBtn);

        // layout del secondo frame
        this.setLayout(new BorderLayout());

        this.add(topLabel, BorderLayout.NORTH);
        this.add(shipPanel, BorderLayout.WEST);
        this.add(boardPanel, BorderLayout.EAST);
        this.add(btnsPanel, BorderLayout.SOUTH);

        this.pack();
        this.setResizable(false);
        this.setPreferredSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("POSIZIONAMENTO BARCHE");
        this.setVisible(true);
    }
}
