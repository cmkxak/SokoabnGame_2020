package com.zetcode;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Baggage extends MoveManager {

    public Baggage(int x, int y) {
        super(x, y);
        
        initBaggage();
    }
    
    public void initBaggage() {
        
    	ImageIcon iicon = new ImageIcon("src/resources/newbaggage.png");
        Image image = iicon.getImage();
        Image changeImg = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
        setImage(changeImg);
    }

    public void move(int x, int y) {
        
        int dx = getX() + x;
        int dy = getY() + y;
        
        setX(dx);
        setY(dy);
    }
}
