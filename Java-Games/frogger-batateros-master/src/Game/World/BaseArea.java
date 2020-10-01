package Game.World;

import java.awt.Graphics;

//import com.sun.javafx.geom.Rectangle;
//import javafx.scene.shape.Rectangle;
import java.awt.Rectangle;
import Main.Handler;

public class BaseArea {
	Handler handler;
    
    
    int yPosition;
    
    
    public boolean canStepOn = true;

    /*
     * Constructs the yPosition of the Tile.
     */
    BaseArea(Handler handler, int yPosition) {
        this.handler = handler;
        this.yPosition = yPosition;
       
    }

    public void tick(){

    }
    
    // Draws the tile.
    public void render(Graphics g) {
    	
    }
    
    public int getYPosition() {
    	return this.yPosition;
    }
    
    public void setYPosition(int position) {
    	this.yPosition = position;
    }
    
   
	public Rectangle GetCollision() {
		return new Rectangle();
	}
}