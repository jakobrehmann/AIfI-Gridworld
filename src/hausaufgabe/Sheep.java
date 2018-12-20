/* 
 * Problem 1.2 - Schaf
 * @author Marcel
 */


package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;
class Sheep extends Animal {
	
	private int lastTimeSheared;

	public Sheep(int age) {
		super(age);
		// TODO Auto-generated constructor stub
		lastTimeSheared = 7;
	}

	public Sheep() {
		// TODO Auto-generated constructor stub
	}
	
	// Adapted from Class Animal
		@Override
	public void act() {
		super.act() ;
		
		if (Math.random() < 0.2) {
			setNewSheep();
		}
		
		if (age >= 10 && Math.random() < 0.1666666) {
			dies();
		}
		lastTimeSheared++;	
	        
	}

	void dies() {
		
		Location loc = getLocation();
		Grid<Actor> grid = getGrid();
		this.removeSelfFromGrid();
		
		Flower flower = new Flower();
		flower.putSelfInGrid(grid, loc);		
		
	}
		
	// Adapted from Class Critter
	public ArrayList<Location> getLocationsForNewSheep() {
	       return getGrid().getEmptyAdjacentLocations(getLocation());
	}
	
	void setNewSheep() {
		if (getLocationsForNewSheep().isEmpty()){
			return;
		}	
		Location newSheepPosition = getLocationsForNewSheep().get(0);
		Sheep newSheep = new Sheep();
		newSheep.putSelfInGrid(getGrid(), newSheepPosition);		
	}
	
	void getsSheared() {
		lastTimeSheared = 0;
	}
	
	int getLastTimeSheared() {
		return lastTimeSheared;
	}

}
