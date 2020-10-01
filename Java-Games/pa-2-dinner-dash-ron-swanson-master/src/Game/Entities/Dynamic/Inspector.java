package Game.Entities.Dynamic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import Game.Entities.Static.Burger;
import Game.Entities.Static.Item;
import Game.Entities.Static.Order;
import Main.Handler;
import Resources.Images;

public class Inspector extends Client{
	public static boolean spawned = false;
	public static boolean served = false;
	public static boolean dissapointed = false;
	public static boolean effectsapplied = false;
	public static boolean finalized = false;
	public Inspector(int xPos, int yPos, Handler handler) {
		super(xPos,yPos,handler);
	}
	public void isServed() {
		served = true;
	}
	
	@Override
	public void tick() {
		patience--;
		if(patience<= 0) {
			isLeaving = true;
			dissapointed = true;
			finalized = true;
			handler.getPlayer().lostcust += 1;
		}
	}
	public void render(Graphics g) {
		if(!isLeaving) {
			 g.drawImage(Images.tint(sprite,1.0f,((float)patience/(float)OGpatience),((float)patience/(float)OGpatience)),xPos,yPos,width,height,null);
			 g.setColor(Color.YELLOW);
			 g.setFont(new Font("ComicSans", Font.BOLD, 32));
			 g.drawString("<--------Inspector",xPos + 120,yPos + 50);
			 ((Burger)order.food).render(g);
			 
		}
	}
	
}


