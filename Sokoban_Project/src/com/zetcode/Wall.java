package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Wall extends Actor {

  

    public Wall(int x, int y) {
        super(x, y);
        
        initWall();
    }
    private void initWall() {
        ImageIcon iicon = new ImageIcon("src/resources/wall.png");
        Image image = iicon.getImage();
        Image changeImg = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        setImage(changeImg);
      
    }
}
