package gridcraft.mobs;

import gridcraft.blocks.Block;
import gridcraft.items.Inventory;
import gridcraft.items.Item;
import gridcraft.items.food.Food;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;


public class Player extends Mob{
	//if item reference variable is null, it should use default values
	//damage value 
	private double miningStrength = 10.0;
	private int currentPlaceOnInv = 0; 
	private Inventory inv = new Inventory(); 
	
	//create a default player
	//default player should have 
	//damage (no item)- 20.0
	//mining (no item)- 10.0
	//health - 1000.0
	
	public Player(Inventory i){
		super(1000.0, 20.0);
		miningStrength = 10.0;
		inv = i; 
	}
	
	public Player(double health, double damage, double ms, Inventory i){
		super(health, damage); 
		miningStrength = ms;
		inv = i; 
	}
	
	public void act(){
		
	}
	
	public void act(String description){
		super.act(description); 
		boolean parsable = true;
		try{
			Integer.parseInt(description);
		}
		catch(NumberFormatException e){
			parsable = false;
		}
		if (parsable){
			int descriptionInt = Integer.parseInt(description);
			switch(descriptionInt){
			case 1:
				changeCurrentItems(0); 
				break; 
			case 2:
				changeCurrentItems(1);
				break; 
			case 3:
				changeCurrentItems(2); 
				break; 
			case 4:
				changeCurrentItems(3); 
				break; 
			case 5:
				changeCurrentItems(4);
				break;
			case 6:
				changeCurrentItems(5);
				break; 
			case 7:
				changeCurrentItems(6); 
				break; 
			case 8:
				changeCurrentItems(7); 
				break; 
			case 9: 
				changeCurrentItems(8); 
				break;
			}
		}
		
		//N is attacks/mines/gathers resources
		if(description.equals("N")){
			int dirFacing =this.getDirection();
			Location frontLoc = this.getLocation().getAdjacentLocation(dirFacing);
			Actor actor = this.getGrid().get(frontLoc); 
			if(actor != null){
				this.attack();
			}
		}

		//M is placing blocks
		if(description.equals("M")){
			int dirFacing = this.getDirection(); 
			Location frontLoc = this.getLocation().getAdjacentLocation(dirFacing); 
			Actor actor = this.getGrid().get(frontLoc); 
			if(actor == null){
				placeBlock(frontLoc); 
			}
		}
	}
	
	public void attack(){
		System.out.println("Attempting to attack"); 
		int dirFacing = this.getDirection();
		Location frontLoc = this.getLocation().getAdjacentLocation(dirFacing);
		Actor actor = this.getGrid().get(frontLoc);
		Item i = inv.getItem(currentPlaceOnInv);
		
		double damageToInflict = 0; 
		if(i != null){
			if(actor instanceof Mob){
				damageToInflict = i.getDefaultAttackStrength() + this.getCurrentDamage(); 
				((Mob) actor).setHealth(-damageToInflict); 
			}
			else if(actor instanceof Block){
				damageToInflict = i.getDefaultMiningStrength() + this.miningStrength;
				((Block) actor).setStrength(-damageToInflict); 
			}
		}
		else{
			if(actor instanceof Mob){
				damageToInflict = this.getCurrentDamage(); 
				((Mob) actor).setHealth(-damageToInflict); 
			}
			else if(actor instanceof Block){
				damageToInflict = this.miningStrength; 
				((Block) actor).setStrength(-damageToInflict); 
			}
		}
		if(actor instanceof Mob){
			System.out.println("Damage inflicted to mob: " + damageToInflict); 
			System.out.println("Current health of mob: " + ((Mob) actor).getHealth()); 
		}
		else if(actor instanceof Block){
			System.out.println("Damage inflicted to block: " + damageToInflict);
			System.out.println("Current strength of block: " + ((Block) actor).getStrength()); 
		}
		

	}
	
	public void eat(){
		System.out.println("Attempting to eat this...."); 
		Item i = inv.getItem(currentPlaceOnInv); 
		if(i != null){
			if(i instanceof Food){
				double amountOfHealthToPlayer = ((Food) i).getHeartValue();
				this.setHealth(amountOfHealthToPlayer); 
			}
		}
	}
	
	public void placeBlock(Location loc){
		System.out.println("Attempting to place blocks..."); 
		
	}
	
	public void changeCurrentItems(int targetIndex){
		System.out.println("changing currentPlaceOnInv to " + targetIndex); 
		currentPlaceOnInv = targetIndex;
	}
	
	
	public Inventory getInventory(){
		return inv; 
	}
	
	public double getDefaultMiningStrength(){
		return miningStrength; 
	}
	
	public int getCurrentPlaceOnInv(){
		return currentPlaceOnInv; 
	}
	
}
