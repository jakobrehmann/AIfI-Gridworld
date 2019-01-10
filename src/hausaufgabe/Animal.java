package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * An <code>Animal</code> is an actor with an increasing age that can move. Animals
 * have a herd behavior, which means they like to move towards other herd animals
 * (Sheep and Lambs). If they are separated from their herd, they will try to move
 * towards the LeadSheep (oldest Sheep on the grid)
 * 
 * @author Jakob, Friedrich, Marcel
 */
class Animal extends Actor {

	private int age;

	public Animal(int age) {

		this.age = age;

	}

	public Animal() {

		this(0);

	}

	int getAge() {

		return age;

	}

	void setAge(int age) {

		this.age = age;

	}

	/**
	 * An Animal's movement is determined by several factors. First, it checks if it can move
	 * to a Location adjacent to another herd animal (Sheep or Lambs). If no other herd animals
	 * are nearby, it will check if a LeadSheep exists. If yes, it will move towards the LeadSheep;
	 * if no, it will to a random adjacent location. The Animal's age increases with each step. 
	 */
	@Override
	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Location> moveLocs = getMoveLocations();
		ArrayList<Location> herdLocs = getHerdLocations(moveLocs);
		Location lead = findLead();
		Location loc = null;

		if (herdLocs.size() > 0) { // if Sheep/Lambs are nearby

			loc = selectMoveLocation(herdLocs);

		} else if (herdLocs.size() == 0 && lead != null) { // if no Sheep/Lambs are nearby, and a LeadSheep exists

			loc = moveTowardsLeadSheep(moveLocs, lead);

		} else { // if no Sheep/Lambs are nearby, and no LeadSheep exists

			loc = selectMoveLocation(moveLocs);

		}

		makeMove(loc);
		age++;

	}

	/**
	 * This method uses the distance formula to find the move location for the Animal that
	 * will bring it closest to the LeadSheep.
	 * @param moveLocs is an ArrayList of all the empty Locations adjacent to the Animal. 
	 * @param lead is the location of the LeadSHeep
	 * @return the empty adjacent location that will bring the Animal closest to the LeadSheep
	 */
	private Location moveTowardsLeadSheep(ArrayList<Location> moveLocs, Location lead) {

		double minDistance = 100000.00;
		Location bestLocation = null;

		for (Location location : moveLocs) {

			double deltaRow = location.getRow() - lead.getRow();
			double deltaCol = location.getCol() - lead.getCol();
			double distance = Math.sqrt(deltaRow * deltaRow + deltaCol * deltaCol); // distance formula

			if (distance < minDistance) {

				minDistance = distance;
				bestLocation = location;

			}

		}

		return bestLocation;

	}

	
	private ArrayList<Location> getMoveLocations() {

		return getGrid().getEmptyAdjacentLocations(getLocation());

	}

	/**
	 * For each possible move location, this method checks whether a herd animal (Sheep and Lamb) is located
	 * adjacent to that location. It returns a list of location that will bring the Animal next to another 
	 * herd Animal. 
	 * @param locs: all the empty adjacent locations 
	 * @return an ArrayList of possible move location that will place the Animal next to a herd animal
	 */
	private ArrayList<Location> getHerdLocations(ArrayList<Location> locs) {

		ArrayList<Location> herdLocs = new ArrayList<Location>();
		Grid<Actor> gr = getGrid();

		for (Location loc : locs) {

			ArrayList<Location> neighborLocs = gr.getValidAdjacentLocations(loc);
			neighborLocs.remove(getLocation()); // removes the Sheep who we are trying to move

			for (Location neighborLoc : neighborLocs) {

				if ((gr.get(neighborLoc) instanceof Sheep) || (gr.get(neighborLoc) instanceof Lamb)) {

					herdLocs.add(loc);
					break;

				}

			}

		}

		return herdLocs;

	}

	/**
	 * This method checks the whole grid to find the location of the LeadSheep. 
	 * @return location of the LeadSheep.
	 */
	private Location findLead() {

		Grid<Actor> grid = getGrid();
		ArrayList<Location> locs;

		if (grid == null) {

			return null;

		}

		locs = grid.getOccupiedLocations();

		for (Location loc : locs) {

			if (grid.get(loc) instanceof LeadSheep) {

				return loc;

			}

		}
		
		return null;
		
	}
	
	/**
	 * From the array of possible move locations, this method selects one location randomly.
	 * If there are no possible move location, this method returns the current location (meaning
	 * the Animal will not move).
	 * @param locs: possible move location
	 * @return one location that the Animal will move to. 
	 */
	private Location selectMoveLocation(ArrayList<Location> locs) {

		int size = locs.size();

		if (size == 0) {

			return getLocation();

		}

		int random = (int) (Math.random() * size);
		return locs.get(random);

	}

	private void makeMove(Location loc) {

		if (loc == null) {

			removeSelfFromGrid();

		} else {

			moveTo(loc);

		}

	}

	@Override
	public String toString() {

		return getClass().getSimpleName() + " [age = " + age + " years]";

	}

}
