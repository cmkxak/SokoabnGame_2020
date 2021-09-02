package com.zetcode;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LevelPanel extends JFrame{

//	private final int OFFSET = 30;

	private JPanel mainPanel;
	private GameManager gameMap;

	private int levelNumber;
	private JPanel levelSelectorPanel;
	private JPanel scorePanel;
	
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton returnButton;
	
	private JLabel level1;
	private JLabel level2;
	private JLabel level3;
	private JLabel level4;
	private JLabel level5;

	public LevelPanel() {
		gameMap = new GameManager(levelNumber);
		setSize(gameMap.getBoardWidth() + GameUtilities.OFFSET, gameMap.getBoardHeight() + 2 * GameUtilities.OFFSET);
		setLocationRelativeTo(null);
		setTitle("Sokoban");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel = new JPanel();
		setContentPane(mainPanel);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		levelSelectorPanel = new JPanel();
		mainPanel.add(levelSelectorPanel);
		
		scorePanel = new JPanel();
		mainPanel.add(scorePanel);
		scorePanel.setLayout(new GridLayout(0, 1, 0, 0));

		SetScore(level1, 1);//Refatoring 1
		SetScore(level2, 2);
		SetScore(level3, 3);
		SetScore(level4, 4);
		SetScore(level5, 5);
		
		SetButton(button1, 1);//Refactoring 2
		SetButton(button2, 2);
		SetButton(button3, 3);
		SetButton(button4, 4);
		SetButton(button5, 5);
		SetButton(returnButton);
		
		setResizable(false);
		setVisible(true);
	}
	
	public void SetScore(JLabel label, int levelnumber) {
		label = new JLabel("Highscore" + levelnumber + ":" + new GameBoard(levelnumber-1).getBest());
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		scorePanel.add(label);
		
	}
	public void SetButton(JButton button) {
		button = new JButton("BACK");
		button.setPreferredSize(new Dimension(120,30));
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		ButtonClickedSet(button);
		levelSelectorPanel.add(button);
	}

	public void SetButton(JButton button, int levelnumber) {
		button = new JButton("LEVEL" + levelnumber);
		button.setPreferredSize(new Dimension(120,30));
		button.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		ButtonClickedSet(button, levelnumber);
		levelSelectorPanel.add(button);
	}
	public void ButtonClickedSet(JButton button, int levelnumber) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main main = new Main(levelnumber-1);
				mainPanel.removeAll();
				main.setVisible(true);
				dispose();
			}
		});
	}
	public void ButtonClickedSet(JButton button) {
			button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainScreen();
				dispose();
			}
		});
	}
}
