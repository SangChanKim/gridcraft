package gridcraft.items;

import info.gridworld.actor.Actor;

import java.util.ArrayList;


public class Item extends Actor {
	private double defaultMiningStrength = 0.0; 
	private double defaultAttackStrength = 0.0; 
	
	public Item(){
		defaultMiningStrength = 10.0;
		defaultAttackStrength = 20.0; 
	}
	
	public Item(double dms, double das){
		defaultMiningStrength = dms;
		defaultAttackStrength = das; 
	}
	
	public void craft(ArrayList<ArrayList<Item>> craftMaterials){
		
	}
	
	public double getDefaultMiningStrength(){
		return defaultMiningStrength; 
	}
	
	public double getDefaultAttackStrength(){
		return defaultAttackStrength; 
	}
	
}
