package gridcraft.blocks;

import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.resources.Iron;

import java.util.ArrayList;

public class StoneBlock extends Block implements Lootable{
	
	public StoneBlock(){
		super(450); 
	}

	@Override
	public ArrayList<Item> loot() {
		int random = (int) (Math.random()*8); 
		ArrayList<Item> loot = new ArrayList<Item>(); 
		
		if(random < 3){
			for(int i = 0; i < random; i++){
				loot.add(new Iron());
			}
		}
	
		return loot();
	}
	
	public String getType(){
		return "Stone Block";
	}
	
	
}
