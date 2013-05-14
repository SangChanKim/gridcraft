package gridcraft.blocks;

import gridcraft.items.Item;

public class Block extends Item{
	
	private double strength = 100.0; 
	
	public Block(double s){
		setColor(null);
		strength = s; 
	}
	
	public void act(){
		if(strength <= 0.0){
			removeSelfFromGrid(); 
		}
	}
	
	public void setStrength(double amount){
		strength += amount; 
	}
	
	public double getStrength(){
		return strength; 
	}

	@Override
	public String getType() {
		return "Block"; 
	}
	
}
