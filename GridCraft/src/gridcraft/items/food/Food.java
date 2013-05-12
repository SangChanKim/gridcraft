package gridcraft.items.food;

import gridcraft.items.Item;

public class Food extends Item {
	private double heartValue = 0; 
	
	public Food(double heartValue){
		super(); 
		this.heartValue = heartValue; 
	}
	public double getHeartValue(){
		return heartValue; 
	}
}
