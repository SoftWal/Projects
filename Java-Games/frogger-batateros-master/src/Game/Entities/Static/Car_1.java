
package Game.Entities.Static;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Resources.Images;

public class Car_1 extends StaticBase {
	
	private Rectangle car_1;
	
	public Car_1(Handler handler,int xPosition, int yPosition) {
        super(handler);
        // Sets original position to be this one.
        this.setX(xPosition); 
        this.setY(yPosition);
    }
	@Override
	public void render(Graphics g) {
    	
    	g.drawImage(Images.car_1, this.getX(), this.getY(), 128, 64, null);
    	car_1 = new Rectangle(this.getX()+40, this.getY(), 50, 60);
    }
	
	@Override
    public Rectangle GetCollision() {
    	return car_1;
    }
}






