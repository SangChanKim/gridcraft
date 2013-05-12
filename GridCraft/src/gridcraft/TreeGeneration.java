package gridcraft;

import gridcraft.blocks.WoodBlock;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Random;

public class TreeGeneration extends Bug {
	ArrayList<Location> locs = new ArrayList<Location>();  
	
	public void act(){
		int i = 0; 
		while(i < 10){
			Location oldLoc = this.getLocation(); 
			int dir = new Random().nextInt(8) * 45; 
			Location loc = this.getLocation().getAdjacentLocation(dir);
			if(!(this.getGrid().get(loc) instanceof TreeGeneration)){
				moveTo(loc); 
				WoodBlock tree = new WoodBlock();
				tree.putSelfInGrid(this.getGrid(), oldLoc);
				i++; 
			}
		}
	}

}
