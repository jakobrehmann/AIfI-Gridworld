/* 
 * Problem 1.9.1 - Werewolf
 * @author Jakob
 */

package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.grid.Location;

/**
 * A <code>Werewolf</code> is a <code>Farmer</code> moves around the that moves
 * to and removes (eats) a Actors who implement the <code>Eatable</code>
 * Interface (<code>Lamb</code>) if one is on an adjacent field, move towards a
 * <code>Lamb</code> if it is nearby, or moves randomly if no Lamb is located
 * nearby.
 */

public class Werewolf extends Farmer {

	public Werewolf() {

		this.setColor(Color.MAGENTA);

	}

	@Override
	public void act() {

		if (getGrid() == null) {

			return;

		}

//    	ArrayList<Actor> actors = getActors(); // Not neccessary.
//      processActors(actors);
		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		makeMove(loc);

	}

	@Override
	public ArrayList<Location> getMoveLocations() {

		ArrayList<Location> moveLocs = new ArrayList<Location>();
		Location currentLoc = getLocation();

		// Primary Criteria: Return adjacent locations that contain a Lamb (Eatable
		// Actor)
		moveLocs = checkForLamb(currentLoc);

		if (moveLocs.size() > 0) {

			return moveLocs;

		}

		// Secondary Criteria: Return all adjacent empty locations that have lambs
		// adjacent to them
		ArrayList<Location> emptyLocs = getGrid().getEmptyAdjacentLocations(currentLoc);

		for (Location loc : emptyLocs) {

			if (checkForLamb(loc).size() > 0) {

				moveLocs.add(loc);

			}

		}

		if (moveLocs.size() > 0) {

			return moveLocs;

		}

		// Tertiary Criteria: Return all empty adjacent locations
		return emptyLocs;

	}

	public ArrayList<Location> checkForLamb(Location loc) {

		ArrayList<Location> moveLocs = new ArrayList<Location>();

		for (Location neighborLoc : getGrid().getValidAdjacentLocations(loc)) {

			if (getGrid().get(neighborLoc) instanceof Eatable) {

				moveLocs.add(neighborLoc);

			}

		}

		return moveLocs;

	}

}
