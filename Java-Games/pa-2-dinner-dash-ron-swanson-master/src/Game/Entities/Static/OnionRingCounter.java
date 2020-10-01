package Game.Entities.Static;

import Main.Handler;
import Resources.Images;

public class OnionRingCounter extends BaseCounter {
    public OnionRingCounter(int xPos, int yPos, Handler handler) {
        super(Images.kitchenCounter[8], xPos, yPos,96,108,handler);
        item = Item.onion;
    }
    
}
