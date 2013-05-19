package gridcraft.mobs;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import gridcraft.blocks.Block;

public class Animal extends Mob
{
  public Animal(){
    super();
  }
  
  public Animal(double health, double damage){
    super(health, damage);
  }
  
  private int n=5;
  
    public void act()
    {
      super.act();
      if (getGrid() == null)
        return;
      else if (n==0)
      {
        makeMove(selectMoveLocation(getMoveLocations()));
        n=5;
      }
      n--;
    }

    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }

    public Location selectMoveLocation(ArrayList<Location> locs)
    {
      int n = locs.size();
      if (n == 0)
        return getLocation();
      else
        return locs.get((int)(Math.random()*n));
    }

    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
}
