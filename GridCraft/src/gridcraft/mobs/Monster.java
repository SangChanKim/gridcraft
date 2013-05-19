package gridcraft.mobs;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import gridcraft.blocks.Block;

public class Monster extends Mob
{
  private int detectionRadius=10;
  
  public Monster(){
    super();
  }
  
  public Monster(int radius){
    super();
    detectionRadius=radius;
  }
  
  public Monster(double health, double damage){
    super(health,damage);
  }
  
  public Monster(double health, double damage, int radius){
    super(health,damage);
    detectionRadius=radius;
  }
  
    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs, getPlayerLocation());
        makeMove(loc);
    }

    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

    public void processActors(ArrayList<Actor> actors)
    {for (Actor a : actors)
        {
            if (a instanceof Player)
                a.removeSelfFromGrid();
        }
        
    }
    
    public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }
    
    public Location getPlayerLocation()
    {
        int row=getLocation().getRow();
        int col=getLocation().getCol();
        for (int r=row-detectionRadius;r<=row+detectionRadius;r++)
        {
          for (int c=col-detectionRadius;c<=col+detectionRadius;c++)
          {
            if (getGrid().get(new Location(r,c)) instanceof Player)  
              return new Location(r,c);
          }
        }
        return null;
    }

    public Location selectMoveLocation(ArrayList<Location> locs, Location player)
    {
      int n = locs.size();
      if (n == 0)
        return getLocation();
      for (Actor a: getGrid().getNeighbors(getLocation()))
      {
        if (a instanceof Player)
        {
          setDirection(getLocation().getDirectionToward(a.getLocation()));
          return getLocation();
        }
      } 
      if (player!=null)
      {
        setDirection(getLocation().getDirectionToward(player));
        for (Location a:locs)
        {
          if (a.equals(getLocation().getAdjacentLocation(getLocation().getDirectionToward(player))))
            return a;
        }
        return locs.get((int)(Math.random()*n));
      }
      else
      {
        return locs.get((int)(Math.random()*n));
      }
    }

    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
}
