package gridcraft.mobs;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import gridcraft.blocks.Block; 

public class Monster extends Mob
{
  private int detectionRadius=10;
  public Monster(int r){
    super();
    detectionRadius=r;
  }
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
        ArrayList<Actor> player = new ArrayList<Actor>();
        int row=getLocation().getRow();
        int col=getLocation().getCol();
        for (int r=row-detectionRadius;r<=row+detectionRadius;r++)
        {
          for (int c=col-detectionRadius;c<=col+detectionRadius;c++)
          {
            if (getGrid().get(new Location(r,c)) instanceof Player)
              player.add(getGrid().get(new Location(r,c)));
          }
        }
    }

    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Player)
                explosion();
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
