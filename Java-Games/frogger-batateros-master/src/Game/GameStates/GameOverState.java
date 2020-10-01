
package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

import Game.Entities.Dynamic.Player;

public class GameOverState extends State {
	//private int pointsdisplay = Player.points;
	private int count = 0;
    private UIManager uiManager;

	public GameOverState(Handler handler) {
		  super(handler);
	      uiManager = new UIManager(handler);
	      handler.getMouseManager().setUimanager(uiManager);
	      uiManager.addObjects(new UIImageButton(33 + 192 * 2,  handler.getGame().getHeight() - 150, 128, 64, Images.BTitle, () -> {
	            handler.getMouseManager().setUimanager(null);
	            State.setState(handler.getGame().menuState);
	        }));
	      
	      uiManager.addObjects(new UIImageButton(33,  handler.getGame().getHeight() - 150, 128, 64, Images.butstart, () -> {
	            handler.getMouseManager().setUimanager(null);
	            handler.getGame().reStart();
	            State.setState(handler.getGame().gameState);
	        }));
	      
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		 handler.getMouseManager().setUimanager(uiManager);
	        uiManager.tick();
	        count++;
	        if( count>=30){
	            count=30;
	        }
	        if(handler.getKeyManager().pbutt && count>=30){
	            count=0;
	            State.setState(handler.getGame().gameState);
	        }
	       // handler.getPlayer().points = Player.pointcounter;
	        Player.pointtracker = 0;
	        
	        
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
		 g.drawImage(Images.GameOver,0,0,handler.getGame().getWidth(),handler.getGame().getHeight(),null);
		 Font font = new Font ("SansSerif", Font.PLAIN, 24);
	       g.setFont(font);
	       g.setColor(Color.GREEN );
	       g.drawString("SCORE", 90, 80);

	       g.drawString(String.valueOf(Player.points), 100, 100);   
 

		 uiManager.Render(g);
	        
		 
		
	}

}

