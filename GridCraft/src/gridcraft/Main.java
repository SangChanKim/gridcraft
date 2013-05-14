package gridcraft;

import info.gridworld.grid.Location;

import gridcraft.blocks.*; 
import gridcraft.items.*;
import gridcraft.mobs.*;
import gridcraft.items.food.*;
import gridcraft.items.resources.*;
import gridcraft.items.tools.*; 

public class Main {
	public static void main(String[] args){
		Inventory inv = new Inventory(); 
		inv.addItem(new Sword());
		inv.addItem(new WoodBlock());
		inv.addItem(new StoneBlock());
		inv.addItem(new Pickaxe()); 
		
		TreeGeneration[] tg = new TreeGeneration[3]; 
		Location[] treeLoc = {new Location(10,10), new Location(10, 30), new Location(50, 25)}; 
		StoneGeneration[] sg = new StoneGeneration[3]; 
		Location[] stoneLoc = {new Location(0,0), new Location(10, 60), new Location(-10, -10)}; 
		
		Player steve = new Player(inv); 
		OverWorld w = new OverWorld(steve); 
		
		
		for(int i = 0; i < tg.length; i++){
			tg[i] = new TreeGeneration();
			w.add(treeLoc[i], tg[i]);
		}
		for(int i = 0; i < sg.length; i++){
			sg[i] = new StoneGeneration();
			w.add(stoneLoc[i], sg[i]);
		}
		
		int counter = 0; 
		while(counter < 10){
			w.step(); 
			counter++; 
		}
		
		for(int x = 0; x < tg.length; x++){
			tg[x].removeSelfFromGrid();
		}
		for(int x = 0; x < sg.length; x++){
			sg[x].removeSelfFromGrid(); 
		}
		
		w.add(new Location(10, 10), new StoneBlock());
		w.addPlayer(); 
		w.show();
		System.out.println("completed setup");
	}
}
