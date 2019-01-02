/* 
 * Problem 1.4 - Schoepfer
 * @author Jakob
 */

package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * A <code>CreatorFarmer</code> is a Farmer adds a new Lamb if none are present,
 * but does not collect flowers
 */

class CreatorFarmer extends Farmer {

	public CreatorFarmer() {
	}

	@Override
	public void act() {
		// Move Randomly
		if (getGrid() == null)
			return;
		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		makeMove(loc);

		// Adds new Lamb if none exist
		if (!existSheep()) {
			addLamb();
		}
	}

	// adapted from getOccupiedLocations() in BoundedGrid.java
	public boolean existSheep() {
		Grid<Actor> grid = getGrid();
		for (int r = 0; r < grid.getNumRows(); r++) {
			for (int c = 0; c < grid.getNumCols(); c++) {
				Location loc = new Location(r, c);
				if (grid.get(loc) instanceof Sheep)
					return true;
			}
		}
		return false;
	}

	protected void addLamb() {
		Grid<Actor> grid = getGrid();
		ArrayList<Location> sheepLocs = getMoveLocations();
		Location newLoc = selectMoveLocation(sheepLocs);
		Lamb newLamb = new Lamb();
		newLamb.putSelfInGrid(grid, newLoc);
	}
}
