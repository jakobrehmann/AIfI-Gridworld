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

/**
 * A <code>CreatorFarmer</code> is a <code>Farmer</code> who moves randomly
 * across the grid and creates a new lamb on an adjacent field if no sheep 
 * are present on the grid. Note: As per the problem statement, the CreatorFarmer
 * will create Lambs even if other Lambs are present.
 */

final class CreatorFarmer extends Farmer {

	public CreatorFarmer() {

		setColor(Color.YELLOW);

	}
	
	/**
	 *  A CreatorFarmer acts by moving to a random adjacent location (if none are free, he remains where he is).
	 * He also checks whether there are any Sheep on the grid, and if none exist, he creates a new Lamb in an
	 * adjacent field. 
	 */
	@Override
	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Location> moveLocs = getMoveLocations();
		Location loc = selectMoveLocation(moveLocs);
		makeMove(loc);

		// Adds new Lamb if no Sheep exist
		if (!existSheep()) {

			addLamb();

		}

	}

	/**
	 * This function checks if any Sheep are on the grid. It returns TRUE if it can find at least one Sheep and FALSE
	 * if it can not find any Sheep.  
	 */
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

	/**
	 * addLamb() adds a Lamb to an adjacent free location to the CreatorFarmer. First, it finds all free adjacent locations, and then chooses one 
	 * location randomly. If there are no adjacent free locations, no Lamb is created. 
	 */
	private void addLamb() {

		Grid<Actor> grid = getGrid();
		ArrayList<Location> sheepLocs = getMoveLocations();
		Location newLoc = selectMoveLocation(sheepLocs);
		
		if (newLoc == this.getLocation()) { // Prevents CreatorFarmer from placing new Lamb on his own location, in which he would effectively kill himself
			
			return ;
			
		}
		
		Lamb newLamb = new Lamb();
		newLamb.putSelfInGrid(grid, newLoc);

	}

}
