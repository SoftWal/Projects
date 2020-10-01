package Game.Entities.Dynamic;

import Game.Entities.Static.*;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import Main.GameSetUp;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends BaseDynamicEntity {
	Item item;
	float money;
	public static boolean magic = false;
	int speed = 6;
	int custSelected = 0;
	public static int lostcust = 0;
	int served = 0;
	private Burger burger;
	private String direction = "right";
	private int interactionCounter = 0;
	private Animation playerAnim;
	public Player(BufferedImage sprite, int xPos, int yPos, Handler handler) {
		super(sprite, xPos, yPos,82,112, handler);
		createBurger();
		playerAnim = new Animation(120,Images.chef);
	}

	public void createBurger(){
		burger = new Burger(handler.getWidth() - 110, 100, 100, 50);

	}

	public void tick(){
		playerAnim.tick();
		MagicCounter.count++;
		if(xPos + width >= handler.getWidth()){
			direction = "left";

		} else if(xPos <= 0){
			direction = "right";
		}
		if (direction.equals("right")){
			xPos+=speed;
		} else{
			xPos-=speed;
		}
		//Win 
		if(money >= 50) {
			State.setState(GameSetUp.winState);
		}
		//Lose
		if(lostcust == 10) {
			System.out.println(lostcust);
			State.setState(GameSetUp.lossState);
		}
		///////////////////////////////////////////////////////
		if(Inspector.dissapointed == true && Inspector.effectsapplied == false && Inspector.finalized == true) {
			money -= money/2;
			Inspector.effectsapplied = true;
		}
		if(Inspector.dissapointed == false && Inspector.effectsapplied == false && Inspector.finalized == true) {
			for(Client select: handler.getWorld().clients) {
				select.patience += select.OGpatience * 0.12;
			}
			Inspector.effectsapplied = true;
		}
		/////////////////////////////////////////////////////
		if (interactionCounter > 15 && handler.getKeyManager().attbut){
			interact();
			interactionCounter = 0;
		} else {
			interactionCounter++;
		}
		if(handler.getKeyManager().fattbut){
			for(BaseCounter counter: handler.getWorld().Counters){
				if (counter instanceof PlateCounter && counter.isInteractable()){
					createBurger();
				}
			}
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			for(BaseCounter counter: handler.getWorld().Counters) {
				if (counter instanceof MagicCounter && counter.isInteractable() && MagicCounter.count >= 1000) {
					
					MagicCounter.magic2 = true;
					MagicCounter.tadah();
				}
			}
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1) && handler.getWorld().clients.size() >=1 ) {
			custSelected = 1;
			ringCustomer();
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2) && handler.getWorld().clients.size() >=2 ) {
			custSelected = 2;
			ringCustomer();
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_3) && handler.getWorld().clients.size() >=3 ) {
			custSelected = 3;
			ringCustomer();
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_4) && handler.getWorld().clients.size() >=4 ) {
			custSelected = 4;
			ringCustomer();
		}
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_5) && handler.getWorld().clients.size() == 5) {
			custSelected = 5;
			ringCustomer();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Slows the speed.
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SHIFT)) {
			speed = 3;
		}
		// Boost in the speed..
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_CAPS_LOCK)) {
			speed++;
		}
		//Interactions with the costumers.
		//No R needed
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)){
//			for(BaseCounter counter: handler.getWorld().Counters) {
//				if (counter instanceof PlateCounter && counter.isInteractable()) {
//					ringCustomer();
//				}
//			}
//
//		} 
		
	}

	private void ringCustomer() {

		for(Client client: handler.getWorld().clients){
			Client selected = handler.getWorld().clients.get(custSelected-1);
			boolean matched = ((Burger)client.order.food).equals(handler.getCurrentBurger());
			if(matched){
				served++;
				if(selected instanceof Inspector) {
					((Inspector) selected).isServed();
					System.out.println("An Inspector has been served well");
					Inspector.spawned = true;
				}
				if(selected.patience > selected.OGpatience/2) {
					money+=client.order.value*0.15;
				}
				money+= selected.order.value;
				//Patience boost.
				Client.patience += Client.OGpatience/4;
				handler.getWorld().clients.remove(selected);
				handler.getPlayer().createBurger();
				for(Client selected1 : handler.getWorld().clients) {
					if(selected1.patience + selected1.OGpatience*0.25 > selected1.OGpatience) {
						selected1.patience = selected1.OGpatience;
					}
					else {
						selected1.patience += selected1.OGpatience*0.25;
					}
				}
				custSelected = 1;
				System.out.println("Total money earned is: " + String.valueOf(money));
				return;
			}

		}
	}

	public void render(Graphics g) {
		if(direction=="right") {
			g.drawImage(playerAnim.getCurrentFrame(), xPos, yPos, width, height, null);
		}else{
			g.drawImage(playerAnim.getCurrentFrame(), xPos+width, yPos, -width, height, null);

		}
		g.setColor(Color.green);
		burger.render(g);
		g.setColor(Color.green);
		if(MagicCounter.count >= 1000) {
			g.drawString("Magic Power", handler.getWidth()-90, handler.getHeight()-350);
		}
		g.fillRect(handler.getWidth()/2 -210, 3, 320, 32);;
		g.setColor(Color.yellow);
		g.setFont(new Font("ComicSans", Font.BOLD, 32));
		g.drawString("Money Earned: " + money, handler.getWidth()/2 -200, 30);
		g.drawString("Clients gone " + lostcust, handler.getWidth()/2 + 200, 30);
		g.drawString("Served " + served, handler.getWidth()/2 - 400, 30);

	}

	public void interact(){
		for(BaseCounter counter: handler.getWorld().Counters){
			if (counter.isInteractable()){
				counter.interact();
			}
		}
	}
	public Burger getBurger(){
		return this.burger;
	}
}
