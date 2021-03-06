package hausaufgabe;

import java.util.ArrayList;
import java.util.Iterator;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**A<Code>Bird</code> is an <code>Animal</code>tries to flee from 
 * <code>Sheep</code> and <code>Farmer</code>
 * He does not move if there is no danger for him.
 * The dangerous <code>Actor</code> is indicated by the
 *  <code>TerrifyinglyHuge</code> interface.
 *  The bird moves by moving in the opposite direction of his
 *   enemy and searches whole columns and rows
 *   to find a safe place.
 */
/**
 *  @author Marcel Heine
 */
final class Bird extends Animal {

	public Bird() {

		super();

	}

	/** The bird gets a list of neighbors which frighten him.
	 * Does not move if the list is empty and otherwise flees from them.
	*/
	@Override
	public void act() {

		if (getGrid() == null) {

			return;
		}

		ArrayList<Actor> hugeNeighbors = check90DegreeFields();

		if (hugeNeighbors.isEmpty()) {

			return;

		} else {

			fleeFromNeigbors(hugeNeighbors);

		}

	}

	/** This Method returns an ArrayList of TerrifyinglyHuge Actors
	 *  which are north, south, west or east of the bird. 
	 * 
	 * @return list of actors north,south,east and west of the bird.
	 * */
	private ArrayList<Actor> check90DegreeFields() {

		ArrayList<Actor> terrifyinglyHugeNeighbors = new ArrayList<Actor>();
		Grid<Actor> grid = getGrid();

		for (Location neighborLoc : grid.getOccupiedAdjacentLocations(getLocation())) {

			// The if condition checks that the neighbor is an instance of TerryfyinglyHuge
			// and
			// has the same row or column as the bird .
			if (grid.get(neighborLoc) instanceof TerrifyinglyHuge
					&& (grid.get(neighborLoc).getLocation().getRow() == this.getLocation().getRow()
							|| grid.get(neighborLoc).getLocation().getCol() == this.getLocation().getCol())) {

				terrifyinglyHugeNeighbors.add(grid.get(neighborLoc));

			}

		}

		return terrifyinglyHugeNeighbors;

	}

	/** The bird tries to flee in the opposite direction of the frightening actors.
	 * 
	 *	@param list of actors
	 */
	private void fleeFromNeigbors(ArrayList<Actor> hugeNeighbors) {
		setOppositeDirection(hugeNeighbors);
		fleeMove();

	}

	/** The birds direction is set in dependence of other actors.
	 * the direction will be set so, that the bird moves on a safe adjacent field if
	 * it is available. 
	 * If there is no safe adjacent field the direction is not changed.
	 * 
	 * @param list of actors
	 */
	private void setOppositeDirection(ArrayList<Actor> actors) {

		ArrayList<Location> fleeFields = getSafe90DegreeAdjacentLocations(getLocation());

		if (fleeFields.isEmpty() || fleeFields.size() == 4) { // If all adjacent locations are empty or occupied the
																// birds direction does not matter
			return;

		}

		if (fleeFields.size() == 1) { // if there is only one safe adjacent field, the birds direction is set in the
										// appropriate direction
			Location onlySafeLoc = fleeFields.get(0);
			setDirection(getLocation().getDirectionToward(onlySafeLoc));
			return;

		}

		for (Actor a : actors) {

			if (a.getLocation().getCol() == getLocation().getCol()) { // bird is in the same column as the actor

				if (getLocation().compareTo(a.getLocation()) == -1 && locationSafe(Location.NORTH, fleeFields)) { // Condition checks if the planed direction is safe and if the bird is threatened
																													// from the south
																													

					setDirection(Location.NORTH);
					return;

				} else if (locationSafe(Location.SOUTH, fleeFields)) {

					setDirection(Location.SOUTH);
					return;

				} else if (locationSafe(Location.EAST, fleeFields)) {

					setDirection(Location.EAST);
					return;

				} else if (locationSafe(Location.WEST, fleeFields)) {

					setDirection(Location.WEST);
					return;

				}

			} else { // bird is in the same row as the actor

				if (getLocation().compareTo(a.getLocation()) == -1 && locationSafe(Location.WEST, fleeFields)) { // bird is threatened from the east.

					setDirection(Location.WEST);
					return;

				} else if (locationSafe(Location.EAST, fleeFields)) {

					setDirection(Location.EAST);
					return;

				} else if (locationSafe(Location.NORTH, fleeFields)) {

					setDirection(Location.NORTH);
					return;

				} else if (locationSafe(Location.SOUTH, fleeFields)) {

					setDirection(Location.SOUTH);
					return;

				}

			}

		}

	}

	/** Returns an  arrayList of safe location directly north, south,
	 *  west or east of the bird.
	 *  The fields are only allowed to be empty or occupied by a flower.
	 * 
	 * @param the birds location
	 * @return ArrayList of safe locations for the bird.
	 */
	public ArrayList<Location> getSafe90DegreeAdjacentLocations(Location loc) {

		ArrayList<Location> locs = new ArrayList<Location>();

		for (Location neighborLoc : getGrid().getValidAdjacentLocations(loc)) {

			if (getGrid().get(neighborLoc) == null || getGrid().get(neighborLoc) instanceof Flower)

				locs.add(neighborLoc);
		}

		for (Iterator<Location> iterator = locs.iterator(); iterator.hasNext();) {

			Location locToProof = iterator.next();

			if (locToProof.getCol() != getLocation().getCol() && locToProof.getRow() != getLocation().getRow()) { // Condition
																													// allows
																													// only
																													// fields
																													// north,
																													// south,
																													// east,
																													// and
																													// west

				iterator.remove();

			}

		}

		return locs;

	}

	/**Returns true if the location in the birds direction is safe. 
	 * 
	 * @param The birds direction
	 * @param An ArrayList of locations
	 * @return true if the location is safe
	 */
	private boolean locationSafe(int direction, ArrayList<Location> fields) {

		if (fields.contains(getLocation().getAdjacentLocation(direction))) {

			return true;
		}

		return false;

	}

	/** As long as the bird has not turned four times, he tries to flee on a empty
	 * field or a field occupied by a flower.
	 * If he does not find one the bird dies.
	 * If he does not find a valid field he flies to the boundary of the grid and
	 * turns right.
	 */
	private void fleeMove() {

		byte turns = 0;
		Location loc = getLocation();

		while (turns <= 4) {

			loc = getValidLocation(loc);

			if (loc != null && loc.compareTo(getLocation()) != 0) { // loc is not null or remains the same

				moveTo(loc);
				return;

			}

			loc = moveVirtuallyToBoundary();
			setDirection(getDirection() + Location.RIGHT);
			turns++;

		}

		removeSelfFromGrid();

	}

	/** Returns a location where the bird can settle or returns null if there no 
	 * safe field in the direction of the bird.
	 *  It iterates, beginning from his position, a row or a column and checks if
	 *  there is a empty field or a field occupied by a flower.
	 * @param location of the bird
	 * @return next location of the bird or null
	 */
	
	private Location getValidLocation(Location loc) {

		switch (getDirection()) {

		case Location.NORTH:

			for (int i = loc.getRow() - 1; i >= 0; i--) { 

				Location futureLoc = new Location(i, loc.getCol());

				if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {

					return futureLoc;

				}

			}

			return null;

		case Location.SOUTH:

			for (int i = loc.getRow() + 1; i <= getGrid().getNumRows() - 1; i++) {

				Location futureLoc = new Location(i, loc.getCol());

				if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {

					return futureLoc;
				}

			}

			return null;

		case Location.WEST:

			for (int i = loc.getCol() - 1; i >= 0; i--) {

				Location futureLoc = new Location(loc.getRow(), i);

				if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {

					return futureLoc;
				}

			}

			return null;

		case Location.EAST:

			for (int i = loc.getCol() + 1; i <= getGrid().getNumCols() - 1; i++) {

				Location futureLoc = new Location(loc.getRow(), i);

				if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {

					return futureLoc;

				}

			}

			return null;

		default:

			return null;

		}

	}

	/** returns a location at the boundary of the grid depending on the direction of
	 *  the bird.
	 * @return Location at the boundary of the grid.
	 */
	private Location moveVirtuallyToBoundary() {

		int row;
		int col;

		switch (getDirection()) {

		case Location.NORTH:
			row = 0;
			col = getLocation().getCol();
			break;

		case Location.SOUTH:
			row = getGrid().getNumRows() - 1;
			col = getLocation().getCol();
			break;

		case Location.WEST:
			row = getLocation().getRow();
			col = 0;
			break;

		default:
			row = getLocation().getRow();
			col = getGrid().getNumCols() - 1;
			break;

		}

		Location boundary = new Location(row, col);

		return boundary;

	}

}
