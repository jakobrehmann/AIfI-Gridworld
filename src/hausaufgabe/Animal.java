/* 
 * Problem 1.1 - Tier
 * Problem 1.7 - Herdentrieb
 * @author Jakob
 */

package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * An <code>Animal</code> is an actor with an age that can move and turn.
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
	
	public void setAge(int age) {
		this.age = age ;
	}

	// Adapted from Class Critter
	@Override
	public void act() {
		if (getGrid() == null) {
			return;
		}
		
		ArrayList<Location> moveLocs = getMoveLocations();
		ArrayList<Location> herdLocs = getHerdLocations(moveLocs);
		Location lead = findLead();
		Location loc = null;

		/*
		 * Herd Behavior : if Sheep/Lambs are nearby, only those move locations are
		 * included in moveLocs
		 */

		if (herdLocs.size() > 0) {
			loc = selectMoveLocation(herdLocs);
			
		}
		else if (herdLocs.size() == 0 && lead != null){ // if no sheeps/lambs nearby, and a LeadSheep exists
			loc = moveTowardsLeadSheep(moveLocs, lead) ;
		}
		
		else {
			loc = selectMoveLocation(moveLocs);
		}

		makeMove(loc);
		age++;
	}

	private Location moveTowardsLeadSheep(ArrayList<Location> moveLocs, Location lead) {
		double min_dist = 100000.00;
		Location best_loc = null ;
		for (Location loc : moveLocs) {
			double dr = loc.getRow() - lead.getRow() ;
			double dc = loc.getCol() - lead.getCol() ;
			double dist = Math.sqrt(dr*dr + dc*dc) ; // distance formula
				if (dist < min_dist) {
					min_dist = dist ;
					best_loc = loc ;							
				}		
		}
		return best_loc;
	}

	private ArrayList<Location> getMoveLocations() {
		return getGrid().getEmptyAdjacentLocations(getLocation());
	}

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
	
    private Location findLead() {
        Grid<Actor> gr = getGrid();   
        ArrayList<Location> locs ;
        if (gr == null)
            return null;
        
        locs = gr.getOccupiedLocations(); 
        
        for (Location loc : locs) {
        	if (gr.get(loc) instanceof LeadSheep)
        		return loc ;
        }
		return null;
    }
	

	private Location selectMoveLocation(ArrayList<Location> locs) {
		int n = locs.size();
		if (n == 0) {
			return getLocation();
		}	
		int r = (int) (Math.random() * n);
		return locs.get(r);
	}

	private void makeMove(Location loc) {
		if (loc == null) {
			removeSelfFromGrid();
		}	
		else {
			moveTo(loc);
		}	
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [age = " + age + " years]";
	}
}
