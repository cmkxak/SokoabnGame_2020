package com.zetcode;

import java.awt.Image;
import java.util.ArrayList;

public class Actor implements ActorObject{

	private int SPACE = GameUtilities.SPACE;

	protected int x;
	protected int y;
	private int dx;
	private int dy;
	private Image image;

	public Actor(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image img) {
		image = img;
	}

	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}

	public void setX(int x) {

		this.x = x;
	}

	public void setY(int y) {

		this.y = y;
	}

	public boolean isLeftColliSion(ActorObject actor) {

		return getX() - SPACE == actor.getX() && getY() == actor.getY();
	}

	public boolean isRightCollision(ActorObject actor) {

		return getX() + SPACE == actor.getX() && getY() == actor.getY();
	}

	public boolean isTopCollision(ActorObject actor) {

		return getY() - SPACE == actor.getY() && getX() == actor.getX();
	}

	public boolean isBottomCollision(ActorObject actor) {

		return getY() + SPACE == actor.getY() && getX() == actor.getX();
	}
	
}
