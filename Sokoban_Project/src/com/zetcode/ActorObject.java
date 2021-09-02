package com.zetcode;

import java.awt.Image;

public interface ActorObject {
	public int getX();
	public int getY();
	public Image getImage();
	public void setImage(Image image);
	public boolean isLeftColliSion(ActorObject obj);
	public boolean isRightCollision(ActorObject obj);
	public boolean isTopCollision(ActorObject obj);
	public boolean isBottomCollision(ActorObject obj);
}
