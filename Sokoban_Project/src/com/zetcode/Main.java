package com.zetcode;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame {

	private static MainScreen mainScreen;
	public int levelNumber;
	private GameBoard gameBoard;
	private GameManager gameMapManager;
	
	public Main(int levelnumber) {
		initUI(levelnumber);
	}
	private void initUI(int levelnumber) {
		gameBoard = new GameBoard(levelnumber);
		gameMapManager = new GameManager(levelnumber);
		add(gameBoard);
		
		setTitle("Sokoban");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(gameMapManager.getBoardWidth() + GameUtilities.OFFSET, gameMapManager.getBoardHeight() + 2 * GameUtilities.OFFSET);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Music.Loop(GameUtilities.backGroundMusic);
			mainScreen = new MainScreen();
			mainScreen.setVisible(true);
		});
	}
}





