/*
 * To changefrmStartMenu license header, choose License Headers in Project Properties.
 * To changefrmStartMenu template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ARCADE_PARK_DEF;

import ARCADE_PARK_DEF.battaglia_navale_game.PlayerDetails;
import ARCADE_PARK_DEF.breakout_game.Game;
import ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions;
import static ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions.APPLE_IMAGE_LOCATION;
import static ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions.DOTS_NUMBER_PER_DIMENSION;
import static ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions.DOT_SIZE;
import static ARCADE_PARK_DEF.snake__game.model.BoardSettingsOptions.SNAKE_DOT_IMAGE_LOCATION;
import ARCADE_PARK_DEF.snake__game.view.SnakeGameUI;
import ARCADE_PARK_DEF.tetris_game.Tetris;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class Menu__ {
    
    public JFrame frmStartMenu;
         ImageIcon img = new ImageIcon("C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\menu_1.gif");
         ImageIcon img1 = new ImageIcon("C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\mute__1.jpg");
         ImageIcon img2 = new ImageIcon("C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\play__but.png");
         ImageIcon img3 = new ImageIcon("C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\exit_1.jpg");
        private JButton playBtn;
       private JButton playBtn1;
      
       private boolean soundLoaded; 
        private static PlayerDetails playerDetailsFrame;
    	
    public Menu__()  {
		initialize();
	}
    private void initialize()  
    {
        
         frmStartMenu = new JFrame();
	frmStartMenu.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
	frmStartMenu.setTitle("ARCADE PARK");
        frmStartMenu.setLocationRelativeTo(null);
        frmStartMenu.setResizable(false);
        frmStartMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmStartMenu.setLayout(null);
        frmStartMenu.getContentPane().setBackground(Color.black);
        frmStartMenu.setSize(800,700);
        frmStartMenu.setLocationRelativeTo(null);
        JLabel l =  new JLabel("",img,JLabel.CENTER);
        l.setSize(800,700);
        l.setBounds(0,0,800,750);
        frmStartMenu.getContentPane().add(l);
       
          
          
          
       ///inserimento file audio
       Clip clip;
       
        try {
	AudioInputStream input=AudioSystem.getAudioInputStream(new File("C:\\Users\\User\\Desktop\\arcadepark_cerenzia_maddocco\\ARCADE_PARK_DEF\\suono3.wav"));
	clip=AudioSystem.getClip();
	clip.open(input);
        soundLoaded = true;
	clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //bottone  per stoppare la musica 
        playBtn = new JButton(img1);
        playBtn.addActionListener(new ActionListener() {
            
        @Override
        public void actionPerformed(ActionEvent e) {
                if (soundLoaded) {
                    	if (clip.isRunning())
                            clip.stop();   
                   }
                }});
          
          playBtn.setBounds(650,20,40,40);
          playBtn.setBackground(Color.ORANGE);
          l.add(playBtn);
      //   playBtn.addActionListener((ActionListener) this);
        
         ///bottone  per riprendere la musica 
        playBtn1 = new JButton(img2);
        playBtn1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                if (soundLoaded) {
                    	if (clip!=null)
                            clip.start();   
                   }
                }});
          playBtn1.setBounds(700,20,40,40);
          playBtn1.setBackground(Color.ORANGE);
          l.add(playBtn1);
//         playBtn1.addActionListener((ActionListener) this);
        
              
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)  {
    	e.printStackTrace();    
       
        }


        ///////
       JLabel ben = new JLabel(" BENVENUTO IN ARCADE PARK");	
            
		ben.setFont(new Font("verdana", Font.BOLD, 17));
		
		ben.setBounds(1,1,300,100);
              
                ben.setForeground(Color.white);
                
                
                l.add(ben);
		
        
        
        ////
        JButton btnNewButton_3 = new JButton("Gioca a Tetris");
		btnNewButton_3.addActionListener((ActionEvent arg0) -> {
                    // tetris
                    Tetris T1=new Tetris();
         });
		
		
		
		
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_3.setBounds(20,150,150,50);
                btnNewButton_3.setBackground(Color.white);
                btnNewButton_3.setForeground(Color.red);
		l.add(btnNewButton_3);
                
                
                
                
        //chiudi
      JButton exit = new JButton(img3);
		exit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				
				 System.exit(0);
				
		
				}
		});
		
                exit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		exit.setBounds(700,600,70,70);//50,20,70,70
                exit.setBackground(Color.white);
                exit.setForeground(Color.red);
		l.add(exit);
		
                
                
		/////////////
                JButton btnNewButton_4 = new JButton("Brick Breaker");
		btnNewButton_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				
				     //Brick Breaker
            JFrame window = new JFrame("Brick Breaker");
		window.add(new Game());
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

				
		
				}
		});
		
		
		
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_4.setBounds(20,250,150,50);
                btnNewButton_4.setBackground(Color.white);
                btnNewButton_4.setForeground(Color.red);
		l.add(btnNewButton_4);
                
                
                ///battaglia navale 
		JButton btnNewButton_5 = new JButton("Battaglia nvale ");
		btnNewButton_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				
				
                           playerDetailsFrame = new PlayerDetails();
		
				}
		
		});
		
		
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_5.setBounds(20,350,150,50);
                btnNewButton_5.setBackground(Color.white);
                btnNewButton_5.setForeground(Color.red);
		l.add(btnNewButton_5);
                
                
                ///////////////
             JButton btnNewButton_6 = new JButton("Snake");
		btnNewButton_6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				
				////snake
                                    new SnakeGameUI(new BoardSettingsOptions(DOT_SIZE,
                                    DOTS_NUMBER_PER_DIMENSION,
                                    SNAKE_DOT_IMAGE_LOCATION,
                                     APPLE_IMAGE_LOCATION));  
				
		
				}
		
		});
		
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_6.setBounds(20,450,150,50);
                btnNewButton_6.setBackground(Color.white);
                btnNewButton_6.setForeground(Color.red);
		l.add(btnNewButton_6);
		
                //////////////////////////////////
                
                JButton btnNewButton_7 = new JButton("The space");
		btnNewButton_7.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0){
				
				
				 ARCADE_PARK_DEF.space.Menu menu = new ARCADE_PARK_DEF.space.Menu();
                                  menu.setVisible(true);
		
				}
		
		});
		
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_7.setBounds(20,550,150,50);
                btnNewButton_7.setBackground(Color.white);
                btnNewButton_7.setForeground(Color.red);
                l.add(btnNewButton_7);
                
            
                
        
    }
}
