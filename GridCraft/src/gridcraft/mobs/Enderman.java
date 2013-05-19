package gridcraft.mobs;

import gridcraft.Lootable;
import gridcraft.items.Item;
import gridcraft.items.food.ZombieFlesh;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Enderman extends Monster implements Lootable{
 
  private int count=5;
  private double prevHealth=super.getHealth();
  
 public Enderman(){
  super(2000.0, 100.0, 0); 
 }
 
 public Enderman(int radius){
  super(2000, 100.0, radius); 
 }
 
 public Enderman(double health, double damage, int radius){
  super(health, damage, radius); 
 }
 
 public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        if (count==0 || prevHealth>super.getHealth())
        {
        Location loc = selectMoveLocation(getMoveLocations(), getPlayerLocation());
        makeMove(loc);
        count=6;
        prevHealth=super.getHealth();
        }
        count--;
    }
 
 public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs=new ArrayList<Location>();
        int row=getLocation().getRow();
        int col=getLocation().getCol();
        for (int r=row-15;r<=row+15;r++)
        {
          for (int c=col-15;c<=col+15;c++)
          {
            if (getGrid().get(new Location(r,c)) == null)  
              locs.add(new Location(r,c));
          }
        }
        return locs;
    }
 
 @Override
 public ArrayList<Item> loot() {
  int numOfFlesh = (int) (Math.random()*6);
  ArrayList<Item> loot = new ArrayList<Item>();
  
  for(int i = 0; i < numOfFlesh; i++){
   loot.add(new ZombieFlesh());
  }
  
  return loot; 
 }
 
}
