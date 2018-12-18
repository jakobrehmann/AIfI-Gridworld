/* 
 * Problem 1.2 - Schaf
 * @author Marcel
 */


package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.grid.Location;

class Sheep extends Animal {

	public Sheep(int age) {
		super(age);
		// TODO Auto-generated constructor stub
	}

	public Sheep() {
		// TODO Auto-generated constructor stub
	}
	
	// Adapted from Class Animal
		@Override
		public void act() {
		super.act() ;
		
		
	        
	    }
		
	// Adapted from Class Critter
	public ArrayList<Location> getLocationsForNewSheep() {
	       return getGrid().getEmptyAdjacentLocations(getLocation());
	}
	
	void setNewSheep() {
		Location newSheepPosition = getLocationsForNewSheep().get(0);
		}

}
