package com.zetcode;

import java.awt.Image;
import java.io.BufferedInputStream;

import javax.swing.ImageIcon;

public class SinglePlayer extends MoveManager {
	private ImageIcon iicon_santa;

	public SinglePlayer(int x, int y) {
		super(x, y);

		initPlayer();
	}

	private void initPlayer() {
		ImageIcon santa = new ImageIcon("src/resources/newsokoban.png");
		Image image = santa.getImage();
		Image changeImg = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
		setImage(changeImg);
		}
	
	public void changeSanta(String color)
    {
       iicon_santa = new ImageIcon("src/resources/santa/" + color +".png");
        setImage(iicon_santa.getImage());
    }
}







