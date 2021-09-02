package com.zetcode;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MultiPlayer extends MoveManager{

	public MultiPlayer(int x, int y) {
		super(x, y);

		initPlayer();
	}

	private void initPlayer() {
		ImageIcon rudolph = new ImageIcon("src/resources/newsokoban2.png");
		Image image = rudolph.getImage();
		Image changesize = image.getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		setImage(changesize);
		
	}
}


