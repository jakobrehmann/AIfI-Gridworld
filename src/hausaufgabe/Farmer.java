/* 
 * Problem 1.3 - Tier
 * @author Jakob
 */

package hausaufgabe;

import java.util.ArrayList;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Critter;
import gridworld.framework.actor.Flower;

/**
 * An <code>Farmer</code> is a Critter that moves randomly and can remove flowers from grid
 */

class Farmer extends Critter {

	public Farmer() {
		super();
	}
	
   @Override
	public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Flower)
                a.removeSelfFromGrid();
        }
    }
	
}
