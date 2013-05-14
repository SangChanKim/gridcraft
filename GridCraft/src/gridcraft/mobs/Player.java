package gridcraft.mobs;

import gridcraft.blocks.Block;
import gridcraft.blocks.StoneBlock;
import gridcraft.blocks.WoodBlock;
import gridcraft.items.Inventory;
import gridcraft.items.Item;
import gridcraft.items.food.Apple;
import gridcraft.items.food.Food;
import gridcraft.items.food.Pork;
import gridcraft.items.food.ZombieFlesh;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;


public class Player extends Mob{
	//if item reference variable is null, it should use default values
	//damage value 
	private double miningStrength = 0;
	private double defaultMiningStrength = 30.0;
	
	private int currentPlaceOnInv = 0; 
	private Inventory inv = new Inventory(); 
	
	//create a default player
	//default player should have 
	//damage (no item)- 20.0
	//mining (no item)- 10.0
	//health - 1000.0
	
	public Player(Inventory i){
		super(10.0, 0);
		inv = i; 
		changeCurrentItems(0);
		//changeToInv(); 
	}
	
	public Player(double health, double damage, double ms, Inventory i){
		super(health, damage);
		miningStrength = ms; 
		inv = i; 
	}
	
	public double getMiningStrength(){
		return miningStrength;
	}
	
	public void act(){
		inv.getInventoryInfo();
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
			attack();
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
		
		//B is eating
		if(description.equals("B")){
			eat(); 
		}
		
		
	}
	
	public void attack(){
		System.out.println("Attempting to attack"); 
		int dirFacing = this.getDirection();
		Location frontLoc = this.getLocation().getAdjacentLocation(dirFacing);
		Actor actor = this.getGrid().get(frontLoc);
		
		double damageToInflict = 0; 
		double currentHealth = 0; 
		
		//deal damage according to the instance of actor
		if(actor != null){
			if(actor instanceof Mob){
				damageToInflict = this.getCurrentDamage(); 
				((Mob) actor).setHealth(-damageToInflict); 
				currentHealth = ((Mob) actor).getHealth();
			}
			else if(actor instanceof Block){
				damageToInflict = this.miningStrength;
				((Block) actor).setStrength(-damageToInflict);
				currentHealth = ((Block) actor).getStrength();
			}
		}
		
		//print information out on console
		if(actor instanceof Mob){
			System.out.println("Damage inflicted to mob: " + damageToInflict); 
			System.out.println("Current health of mob: " + ((Mob) actor).getHealth()); 
		}
		else if(actor instanceof Block){
			System.out.println("Damage inflicted to block: " + damageToInflict);
			System.out.println("Current strength of block: " + ((Block) actor).getStrength()); 
		}
		
		//loot
		if(currentHealth <= 0){
			ArrayList<Item> loot = new ArrayList<Item>(); 
			if(actor instanceof WoodBlock){
				loot = ((WoodBlock) actor).loot(); 
			}
			else if(actor instanceof StoneBlock){
				loot = ((StoneBlock) actor).loot(); 
			}
			else if(actor instanceof Zombie){
				loot = ((Zombie) actor).loot(); 
			}
			else if(actor instanceof Pig){
				loot = ((Pig) actor).loot(); 
			}
			
			for(int x = 0; x < loot.size(); x++){
				inv.addItem(loot.get(x)); 
			}
		}
		

	}
	
	public void eat(){
		System.out.println("Attempting to eat this...."); 
		Item i = inv.getItem(currentPlaceOnInv); 
		if(i != null && i instanceof Food){
			
			double amountOfHealthToPlayer = 0; 
			if(i instanceof Apple){
				amountOfHealthToPlayer = ((Apple) i).getHeartValue();
				
			}
			else if(i instanceof Pork){
				amountOfHealthToPlayer = ((Pork) i).getHeartValue();
			}
			else if(i instanceof ZombieFlesh){
				amountOfHealthToPlayer = ((ZombieFlesh) i).getHeartValue();  
			}
			
		
			setHealth(amountOfHealthToPlayer); 
			inv.deleteItem(currentPlaceOnInv);
		}
	}
	
	public void placeBlock(Location loc){
		System.out.println("Attempting to place blocks..."); 
		Item i = inv.getItem(currentPlaceOnInv);
		if(i != null){
			if(i instanceof Block){
				Location blockLoc = getLocation().getAdjacentLocation(getDirection());
				if(getGrid().get(blockLoc) == null && inv.getNumForSlot(currentPlaceOnInv) > 0){
					if(i instanceof StoneBlock){
						StoneBlock sb = new StoneBlock();
						sb.putSelfInGrid(getGrid(), blockLoc);
					}
					else if(i instanceof WoodBlock){
						WoodBlock wb = new WoodBlock();
						wb.putSelfInGrid(getGrid(), blockLoc);
					}
					else{
						Block b = new Block(100);
						b.putSelfInGrid(getGrid(), blockLoc); 
					}
					inv.setInvNum(currentPlaceOnInv, inv.getNumForSlot(currentPlaceOnInv)-1); 
				}
			}
		}
	}
	
	public void changeCurrentItems(int targetIndex){
		System.out.println("changing currentPlaceOnInv to " + targetIndex); 
		currentPlaceOnInv = targetIndex;
		
		double defaultA;
		double defaultM; 
		if(inv.getItem(currentPlaceOnInv) != null){
			defaultA = inv.getItem(currentPlaceOnInv).getDefaultAttackStrength();
			defaultM = inv.getItem(currentPlaceOnInv).getDefaultMiningStrength();
		}
		else{
			defaultA = this.defaultDamage; 
			defaultM = this.defaultMiningStrength;
		}
		this.miningStrength = defaultM; 
		this.damage = defaultA; 
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
