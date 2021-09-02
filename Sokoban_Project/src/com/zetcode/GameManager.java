package com.zetcode;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager {
	private SinglePlayer player;
	private MultiPlayer player2;
	private int x;
	private int y;
	
	private int w = 0;
	private int h = 0;

	private LevelManage level = new LevelManage();

	public GameManager(int levelnumber) {
		initWorld(levelnumber);
	}
	public int getBoardWidth() {
		return this.w;
	}

	public int getBoardHeight() {
		return this.h;
	}
	ArrayList<Wall> walls;
	ArrayList<Baggage> baggs;
	ArrayList<Area> areas;
	ArrayList<Coin> coins;

	public ArrayList<Wall> getWalls() {
		return walls;
	}
	public ArrayList<Baggage> getBaggages() {
		return baggs;
	}
	public ArrayList<Area> getAreas() {
		return areas;
	}
	public ArrayList<Coin> getcoins() {
		return coins;
	}
	public SinglePlayer getPlayer() {
		return player;
	}
	public MultiPlayer getMultiPlayer() {
		return player2;
	}
	
	public void initWorld(int levelnumber) {
		walls = new ArrayList<>();
		baggs = new ArrayList<>();
		areas = new ArrayList<>();
		coins = new ArrayList<>();
		
		x = GameUtilities.OFFSET;
		y = GameUtilities.OFFSET;
		
		Wall wall;
		Baggage b;
		Area a;
		Coin c;
		for (int i = 0; i < level.ShowLevel(levelnumber).length(); i++) {
			char item = level.ShowLevel(levelnumber).charAt(i);
			switch (item) {
			case '\n':
				y += GameUtilities.SPACE;
				if (this.w < x) {
					this.w = x;
				}
				x = GameUtilities.OFFSET;
				break;
			
			case GameUtilities.Wall:
				wall = new Wall(x, y);
				walls.add(wall);
				x += GameUtilities.SPACE;
				break;
			
			case GameUtilities.Baggage:
				b = new Baggage(x, y);
				baggs.add(b);
				x += GameUtilities.SPACE;
				break;
			
			case GameUtilities.Area:
				a = new Area(x, y);
				areas.add(a);
				x += GameUtilities.SPACE;
				break;
				
				case GameUtilities.Player:
					player = new SinglePlayer(x, y);
				x += GameUtilities.SPACE;
				break;
			
			case GameUtilities.MultiPlayer:
				player2 = new MultiPlayer(x, y);
				x += GameUtilities.SPACE;
				break;
			
			case GameUtilities.MAP_SPACE:
				x += GameUtilities.SPACE;
				break;
			
			case GameUtilities.Coin:
				c = new Coin(x, y);
				coins.add(c);
				x += GameUtilities.SPACE;
				break;
			
			default:
				break;
			}
			h = y;
		}
	}
	public boolean checkBagCollision(Actor actor, int type) {
		switch (type) {
		case GameUtilities.LEFT_COLLISION:

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);

				if (actor.isLeftColliSion(bag)) {

					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isLeftColliSion(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, GameUtilities.LEFT_COLLISION)) {
							return true;
						}
					}
					bag.move(-GameUtilities.SPACE, 0);
				}
			}
			return false;
		case GameUtilities.RIGHT_COLLISION:
			for (int i = 0; i < baggs.size(); i++) {
				
				Baggage bag = baggs.get(i);
				
				if (actor.isRightCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {
						Baggage item = baggs.get(j);
						if (!bag.equals(item)) {
							if (bag.isRightCollision(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, GameUtilities.RIGHT_COLLISION)) {
							return true;
						}
					}
					bag.move(GameUtilities.SPACE, 0);
				}
			}
			return false;

		case GameUtilities.TOP_COLLISION:
			for (int i = 0; i < baggs.size(); i++) {
				
				Baggage bag = baggs.get(i);
			
				if (actor.isTopCollision(bag)) {
					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {
							if (bag.isTopCollision(item)) {
								return true;
							}
						}
						if (checkWallCollision(bag, GameUtilities.TOP_COLLISION)) {
							return true;
						}
					}
					bag.move(0, -GameUtilities.SPACE);
				}
			}
			return false;
		case GameUtilities.BOTTOM_COLLISION:

			for (int i = 0; i < baggs.size(); i++) {

				Baggage bag = baggs.get(i);

				if (actor.isBottomCollision(bag)) {

					for (int j = 0; j < baggs.size(); j++) {

						Baggage item = baggs.get(j);

						if (!bag.equals(item)) {

							if (bag.isBottomCollision(item)) {
								return true;
							}
					}
						if (checkWallCollision(bag, GameUtilities.BOTTOM_COLLISION)) {
							return true;
						}
					}
				bag.move(0, GameUtilities.SPACE);
			}
			}

			break;

		default:
			break;
		}

		return false;
	}
	public boolean checkWallCollision(Actor actor, int type) {

		switch (type) {

		case GameUtilities.LEFT_COLLISION:
			
			for (int i = 0; i < walls.size(); i++) {

				Wall wall = walls.get(i);

				if (actor.isLeftColliSion(wall)) {
					return true;
				}
			}
			return false;

		case GameUtilities.RIGHT_COLLISION:

			for (int i = 0; i < walls.size(); i++) {

				Wall wall = walls.get(i);

				if (actor.isRightCollision(wall)) {
					return true;
				}
			}
			return false;

		case GameUtilities.TOP_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);

				if (actor.isTopCollision(wall)) {
					return true;
				}
			}
			return false;
	
		case GameUtilities.BOTTOM_COLLISION:
			for (int i = 0; i < walls.size(); i++) {
				Wall wall = walls.get(i);
				if (actor.isBottomCollision(wall)) {
				return true;
			}
			}
			return false;

		default:
			break;
		}
		return false;
	}
}


