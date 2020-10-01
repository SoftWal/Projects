   package Game.World;

import Game.Entities.Dynamic.Anti_V;
import Game.Entities.Dynamic.Client;
import Game.Entities.Dynamic.Inspector;
import Game.Entities.Dynamic.Player;
import Game.Entities.Static.BaseCounter;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class BaseWorld {

    public BufferedImage Background;

    public BaseCounter Counters[];

    public Handler handler;

    public ArrayList<Client> clients = new ArrayList<>();

    public BaseWorld(BufferedImage Background, BaseCounter Counters[], Handler handler, Player player){
        this.Background = Background;
        this.Counters = Counters;
        this.handler=handler;
        handler.setWorld(this);
        handler.setPlayer(player);
    }

    public Client generateClient(){
    	int possibilities = new Random().nextInt((9-3)+ 1)+ 3;
    	if(possibilities == 3 && Inspector.spawned == false) {
    		Inspector inspector = new Inspector(0,96,handler);
    		Inspector.spawned = true;
    		this.clients.add(inspector);
    		return inspector;
    	}
    	
        Client client =  new Client(0,96,handler);
        if(Inspector.dissapointed ==true && Inspector.finalized == true) {
        	client.setPatience((int) (client.getPatience() - client.getOGpatience() * 0.06));
        }
        else if (Inspector.dissapointed == false && Inspector.finalized == true) {
        	client.setPatience((int) (client.getPatience() + client.getOGpatience() * 0.10));
        }
        if(possibilities == 7) {
        	Anti_V Jorge = new Anti_V(0,96,handler);
        	this.clients.add(Jorge);
        	return Jorge;
        }
        this.clients.add(client);
        return client;
    }

    public void tick(){

    }

    public void render(Graphics g){

    }
}
