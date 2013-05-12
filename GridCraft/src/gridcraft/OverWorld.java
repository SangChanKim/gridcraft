package gridcraft;



import gridcraft.blocks.WoodBlock;
import gridcraft.items.Inventory;
import gridcraft.items.tools.Sword;
import gridcraft.mobs.Player;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;


public class OverWorld extends ActorWorld {
	private Player mainPlayer; 
	private Grid gr;
	
	public OverWorld(Player mainPlayer){
		this.mainPlayer = mainPlayer; 
		gr = new UnboundedGrid(); 
		this.setGrid(gr);
	}
	
	public boolean keyPressed(String description, Location loc){
		mainPlayer.act(description);
		return false;
	}
	
	public void addPlayer(){
		this.add(mainPlayer);
	}
	
	public static void main(String[] args){
		Inventory inv = new Inventory(); 
		inv.addItem(new Sword());
		inv.addItem(new WoodBlock());
		
		Player steve = new Player(inv); 
		OverWorld w = new OverWorld(steve); 
		
		TreeGeneration t1 = new TreeGeneration(); 
		TreeGeneration t2 = new TreeGeneration(); 
		TreeGeneration t3 = new TreeGeneration(); 
		
		w.add(new Location(10, 10), t1);
		w.add(new Location(40, 40), t2); 
		w.add(new Location(0, 0), t3);
		int i = 0; 
		while(i < 10){
			w.step(); 
			i++; 
		}
		
		t1.removeSelfFromGrid();
		t2.removeSelfFromGrid();
		t3.removeSelfFromGrid(); 
		
		w.addPlayer(); 
		w.show(); 
	}
	
}
