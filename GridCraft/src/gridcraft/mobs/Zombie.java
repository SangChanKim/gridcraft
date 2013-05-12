package gridcraft.mobs;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Zombie extends Mob{
	
	public Zombie(){
		super(750, 30.0); 
	}
	
	
	public void act(){
		selectTarget(); 
		Location nextLoc = selectMoveLocation(getMoveLocations()); 
		makeMove(nextLoc); 
	}
	
	public void selectTarget(){
		ArrayList<Actor> neighbors = this.getGrid().getNeighbors(this.getLocation());
		for(Actor a: neighbors){
			if(a instanceof Player){
				this.attack((Player) a);
			}
		}
	}
	
	public ArrayList<Location> getMoveLocations(){
		return this.getGrid().getEmptyAdjacentLocations(this.getLocation());
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs){
		int n = locs.size(); 
		if(n == 0)
			return getLocation(); 
		int r = (int)(Math.random() * n);
		return locs.get(r); 
		
	}
	
	public void makeMove(Location loc){
		if(loc == null)
			removeSelfFromGrid(); 
		else
			moveTo(loc);
	}
	
}
