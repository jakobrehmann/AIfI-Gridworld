package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Critter;
import gridworld.framework.actor.Flower;

/**
 * An <code>Farmer</code> is a Critter that moves randomly and can remove
 * flowers from grid
 * 
 * @author Jakob, Friedrich, Marcel
 */
class Farmer extends Critter implements TerrifyinglyHuge {

	public Farmer() {

		super();
		setColor(Color.GRAY);

	}

	@Override
	public void processActors(ArrayList<Actor> actors) {

		// remove all flowers around the farmer
		for (Actor a : actors) {

			if (a instanceof Flower) {

				a.removeSelfFromGrid();

			}

		}

	}

	// return the name from the class
	@Override
	public String toString() {

		return getClass().getSimpleName();

	}

}
