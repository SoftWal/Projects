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

public class Anti_V extends Client{
	int var =(int)( 0.08*OGpatience);
	public Anti_V(int xPos, int yPos, Handler handler) {
		super(xPos,yPos,handler);
	}
	
	
	@Override
	public void tick() {
		patience--;
		if(patience<= 0) {
			isLeaving = true;
			handler.getPlayer().lostcust += 1;
		}
		//Checks everytime the client's patience reaches 8%.
		if(patience % var == 0) {
			annoying();
		}
	}
	public void render(Graphics g) {
		if(!isLeaving) {
			 g.drawImage(Images.tint(sprite,1.0f,((float)patience/(float)OGpatience),((float)patience/(float)OGpatience)),xPos,yPos,width,height,null);
			 g.setColor(Color.YELLOW);
			 g.setFont(new Font("ComicSans", Font.BOLD, 32));
			 g.drawString("<--------Anti_V",xPos + 120,yPos + 50);
			 ((Burger)order.food).render(g);
			 
		}
	}
	//Annoys the client in the front or in the back Depending on the position of the Anti_V.
	public void annoying() {
		int Anti_v_pos = 0;
		int rnd = new Random().nextInt(2);
		for (Client client: handler.getWorld().clients) {
			if(client.equals(this)) {
				if(rnd == 0) {
					if(Anti_v_pos == 0 && handler.getWorld().clients.size() > 1) {
						handler.getWorld().clients.get(Anti_v_pos + 1).patience -= handler.getWorld().clients.get(Anti_v_pos + 1).OGpatience * 0.04;
					}
					else if (Anti_v_pos != 0) {
						handler.getWorld().clients.get(Anti_v_pos - 1).patience -= handler.getWorld().clients.get(Anti_v_pos - 1).OGpatience * 0.04;
					}
					
				}
				if(rnd == 1) {
					if(Anti_v_pos == 4 && handler.getWorld().clients.size() == 5) {
						handler.getWorld().clients.get(Anti_v_pos - 1).patience -= handler.getWorld().clients.get(Anti_v_pos - 1).OGpatience * 0.04;
					}
					else if (Anti_v_pos !=4 && handler.getWorld().clients.size() > Anti_v_pos + 2) {
						handler.getWorld().clients.get(Anti_v_pos + 1).patience -= handler.getWorld().clients.get(Anti_v_pos + 1).OGpatience * 0.04;
					}
				}
			}
		Anti_v_pos++;
		}
	}
	
	
}


