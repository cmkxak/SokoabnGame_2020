package com.zetcode;

import java.util.ArrayList;

public class MoveManager extends Actor {
	private int dx;
	private int dy;
	private int moved;
	private int maxmoving;
	private int levelnumber;
	protected int[] pos = new int[2];
	protected ArrayList<int[]> moves = new ArrayList<>();
	
	LevelManage level;
	public MoveManager(int x, int y) {
		super(x, y);
		dx = x;
		dy = y;
		// TODO Auto-generated constructor stub
	}
	public void move(int x, int y) {
		dx = this.x + x;
		dy = this.y + y;
		setX(dx);
		setY(dy);
	}
	public void savePos() {
		moves.add(new int[] { x, y });
	}
	public void undoMove() {
		pos = moves.get(moves.size() - 1);
		setX(pos[0]);
		setY(pos[1]);
		moves.remove(moves.size() - 1);
	}

	public void countUndoMove() {
		moved--;
	}
	public void countMoved() {
		moved++;
	}
	public int getMoved() {
		return moved;
	}

	public void setMaxMoving() {
		level = new LevelManage();
		maxmoving = level.getMaxMoving(levelnumber);
	}

	public int getMaxMoving() {
		return maxmoving;
	}
}








