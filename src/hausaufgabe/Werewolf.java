/* 
 * Problem 1.9.1 - Werewolf
 * @author Jakob
 */

package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.grid.Location;

/**
 * A <code>Werewolf</code> is a <code>Farmer</code> who moves
 * to and removes (eats) a Actors who implement the <code>Eatable</code>
 * Interface (<code>Lamb</code>) if one is on an adjacent field, move towards a
 * <code>Lamb</code> if it is nearby, or moves randomly if no Lamb is located
 * nearby.
 */

final class Werewolf extends Farmer {

	public Werewolf() {

		this.setColor(Color.MAGENTA);

	}

	/**
	 * A Werewolf acts by getting locations to move to (with the motive of either
	 * eating or getting closer to a Lamb), selecting one of them, and moving to the
	 * selected location. If the Werewolf moves onto the field containing a Lamb it
	 * automatically removes (eats) it; this makes it unnecessary to use getActors()
	 * or processActors(actors).
	 */
	@Override
	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		makeMove(loc);

	}

	/**
	 * Gets a list of possible locations for the next move. The first criteria
	 * returns a list of the locations of neighboring lambs (which the Werewolf
	 * wants to eat). If this list is empty (no Lambs), it checks if there are Lambs
	 * nearby (only one free location between Werewolf and Lamb), and returns a list
	 * of locations where the Werewolf can get closer to a Lamb. If no Lambs are
	 * nearby, a list of all empty adjacent locations is returned. Postcondition:
	 * The state of all actors is unchanged.
	 * 
	 * @return a list of possible locations for the next move
	 */
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

	/**
	 * Gets a list of locations of adjacent lambs. Postcondition: The state of all
	 * actors is unchanged.
	 * 
	 * @return a list of locations of adjacent Lambs
	 */
	public ArrayList<Location> checkForLamb(Location loc) {

		ArrayList<Location> moveLocs = new ArrayList<Location>();

		for (Location neighborLoc : getGrid().getOccupiedAdjacentLocations(loc)) {

			if (getGrid().get(neighborLoc) instanceof Eatable) {

				moveLocs.add(neighborLoc);

			}

		}

		return moveLocs;

	}

}
