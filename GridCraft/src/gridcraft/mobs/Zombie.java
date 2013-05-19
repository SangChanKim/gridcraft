package gridcraft.mobs;

import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.food.ZombieFlesh;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;


public class Zombie extends Monster
{
  public Zombie()
  {
    super(1000.0, 100.0,10);
  }
  
  public Zombie(int radius)
  {
    super(1000.0, 100.0,radius);
  }
  
  public void processActors(ArrayList<Actor> actors)
  {
    for (Actor a : actors)
    {
      if (a instanceof Player)
      {
        attack((Mob)a);
      }
    }
  }
  
  public ArrayList<Item> loot() {
    int numOfFlesh = (int) (Math.random()*6);
    ArrayList<Item> loot = new ArrayList<Item>();
    
    for(int i = 0; i < numOfFlesh; i++){
      loot.add(new ZombieFlesh());
    }
    
    return loot; 
  }
}
