/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* File main: nel file è presente la classe MAIN .  
   


*/
    package ARCADE_PARK_DEF;
//librerie utilizzate

import java.awt.EventQueue;


/**
 *
 * @author Cerenzia Francesco & Maddocco Alessandro
 * 
 */

//classe principale
public class Aracade_park {

  
    public static void main(String[] args) {
                
                  EventQueue.invokeLater(() -> {
                      try {
                          Menu__ window = new Menu__();
                          window.frmStartMenu.setVisible(true);
                      } catch (Exception e) {
                          e.printStackTrace();
                      }
                  });
    }
    
}
