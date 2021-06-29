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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//gestisce il replay
public class ReplayHandler implements ActionListener {

	public ReplayHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
             new Tetris();
	}

}
