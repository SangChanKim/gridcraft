package gridcraft.mobs;

import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.food.Pork;

import java.util.ArrayList;


public class Pig extends Mob implements Lootable{
	
	public Pig(){
		super(500.0,0.0); 
	}

	@Override
	public ArrayList<Item> loot() {
		int numOfPork = (int) (Math.random()*4);
		ArrayList<Item> loot = new ArrayList<Item>();
		
		for(int i = 0; i < numOfPork; i++){
			loot.add(new Pork());
		}
		return loot;
	}
	
}
