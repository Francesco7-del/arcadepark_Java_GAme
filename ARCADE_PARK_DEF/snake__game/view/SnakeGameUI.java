
package ARCADE_PARK_DEF.snake__game.view;


import ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions;
import ARCADE_PARK_DEF.snake__game.panel.BoardPanel;
import javax.swing.*;
import java.awt.*;

public class SnakeGameUI extends JFrame {

    //   apre il gioco

    public SnakeGameUI(final BoardSettingsOptions boardSettingsOptions) {
        super("~~~ Snake ~~~");

        getContentPane().add(new BoardPanel(boardSettingsOptions));
        pack();

        setWindow();
    }

    /**
     * impostazioni finestra
     */
    private void setWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationInCenter();
        setVisible(true);
    }

    /**
     * imposta finestra al centro
     */
    private void setLocationInCenter() {
        setLocationRelativeTo(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(
                screenSize.width / 2 - this.getSize().width / 2,
                screenSize.height / 2 - this.getSize().height / 2);
    }
}
