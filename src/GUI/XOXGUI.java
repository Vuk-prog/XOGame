package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Engine.STANJE_IGRE;
import Engine.XOXEngine;

public class XOXGUI extends JFrame{
	
	JLabel playerLbl = new JLabel("Currently playing X");
	JButton newGameBtn = new JButton("New game");
	JButton buttons[][]= new JButton[3][3];
	XOXEngine engine = new XOXEngine();
	
	JPanel centralPanel = new JPanel(new GridLayout(3,3,5,5));
	
	public XOXGUI() {
		setBounds(100, 100, 800, 800);
		playerLbl.setFont( new Font("Arial", Font.BOLD, 40));
		
		
		for( int i = 0; i < buttons.length; i++)
		{
			for( int j = 0; j < buttons.length; j++)
			{
				buttons[i][j] = new MyButton(i, j);
				buttons[i][j].setPreferredSize(new Dimension(100, 100));
				centralPanel.add(buttons[i][j]);
				
				
				buttons[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						MyButton clickedBtn = (MyButton)e.getSource();
						
						System.out.println("Click on "+ clickedBtn.getI() +
								" " + clickedBtn.getJ());
						
						STANJE_IGRE move = engine.play(clickedBtn.getI(),
								clickedBtn.getJ());
						
						clickedBtn.setEnabled(false);
						refreshGUI();
						
						if(move != STANJE_IGRE.U_TOKU)
						{
							String msg = "";
							refreshGUI();
							
							if(move == STANJE_IGRE.POBEDIO_OX) {
								msg = "Winner: OX";
							}
							if(move == STANJE_IGRE.POBEDIO_X) {
								msg = "Winner: X";
							}
							if(move == STANJE_IGRE.NERESENO) {
								msg = "DRAW.";
							}
							
							int response = JOptionPane.showConfirmDialog(null, 
									msg, "New game?",
									JOptionPane.YES_NO_OPTION);
							
							if(response == JOptionPane.YES_OPTION) {
								engine.init();
							}else {
								System.exit(0);
							}
						}
						
						
						
						
					}
				});
			}
		}
		
		
		newGameBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.init();
				refreshGUI();
				
			}
		});
		
		getContentPane().add(centralPanel, BorderLayout.CENTER);
		getContentPane().add(playerLbl, BorderLayout.NORTH);
		getContentPane().add(newGameBtn, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void refreshGUI() {
		
		playerLbl.setText("Currently playing: "+ engine.currPlay());
		
		for( int i = 0; i < buttons.length; i++)
		{
			for( int j = 0; j < buttons.length; j++)
			{
				buttons[i][j].setText(engine.getValue(i,j));
				if( engine.getValue(i, j) == " ") {
					buttons[i][j].setEnabled(true);
				}
			}
		}
	}

}
