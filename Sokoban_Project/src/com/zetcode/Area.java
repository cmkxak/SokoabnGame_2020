package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Area extends Actor {

    public Area(int x, int y) {
        super(x, y);
        
        initArea();
    }
    
    private void initArea() {
        ImageIcon iicon = new ImageIcon("src/resources/newarea.png");
        Image image = iicon.getImage();
        Image changeImg = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        setImage(changeImg);
    }
}
