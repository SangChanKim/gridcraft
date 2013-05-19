package gridcraft;

import info.gridworld.actor.Actor;

public class Explosion extends Actor
{
  public Explosion()
  {
   super();
   setColor(null);
  }
  
  public void act()
  {
    this.removeSelfFromGrid();
  }
}