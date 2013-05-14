package gridcraft.blocks;

import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.food.Apple;
import gridcraft.items.resources.Wood;

import java.util.ArrayList;


public class WoodBlock extends Block implements Lootable{
	
	
	public WoodBlock(){
		super(250); 
	}

	@Override
	public ArrayList<Item> loot() {
		int numOfApple = (int) (Math.random() * 3); 
		ArrayList<Item> loot = new ArrayList<Item>();
		
		for(int i = 0; i < numOfApple; i++){
			loot.add(new Apple());
		}
		
		int numOfWood = (int) (Math.random() * 4); 
		for(int i = 0; i < numOfWood; i++){
			loot.add(new Wood()); 
		}
		
		return loot;
	}
	
	public String getType(){
		return "Wood Block";
	}
	


}
