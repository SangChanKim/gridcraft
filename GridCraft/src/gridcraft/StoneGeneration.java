package gridcraft;

import gridcraft.blocks.WoodBlock;
import info.gridworld.grid.Location;

import java.util.Random;

public class StoneGeneration extends TreeGeneration{
	public void act(){
		int i = 0; 
		while(i < 10){
			Location oldLoc = this.getLocation(); 
			int dir = new Random().nextInt(8) * 45; 
			Location loc = this.getLocation().getAdjacentLocation(dir);
			if(!(this.getGrid().get(loc) instanceof StoneGeneration)){
				moveTo(loc); 
				StoneGeneration tree = new StoneGeneration();
				tree.putSelfInGrid(this.getGrid(), oldLoc);
				i++; 
			}
		}
	}

}
