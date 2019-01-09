package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/** 
 * @author Jakob, Friedrich, Marcel
 */
final class LeadSheep extends Sheep {
	private Grid<Actor> grid;
	private Location loc;

	public LeadSheep(Grid<Actor> grid) {

		super();

		this.grid = grid;
		this.loc = chooseLocation();

		int newAge = ((Animal) grid.get(loc)).getAge();
		this.setAge(newAge);

		this.setColor(Color.RED);
		this.putSelfInGrid(grid, loc);

	}

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
