
package com.zetcode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreen extends JFrame {
	private JButton soloMode;
	private JButton multiMode;
	private JButton quitButton;

	private JPanel mainScreen;
	
	private JLabel label;

	private ImageIcon soloModeImage;
	private ImageIcon multiModeImage;
	private ImageIcon quitButtonImage;

	private GameManager map;
	private int levelNumber;
	
	public MainScreen() {

		map = new GameManager(levelNumber);
		mainScreen = new JPanel();
		
		setContentPane(mainScreen);
		setTitle("Sokoban");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(map.getBoardWidth() + GameUtilities.OFFSET, map.getBoardHeight() + 2 * GameUtilities.OFFSET);
		setLocationRelativeTo(null);

		label = new JLabel("SOKOBAN");
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		mainScreen.setBackground(Color.white);

		soloModeImage = new ImageIcon("src/resources/solomodebutton.jpg");
		multiModeImage = new ImageIcon("src/resources/multimodebutton.jpg");
		quitButtonImage = new ImageIcon("src/resources/quitbutton.jpg");

		soloMode = new JButton(soloModeImage);
		multiMode = new JButton(multiModeImage);
		quitButton = new JButton(quitButtonImage);
		
		ButtonSet(soloMode);
		ButtonSet(multiMode);
		ButtonSet(quitButton);	
	
		ButtonClicked();
	
		mainScreen.add(label);
		mainScreen.add(soloMode);
		mainScreen.add(multiMode);
		mainScreen.add(quitButton);
		setResizable(false);
		setVisible(true);
	}
	
	private void ButtonSet(JButton button) {
		if(soloMode.isVisible() == true) {
			new JButton(soloModeImage);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setOpaque(false);
		}
		else if(multiMode.isVisible() == true) {
			new JButton(multiModeImage);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setOpaque(false);
		}
		else if(quitButton.isVisible() == true){
			new JButton(quitButtonImage);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			button.setFocusPainted(false);
			button.setOpaque(false);
		}
	}

	private void ButtonClicked() { // duplicated code --> Extracted Method (Refactoring 2) <RemoveMainScreen>
		soloMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LevelPanel level = new LevelPanel();
				RemoveMainScreen();
				level.setVisible(true);
				dispose();
			}
		});
		multiMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				MultiLevelPanel multilevel = new MultiLevelPanel();
				RemoveMainScreen();
				multilevel.setVisible(true);
			}
		});
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
	}
	private void RemoveMainScreen() {
		mainScreen.removeAll();
	}
}