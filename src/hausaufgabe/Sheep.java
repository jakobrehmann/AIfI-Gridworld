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
		public void act()
	    {
	        if (canMove())
	            move();
	        else
	            turn();
	    }
		
	// Adapted from Class Critter
	public ArrayList<Location> getMoveLocations() {
	       return getGrid().getEmptyAdjacentLocations(getLocation());
	}

}
