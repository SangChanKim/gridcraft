package gridcraft;

import gridcraft.mobs.Player;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.util.ArrayList;


public class OverWorld extends ActorWorld {
	// first player is the local player
	private ArrayList<Player> players = new ArrayList<Player>(); 
	private Grid gr;

	public int spawnX = 0;
	public int spawnY = 0; 

	public OverWorld(){
		gr = new UnboundedGrid(); 
		this.setGrid(gr);
	}
	
	public boolean keyPressed(String description, Location loc){
		players.get(0).act(description);
		return false;
	}

	public void step(){
		super.step(); 
		setMessage(players.get(0).toString());
	}

	public void addPlayer(Player p){
		this.add(p);
		players.add(p); 
	}
	

}
