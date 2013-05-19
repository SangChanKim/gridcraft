package gridcraft.mobs;
import java.util.ArrayList;

import gridcraft.Explosion; 
import info.gridworld.actor.Actor;
import info.gridworld.grid.*;
import gridcraft.blocks.*; 

public class Creeper extends Monster
{
  private boolean remove;
  
  public Creeper()
  {
    super(1000.0,0.0,10);
  }
  
  public Creeper(int radius)
  {
    super(1000.0,0.0,radius);
  }
  
  public void processActors(ArrayList<Actor> actors)
  {
    for (Actor a : actors)
    {
      if (a instanceof Player)
      {
        explosion();
        remove=true;
      }
    }
  }
  
  public void explosion()
  {
    int row=getLocation().getRow();
    int col=getLocation().getCol();
    int n=0;
    for (int r=row-1;r<=row+1;r++)
    {
      for (int c=col-1;c<=col+1;c++)
      {
        n++;
        if (n!=5)
        {
          getGrid().remove(new Location(r,c));
          new Explosion().putSelfInGrid(getGrid(),new Location(r,c));
        }
      }
    }
  }
  
  public void makeMove(Location loc)
    {
        if (loc == null || remove)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
}
