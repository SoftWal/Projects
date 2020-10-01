
package Game.World;

import Game.GameStates.GameOverState;
import Game.Entities.Dynamic.Player;
import Game.Entities.Static.LillyPad;
import Game.Entities.Static.Log;
import Game.Entities.Static.StaticBase;
import Game.World.WaterArea;
import Game.Entities.Static.Tree;
import Game.Entities.Static.Turtle;
import Game.Entities.Static.Truck;
import Game.Entities.Static.Car_1;
import Game.Entities.Static.Car_2;
import Game.GameStates.State;
import Main.GameSetUp;
import Main.Handler;
import Main.Launch;
import UI.UIManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Literally the world. This class is very important to understand.
 * Here we spawn our hazards (StaticBase), and our tiles (BaseArea)
 * 
 * We move the screen, the player, and some hazards. 
 * 				How? Figure it out.
 */

public class WorldManager {
	
	public int a;
	boolean Lpad = true;
	public ArrayList LillyCoord;
	public ArrayList LillyCoord2;
	public ArrayList tempList;
	public int LillyLoc;
	public int xPosition;
	private ArrayList<BaseArea> AreasAvailables;			// Lake, empty and grass area (NOTE: The empty tile is just the "sand" tile. Ik, weird name.)
	private ArrayList<StaticBase> StaticEntitiesAvailables;	// Has the hazards: LillyPad, Log, Tree, and Turtle.

	private ArrayList<BaseArea> SpawnedAreas;				// Areas currently on world
	private ArrayList<StaticBase> SpawnedHazards;			// Hazards currently on world.
    
    Long time;
    Boolean reset = true;
    
    Handler handler;


	private Player player;									// How do we find the frog coordinates? How do we find the Collisions? This bad boy.
    
    UIManager object = new UIManager(handler);
    UI.UIManager.Vector object2 = object.new Vector();


	private ID[][] grid;									
	private int gridWidth,gridHeight;						// Size of the grid. 
	private int movementSpeed;								// Movement of the tiles going downwards.
    

    public WorldManager(Handler handler, int height) {
        this.handler = handler;
        
        LillyCoord = new ArrayList <>();
        LillyCoord2 = new ArrayList <>();
        tempList = new ArrayList <>();
        
        AreasAvailables = new ArrayList<>();				// Here we add the Tiles to be utilized.
        StaticEntitiesAvailables = new ArrayList<>();		// Here we add the Hazards to be utilized.

        AreasAvailables.add(new GrassArea(handler, 0));		
        AreasAvailables.add(new WaterArea(handler, 0));
        AreasAvailables.add(new EmptyArea(handler, 0));

        StaticEntitiesAvailables.add(new LillyPad(handler, 0, 0));
        StaticEntitiesAvailables.add(new Log(handler, 0, 0));
        StaticEntitiesAvailables.add(new Tree(handler, 0 ,0));
        StaticEntitiesAvailables.add(new Turtle(handler, 0, 0));
        StaticEntitiesAvailables.add(new Truck(handler, 0, 0));
        StaticEntitiesAvailables.add(new Car_1(handler,0 ,0 ));
        StaticEntitiesAvailables.add(new Car_2(handler,0 ,0 ));
        
        SpawnedAreas = new ArrayList<>();
        SpawnedHazards = new ArrayList<>();
        
        player = new Player(handler);       

        gridWidth = handler.getWidth()/64;
        gridHeight = handler.getHeight()/64;
        movementSpeed = 1; 
        // movementSpeed = 20; I dare you.
        
        /* 
         * 	Spawn Areas in Map (2 extra areas spawned off screen)
         *  To understand this, go down to randomArea(int yPosition) 
         */
        for(int i=0; i<gridHeight+2; i++) {
        	SpawnedAreas.add(firstGrassAreas((-2+i)*64));	
        	
        }
        	
        player.setX((gridWidth/2)*64);
        player.setY((gridHeight-3)*64);

        // Not used atm.
        grid = new ID[gridWidth][gridHeight];
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                grid[x][y]=ID.EMPTY;
            }
        
        }
        
        
        
    }
    
	public void tick() {
		
		
		if(this.handler.getKeyManager().keyJustPressed(this.handler.getKeyManager().num[2])) {
			this.object2.word = this.object2.word + this.handler.getKeyManager().str[1];
		}
		if(this.handler.getKeyManager().keyJustPressed(this.handler.getKeyManager().num[0])) {
			this.object2.word = this.object2.word + this.handler.getKeyManager().str[2];
		}
		if(this.handler.getKeyManager().keyJustPressed(this.handler.getKeyManager().num[1])) {
			this.object2.word = this.object2.word + this.handler.getKeyManager().str[0];
		}
		if(this.handler.getKeyManager().keyJustPressed(this.handler.getKeyManager().num[3])) {
			this.object2.addVectors();
		}
		if(this.handler.getKeyManager().keyJustPressed(this.handler.getKeyManager().num[4]) && this.object2.isUIInstance) {
			this.object2.scalarProduct(handler);
		}
		
		if(this.reset) {
			time = System.currentTimeMillis();
			this.reset = false;
		}
		
		if(this.object2.isSorted) {
			
			if(System.currentTimeMillis() - this.time >= 2000) {		
				this.object2.setOnScreen(true);	
				this.reset = true;
			}
			
		}
		
		for (BaseArea area : SpawnedAreas) {
			area.tick();
		}
		for (StaticBase hazard : SpawnedHazards) {
			hazard.tick();
		}
		
		
		
		for (int i = 0; i < SpawnedAreas.size(); i++) {
			
			SpawnedAreas.get(i).setYPosition(SpawnedAreas.get(i).getYPosition() + movementSpeed);

			// Check if Area (thus a hazard as well) passed the screen.
			if (SpawnedAreas.get(i).getYPosition() > handler.getHeight()) {
				// Replace with a new random area and position it on top
				SpawnedAreas.set(i, randomArea(-2 * 64));
			}
			//Make sure players position is synchronized with area's movement
			if (SpawnedAreas.get(i).getYPosition() < player.getY()
					&& player.getY() - SpawnedAreas.get(i).getYPosition() < 3) {
				player.setY(SpawnedAreas.get(i).getYPosition());
			}
			// If player gets out of the screen on the lowest part the game ends
			if (player.getY() + player.getHeight() > handler.getHeight()) {
				State.setState(handler.getGame().gameoverState);
			
			}		
		}
		
		frogdeath();
		HazardMovement();
		
		
        player.tick();
        //make player move the same as the areas
        player.setY(player.getY()+movementSpeed); 
        
        object2.tick();
        
			 
       
    }

	public void frogdeath() {		
		//if player touches water with no hazard on top, player dies
		for (int i = 0; i < SpawnedAreas.size(); i++) {
			////
			if(SpawnedAreas.get(i) instanceof WaterArea) {	
				if (player.getPlayerCollision().getY() == SpawnedAreas.get(i).getYPosition()){
				//////
					for (a = 0; a < SpawnedHazards.size(); a++) {
					///
						if(SpawnedHazards.get(a).GetCollision() != null
								&& player.getPlayerCollision().intersects(SpawnedHazards.get(a).GetCollision())						
								) {
							//
							//System.out.println("Activated");
							//check_out = true;
							return;					
						}																						
					////					
					}
					State.setState(handler.getGame().gameoverState);
				}			
			}
		}
	}
	public void HazardMovement() {
		
		for (int i = 0; i < SpawnedHazards.size(); i++) {
			
			// Moves hazard down
			SpawnedHazards.get(i).setY(SpawnedHazards.get(i).getY() + movementSpeed);

			// Moves Log or Turtle to the right
			if (SpawnedHazards.get(i) instanceof Log) {
				SpawnedHazards.get(i).setX(SpawnedHazards.get(i).getX() + 1);
				

				if (SpawnedHazards.get(i).GetCollision() != null
						&& player.getPlayerCollision().intersects(SpawnedHazards.get(i).GetCollision())) {
					
					player.setX(player.getX() + 1);
				}			
			}
			
			
			if (SpawnedHazards.get(i) instanceof Truck){				
				SpawnedHazards.get(i).setX(SpawnedHazards.get(i).getX() + 2);	
				
				if (SpawnedHazards.get(i).GetCollision() != null
						&& player.getPlayerCollision().intersects(SpawnedHazards.get(i).GetCollision())) {					
					State.setState(handler.getGame().gameoverState);
				}	
			}
			else if(SpawnedHazards.get(i) instanceof Car_1) {
				SpawnedHazards.get(i).setX(SpawnedHazards.get(i).getX() - 3);
				
				if (SpawnedHazards.get(i).GetCollision() != null
						&& player.getPlayerCollision().intersects(SpawnedHazards.get(i).GetCollision())) {					
					State.setState(handler.getGame().gameoverState);
				}	
			}
			else if(SpawnedHazards.get(i) instanceof Car_2) {
				SpawnedHazards.get(i).setX(SpawnedHazards.get(i).getX() - 6);
				
				if (SpawnedHazards.get(i).GetCollision() != null
						&& player.getPlayerCollision().intersects(SpawnedHazards.get(i).GetCollision())) {					
					State.setState(handler.getGame().gameoverState);
				}	
			}
			
			
			if (SpawnedHazards.get(i) instanceof Turtle ) {
				SpawnedHazards.get(i).setX(SpawnedHazards.get(i).getX() - 1);
				
				if (SpawnedHazards.get(i).GetCollision() != null
						&& player.getPlayerCollision().intersects(SpawnedHazards.get(i).GetCollision())) {
					player.setX(player.getX() - 1);
				}
			}
				
				
				
				
			
			
			else if (SpawnedHazards.get(i) instanceof Tree 
					
					) {
				if (SpawnedHazards.get(i).GetCollision() != null
						&& player.getPlayerCollision().intersects(SpawnedHazards.get(i).GetCollision())) {	
					player.pointcounter = player.pointcounter - 1;					
					if(player.facing == "UP") {
						player.setY(player.getY() + 64);
						player.pointtracker --;
					}
					else if(player.facing == "DOWN") {
						player.setY(player.getY() - 8);
					}
					else if(player.facing == "RIGHT") {
						player.setX(player.getX() -8);
					}
					else if(player.facing == "LEFT") {
						player.setX(player.getX() +8);
					}
				
				
				}
			}
			
			if (SpawnedHazards.get(i).getY() > handler.getHeight()) {
				SpawnedHazards.remove(i);
			}
			
		}
	}
				
			
			
			
		
	

	
	
    public void render(Graphics g){
    	
       for(BaseArea area : SpawnedAreas) {
    	   area.render(g);
       }
    	
       for (StaticBase hazards : SpawnedHazards) {
    		hazards.render(g);
 
       }
    	
       
       player.render(g);       
       this.object2.render(g);     
       Font font = new Font ("SansSerif", Font.PLAIN, 24);
       g.setFont(font);
       g.setColor(Color.WHITE );
       g.drawString("SCORE", 10, 20);
       g.drawString(String.valueOf(Player.pointtracker), 20, 40);
       
       }
    
    /*
     * Given a yPosition, this method will return a random Area out of the Available ones.)
     * It is also in charge of spawning hazards at a specific condition.
     */
	private BaseArea firstGrassAreas(int yPosition) {
		BaseArea firstGrassAreas;
		firstGrassAreas = new GrassArea(handler, yPosition);
		return firstGrassAreas;
	}
   public BaseArea randomArea(int yPosition) {
    	Random rand = new Random();
    	
    	// From the AreasAvailable, get me any random one.
    	BaseArea randomArea = AreasAvailables.get(rand.nextInt(AreasAvailables.size())); 
    	
    	
    	if(randomArea instanceof GrassArea) {
    		randomArea = new GrassArea(handler, yPosition);
    		SpawnHazard2(yPosition);
    	}
    	else if(randomArea instanceof WaterArea) {
    		randomArea = new WaterArea(handler, yPosition);
    		xPosition = LillyLoc;
    		SpawnHazard(yPosition);  		
    	}
    	else {
    		randomArea = new EmptyArea(handler, yPosition);
    		SpawnHazard3(yPosition);
    	}
    	return randomArea;
    }

	/*
	 * Given a yPositionm this method will add a new hazard to the SpawnedHazards ArrayList
	 */
	private void SpawnHazard(int yPosition) {
		Random rand = new Random();
		int randInt;
		int trigger = 0;
		int choice = rand.nextInt(9);
		// Chooses between Log or Lillypad
		if (choice <=4) {
			
			for(int lop = 64; lop < 1000; lop = lop + 64) {		
			randInt = lop * -4;			
			SpawnedHazards.add(new Log(handler, randInt, yPosition));
			}
			
		}
		
		else if (choice >=6){
			//The Positioning of the Lilly
			
			if (Lpad == true) { 
			randInt = 64 * rand.nextInt(9);								
			SpawnedHazards.add(new LillyPad(handler, randInt , yPosition ));							
			int choice1 = rand.nextInt(15);
			
			if(choice1 <= 15) {
				choice1 = 15;
				
				for(int rndmcounter =  0; rndmcounter < choice1 - 7; rndmcounter++) {					
						randInt = 64 * rand.nextInt(9);
						SpawnedHazards.add(new LillyPad(handler, randInt, yPosition));
						Lpad = false;							
					}				
				}			
			}
			else if (Lpad == false) {
					Lpad = true; 
					for(int lop = 64; lop < 1000; lop = lop + 64) {						
						randInt = lop * -4;						
						SpawnedHazards.add(new Log(handler, randInt, yPosition));
					}					
				}
			}
		
			else {
				for(int lop = 64; lop < 1000; lop++) {				
					lop = lop + 64;
					randInt = lop * 4;
					SpawnedHazards.add(new Turtle(handler, randInt, yPosition));
				}
			}
	}	
				
	
	
	
	private void SpawnHazard2(int yPosition) {
		Random rand = new Random();
		int randInt;
		int choice = rand.nextInt(20);
		// Chooses between Tree or no tree
		if (choice <=13) {
			randInt = 64 * rand.nextInt(9);
			SpawnedHazards.add(new Tree(handler, randInt, yPosition));
			if (choice <= 9) {
				int rndmcounter = rand.nextInt(8);
				for(int rndmcounter2 =  0; rndmcounter2 < rndmcounter; rndmcounter2++) {
					randInt = 64 * rand.nextInt(9);
					SpawnedHazards.add(new Tree(handler, randInt, yPosition));
					
				}
				
			}
		}
		else {
			
		}
	}
	private void SpawnHazard3(int yPosition) {
		Random rand = new Random();
		int randInt;
		int choice = rand.nextInt(9);
		if (choice <=4) {			
			for(int lop = 64; lop < 1000; lop= lop+64) {										
				randInt = lop * -9;				
				SpawnedHazards.add(new Truck(handler, randInt, yPosition));
			}
		}
		else if(choice >= 7){
			for(int lop = 64; lop < 1000; lop++) {				
				lop = lop + 64;
				randInt = lop * 12;
				SpawnedHazards.add(new Car_2(handler, randInt, yPosition));
			}
		}
		else {
			for(int lop = 64; lop < 1000; lop++) {				
				lop = lop + 64;
				randInt = lop * 7;
				SpawnedHazards.add(new Car_1(handler, randInt, yPosition));
			}
		}
	}   
}

