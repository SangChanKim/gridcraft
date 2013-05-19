package gridcraft;



import gridcraft.blocks.StoneBlock;
import gridcraft.blocks.WoodBlock;
import gridcraft.items.Item;
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
 
 public void step(){
  super.step(); 
  setMessage("Health: " + mainPlayer.getHealth() + " | " + "Damage: " + mainPlayer.getCurrentDamage() + " | " + "Mining Strength: " + 
  mainPlayer.getMiningStrength() + " | " + "Current Invetory: " + mainPlayer.getInventory().getItem(mainPlayer.getCurrentPlaceOnInv()).getClass().getSimpleName() + "\n" + mainPlayer.getInventory().getInventoryInfo());
 }
 
 public void addPlayer(){
  this.add(mainPlayer);
 }
 
}
