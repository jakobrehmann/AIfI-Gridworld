package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/** 
 * A <code>LeadSheep</code> moves and acts exactly like a <code>Sheep</code>. 
 * There can only be one LeadSheep on the grid at a time, and it is the 
 * oldest Sheep (or one of the oldest, if multiple Sheep have the same age). 
 *
 * @author Jakob, Friedrich, Marcel
 */
final class LeadSheep extends Sheep {
	
	private Grid<Actor> grid;
	private Location loc;

	/**
	 * In class <code>Sheep</code>, a LeadSheep is generated if there is no other
	 * LeadSheep on the grid. However, the location of the new LeadSheep is not 
	 * immediately specified. Therefore, in the Constructor of LeadSheep, the correct
	 * location for insertion must first be found using the chooseLocation() method,
	 * and the LeadSheep then puts itself in the grid at the specified location.
	 * @param grid
	 */
	public LeadSheep(Grid<Actor> grid) {

		super();
		this.grid = grid;
		this.loc = chooseLocation();
		int newAge = ((Animal) grid.get(loc)).getAge();
		this.setAge(newAge);
		this.setColor(Color.RED);
		this.putSelfInGrid(grid, loc);

	}

	/**
	 * This method returns the location of the oldest sheep on the grid. When the
	 * In the Construction, the LeadSheep places itself at the location of the oldest
	 * Sheep, thereby replacing it. 
	 * @return
	 */
	private Location chooseLocation() {
		
		int maxAge = 0;
		Location locOldestSheep = null;

		for (Location loc : grid.getOccupiedLocations()) {

			if (grid.get(loc) instanceof Sheep) {

				Sheep a = (Sheep) grid.get(loc);
				int age = a.getAge();

				if (age > maxAge) {

					maxAge = age;
					locOldestSheep = loc;
				}
				
			}
			
		}
		
		return locOldestSheep;
		
	}
	
}
