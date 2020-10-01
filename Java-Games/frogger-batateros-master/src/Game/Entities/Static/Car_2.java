
package Game.Entities.Static;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Resources.Images;

public class Car_2 extends StaticBase {
	
	private Rectangle car_2;
	
	public Car_2(Handler handler,int xPosition, int yPosition) {
        super(handler);
        // Sets original position to be this one.
        this.setX(xPosition); 
        this.setY(yPosition);
    }
	@Override
	public void render(Graphics g) {
    	
    	g.drawImage(Images.car_2, this.getX(), this.getY(), 128, 64, null);
    	car_2 = new Rectangle(this.getX()+40, this.getY(), 50, 60);
    }
	
	@Override
    public Rectangle GetCollision() {
    	return car_2;
    }
}



