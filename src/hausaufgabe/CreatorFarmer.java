package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;
import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * A <code>CreatorFarmer</code> is a Farmer adds a new Lamb if none are present,
 * but does not collect flowers
 */

/** 
 * @author Jakob, Friedrich, Marcel
 */
final class CreatorFarmer extends Farmer {

	public CreatorFarmer() {

		setColor(Color.YELLOW);

	}

	@Override
	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		makeMove(loc);

		// Adds new Lamb if none exist
		if (!existSheep()) {

			addLamb();

		}

	}

	// adapted from getOccupiedLocations() in BoundedGrid.java
	private boolean existSheep() {

		Grid<Actor> grid = getGrid();

		for (int r = 0; r < grid.getNumRows(); r++) {

			for (int c = 0; c < grid.getNumCols(); c++) {

				Location loc = new Location(r, c);

				if (grid.get(loc) instanceof Sheep) {

					return true;

				}

			}

		}

		return false;

	}

	private void addLamb() {

		Grid<Actor> grid = getGrid();
		ArrayList<Location> sheepLocs = getMoveLocations();
		Location newLoc = selectMoveLocation(sheepLocs);
		Lamb newLamb = new Lamb();
		newLamb.putSelfInGrid(grid, newLoc);

	}

}
