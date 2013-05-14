package gridcraft.mobs;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import gridcraft.blocks.Block; 


public class Mob extends Actor {
	
	protected double health = 0; 
	protected double damage = 0; 
	protected double maxHealth = 1000; //always 1000
	
	protected double defaultDamage = 50.0; //always 50
	
	//create a default mob
	public Mob(){
		setColor(null); 
		health = 500.0; 
		damage = defaultDamage; 
	}
	
	public Mob(double health, double damage){
		setColor(null);
		this.health = health; 
		this.damage = damage; 
	}
	
	public void act(){
		if(health <= 0.0){
			removeSelfFromGrid(); 
		}
	}
	
	public double getDefaultDamage(){
		return defaultDamage;
	}
	
	public void attack(Mob target){
		target.setHealth(-1*this.getCurrentDamage()); 
	}
	
	
	public void setHealth(double x0){
		double temp = health + x0; 
		if(temp < maxHealth){
			health += x0; 
		}
		else{
			System.out.println("I'm already healthy"); 
		}
	}
	
	public double getHealth(){
		return health; 
	}
	
	public double getCurrentDamage(){
		return damage; 
	}
	
	
	public void act(String description){
		Location loc = null; 
		int directionToFace = -1; 
		if(description.equals("W")){
			loc = this.getLocation().getAdjacentLocation(0);
			directionToFace = 0; 
		}
		else if(description.equals("S")){ 
			loc = this.getLocation().getAdjacentLocation(180); 
			directionToFace = 180; 
		}
		else if(description.equals("A")){
			loc = this.getLocation().getAdjacentLocation(270);
			directionToFace = 270; 
		}
		else if(description.equals("D")){
			loc = this.getLocation().getAdjacentLocation(90); 
			directionToFace = 90; 
		}
		
		if(directionToFace >= 0){
			setDirection(directionToFace); 
		}
		
		
		if(loc != null){
			if(this.getGrid().isValid(loc) && this.getGrid().get(loc) == null){
				moveTo(loc); 
			}
		}
	}
	
}
