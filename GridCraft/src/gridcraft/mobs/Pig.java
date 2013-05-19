package gridcraft.mobs;
import java.util.ArrayList;
import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.food.Pork;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import gridcraft.blocks.Block;

public class Pig extends Animal
{
  public Pig(){
    super(750.0, 0.0);
  }
  
  public Pig(double health, double damage){
    super(health, damage);
  }
  
  public ArrayList<Item> loot() {
    int numOfPork = (int) (Math.random()*4);
    ArrayList<Item> loot = new ArrayList<Item>();
    
    for(int i = 0; i < numOfPork; i++){
      loot.add(new Pork());
    }
    return loot;
  }
}