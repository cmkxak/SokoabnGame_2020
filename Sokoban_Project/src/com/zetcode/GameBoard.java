package com.zetcode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameBoard extends JPanel implements ActionListener {
	private Character character;
	private SinglePlayer player;
	private MultiPlayer player2;

	private int moved;
	private int best;
	private int coinscore;
	private int baggages;
	private int levelNumber;
	private int maxMoving;
	private boolean isFailed = false;
	private boolean isCompleted = false;
	private JButton backButton;

	private MultiLevelPanel multiLevelPanel;
	private LevelPanel levelPanel;
	private ArrayList<Wall> walls;
	private ArrayList<Area> areas;
	private ArrayList<Baggage> baggs;
	private ArrayList<Coin> coins;

	GameManager gameManager = new GameManager(levelNumber);
	LevelManage level = new LevelManage();

	public String selectedSanta;

	public GameBoard(int levelnumber) {
		this.levelNumber = levelnumber;
		initBoard(levelnumber);
		ShowBestScore(levelnumber);
		add(backButton);
	}

	private void initBoard(int levelnumber) {
		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld(levelnumber);
		setReturnButton();
		SetCharactorBox();
		maxMoving = level.getMaxMoving(levelnumber);
	}

	public void initWorld(int levelnumber) {
		gameManager.initWorld(levelnumber);
	}

	public void buildWorld(Graphics g) {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		ArrayList<Actor> gameActor = new ArrayList<>();

		walls = gameManager.getWalls();
		areas = gameManager.getAreas();
		baggs = gameManager.getBaggages();
		coins = gameManager.getcoins();
		player = gameManager.getPlayer();
		player2 = gameManager.getMultiPlayer();
		
		gameActor.addAll(walls);
		gameActor.addAll(areas);
		gameActor.addAll(baggs);
		gameActor.addAll(coins);
		gameActor.add(player);
		
		if (player2 != null)
			gameActor.add(player2);

		if (isCompleted) {
			g.setColor(new Color(0, 0, 0));
			g.drawString("Completed", 20, 240);
			ScoreManager(levelNumber);
		}
		if (isFailed) {
			g.setColor(new Color(0, 0, 0));
			g.drawString("Failed T_T", 20, 240);
		}

		for (int i = 0; i < gameActor.size(); i++) {

			ActorObject item = gameActor.get(i);

			if (item instanceof SinglePlayer || item instanceof Baggage) {

				g.drawImage(item.getImage(), item.getX() + 2, item.getY() + 2, this);
			} else {

				g.drawImage(item.getImage(), item.getX(), item.getY(), this);
			}
			if (player.getMoved() >= 0) {
				g.setColor(new Color(0, 0, 0));
				g.drawString("Moved:" + player.getMoved(), 10, 40);
			}
			if (baggs.size() >= 0) {
				countBaggs();
				g.setColor(new Color(0, 0, 0));
				g.drawString("Baggages:" + baggages, 10, 20);
			}
			getCoins(g);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		buildWorld(g);
	}

	public void KeyPressedEvent() {
		player.savePos();
		player.countMoved();

		if (player2 != null) {
			player2.savePos();
			player2.countMoved();
		}
		Music.Play(GameUtilities.footsound);
		isFailed();
	}
	
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (isCompleted) {
				return;
			}
			if (isFailed) {
				return;
			}

			int key = e.getKeyCode();
			switch (key) {

			case KeyEvent.VK_LEFT:
				if (gameManager.checkWallCollision(player, GameUtilities.LEFT_COLLISION))
					return;

				if (gameManager.checkBagCollision(player, GameUtilities.LEFT_COLLISION))
					return;

				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}
				player.move(-GameUtilities.SPACE, 0);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_RIGHT:
				if (gameManager.checkWallCollision(player, GameUtilities.RIGHT_COLLISION)) {
					return;
				}
				if (gameManager.checkBagCollision(player, GameUtilities.RIGHT_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}
				player.move(GameUtilities.SPACE, 0);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_UP:
				if (gameManager.checkWallCollision(player, GameUtilities.TOP_COLLISION)) {
					return;
				}
				if (gameManager.checkBagCollision(player, GameUtilities.TOP_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}
				player.move(0, -GameUtilities.SPACE);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_DOWN:
				if (gameManager.checkWallCollision(player, GameUtilities.BOTTOM_COLLISION)) {
					return;
				}
				if (gameManager.checkBagCollision(player, GameUtilities.BOTTOM_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}
				player.move(0, GameUtilities.SPACE);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_R:
				moved = 0;
				restartLevel(levelNumber);
				Music.Play(GameUtilities.failedSound);
				break;

			case KeyEvent.VK_BACK_SPACE: // backspace 키를 입력받아 undo 구현하는 부분
				if (isCompleted) {
					return;
				}
				if (player.getMoved() > 0) {
					player.undoMove();
					
					if (player2 != null)
						player2.undoMove();
					
					for (int i = 0; i < gameManager.getBaggages().size(); i++) {
						gameManager.getBaggages().get(i).undoMove();
					}
					player.countUndoMove();
					
					isCompleted();
				}
				Music.Play(GameUtilities.undoSound);
				break;

			case KeyEvent.VK_A:
				if (gameManager.checkBagCollision(player2, GameUtilities.LEFT_COLLISION)) {
					return;
				}
				if (gameManager.checkWallCollision(player2, GameUtilities.LEFT_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}

				player2.move(-GameUtilities.SPACE, 0);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_D:
				if (gameManager.checkBagCollision(player2, GameUtilities.RIGHT_COLLISION)) {
					return;
				}

				if (gameManager.checkWallCollision(player2, GameUtilities.RIGHT_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}
				player2.move(GameUtilities.SPACE, 0);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_W:
				if (gameManager.checkBagCollision(player2, GameUtilities.TOP_COLLISION)) {
					return;
				}
				if (gameManager.checkWallCollision(player2, GameUtilities.TOP_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}
				player2.move(0, -GameUtilities.SPACE);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_S:
				if (gameManager.checkBagCollision(player2, GameUtilities.BOTTOM_COLLISION)) {
					return;
				}
				if (gameManager.checkWallCollision(player2, GameUtilities.BOTTOM_COLLISION)) {
					return;
				}
				for (int i = 0; i < gameManager.getBaggages().size(); i++) {
					gameManager.getBaggages().get(i).savePos();
				}

				player2.move(0, GameUtilities.SPACE);
				KeyPressedEvent();
				break;

			case KeyEvent.VK_B:
				character.setVisible(true);
				break;

			default:
				break;
			}
			isCompleted();
			repaint();
		}
	}
	public void isCompleted() {
		int nOfBags = baggs.size();
		int finishedBags = 0;

		for (int i = 0; i < nOfBags; i++) {
			Baggage bag = baggs.get(i);

			for (int j = 0; j < areas.size(); j++) {

				Area area = areas.get(j);
				if (bag.getX() == area.getX() && bag.getY() == area.getY()) {
					finishedBags += 1;
				}
			}
		}
		if (finishedBags == nOfBags) {
			Music.Play(GameUtilities.completeSound);
			isCompleted = true;
		}
	}

	public void isFailed() {
		if (player.getMoved() > maxMoving - 1) {
			isFailed = true;
		}
	}
	public void countBaggs() {
		int nOfBags = gameManager.getBaggages().size();
		int finishedBags = 0;
		for (int i = 0; i < nOfBags; i++) {

			Baggage bag = gameManager.getBaggages().get(i);

			for (int j = 0; j < nOfBags; j++) {

				Area area = gameManager.getAreas().get(j);

				if (bag.getX() == area.getX() && bag.getY() == area.getY()) {
					finishedBags += 1;
				}
			}
			baggages = nOfBags - finishedBags;
		}
	}

	private void setReturnButton() {
		backButton = new JButton("BACK");
		backButton.setPreferredSize(new Dimension(90, 20));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main topFrame = (Main)SwingUtilities.getWindowAncestor(GameBoard.this);
				topFrame.dispose();
				if (levelNumber >= 0 && levelNumber < 5) {
					levelPanel = new LevelPanel();
					levelPanel.setVisible(true);
				}
				if (levelNumber >= 5 && levelNumber < 10) {
					multiLevelPanel = new MultiLevelPanel();
					multiLevelPanel.setVisible(true);
				}
			}
		});
	}
	private void SetCharactorBox() {
		selectedSanta = "Red";
		character = new Character(selectedSanta);
		character.okButton.addActionListener(this);
	}
	public void restartLevel(int levelnumber) {
		areas.clear();
		baggs.clear();
		walls.clear();

		initWorld(levelnumber);
		Music.Play(GameUtilities.failedSound);

		isCompleted = false;

		if (isFailed) {
			isFailed = false;
		}
	}
	public void ShowBestScore(int levelnumber) {
		try {
			File scoreFile = new File("src/resources/scores/score" + levelnumber + ".txt");
			FileReader filereader = new FileReader(scoreFile);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				best = Integer.parseInt(line);
			}
			filereader.close();
		} catch (FileNotFoundException e) {
			best = 0;
		} catch (IOException e) {
		}
	}

	public void ScoreManager(int levelnumber) {
		if (moved < best || best == 0) {
			moved = player.getMoved();
			try {
				File scoreFile = new File("src/resources/scores/score" + levelnumber + ".txt");
				FileWriter fw = new FileWriter(scoreFile);
				fw.write(Integer.toString(moved + coinscore));
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getBest() {
		return best;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		{
			selectedSanta = character.selected;
			player.changeSanta(character.selected);
			repaint();
			character.setVisible(false);
		}
	}

	public void getCoins(Graphics g) {
		int nOfCoins = coins.size();

		for (int i = 0; i < nOfCoins; i++) {

			Coin coin = coins.get(i);
			if (coin.getX() == player.getX() && coin.getY() == player.getY()) {
				coinscore = -30;
				coins.clear();
				g.setColor(new Color(0, 0, 0));
				g.drawString("You get Coin!", 10, 90);
			}
		}
	}
}
