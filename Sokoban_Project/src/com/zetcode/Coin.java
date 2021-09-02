package com.zetcode;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Coin extends Actor {
	
	public Coin(int x, int y) {
		super(x,y);
		initCoin();
	}

	private void initCoin() {
		// TODO Auto-generated method stub
		ImageIcon coin = new ImageIcon("src/resources/coin.png");
		Image image = coin.getImage();
		Image coinImage = image.getScaledInstance(18, 18,
				Image.SCALE_SMOOTH);
		setImage(coinImage);		
	}

}










