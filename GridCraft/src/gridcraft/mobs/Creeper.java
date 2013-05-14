package gridcraft.mobs;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Creeper extends Monster
{
    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }

    public ArrayList<Actor> getActors()
    {
        return getGrid().getNeighbors(getLocation());
    }

    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Player)
                explosion();
        }
    }
    
    public void explosion()
    {
      int row=getLocation().getRow();
      int col=getLocation().getCol();
      for (int r=row-1;r<=row+1;r++)
      {
        for (int c=col-1;c<=col+1;c++)
        {
          
        }
      }
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
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }

    public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
}
