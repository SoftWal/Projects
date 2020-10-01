package Game.World;

import java.awt.Graphics;

import java.awt.Rectangle;

import Main.Handler;
import Resources.Images;

public class EmptyArea extends BaseArea {
	private Rectangle EmptyArea;

    EmptyArea(Handler handler, int yPosition) {
        super(handler, yPosition);
    }
    
    @Override
    public void render(Graphics g) {
        for (int i = 0; i < 9; i++) {
            g.drawImage(Images.emptyArea, i*64, yPosition,64,66, null);
        }
    }
    @Override
    public Rectangle GetCollision() {
    	return EmptyArea;
}
}