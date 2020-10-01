package Game.Entities.Static;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Game.Entities.Dynamic.Client;
import Game.Entities.Dynamic.Player;
import Main.Handler;
import Resources.Images;

public class MagicCounter extends BaseCounter {
	public static int count = 0;
	public static int greatnum = 0;
	public static boolean magic2 = false;
	public MagicCounter(int xPos, int yPos, Handler handler) {
		super(Images.kitchenCounter[1], xPos, yPos,96,102,handler);
	}
	//Makes all of the patience of the clients return to the original.
	public static void Magic() {
		for (Client People : handler.getWorld().clients) {
			People.patience = People.OGpatience;
		}
	}
	//Calls the function Magic in order for the counter to work.
	public static void tadah() {
		if(count >= greatnum) {
			if(magic2) {
				System.out.println("WOW");
				Magic();
				count = 0;
				magic2 = false;
			}

		}
	}
}
