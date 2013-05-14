package gridcraft.mobs;

import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.food.ZombieFlesh;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Zombie extends Mob implements Lootable{
	
	
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


	@Override
	public ArrayList<Item> loot() {
		int numOfFlesh = (int) (Math.random()*6);
		ArrayList<Item> loot = new ArrayList<Item>();
		
		for(int i = 0; i < numOfFlesh; i++){
			loot.add(new ZombieFlesh());
		}
		
		return loot; 
	}
	
}
