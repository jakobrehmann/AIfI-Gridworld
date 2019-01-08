/* 
 * Problem 1.6 - Laemmer 
 * @author Jakob
 */

package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * A <code>Lamb</code> is an actor that moves randomly turns into a
 * <code>Sheep</code> at age 4.
 */

final class Lamb extends Animal implements Eatable {

	public Lamb() {

		super();
		this.setColor(Color.GRAY);
		
	}

	@Override
	public void act() {
		
		super.act();

		if (super.getAge() == 4) {
			Location loc = getLocation();
<<<<<<< HEAD
			Grid<Actor> gr = getGrid();
//			this.removeSelfFromGrid();
			if (gr == null)
				return;
=======
			Grid<Actor> grid = getGrid();
			if (grid == null)
	            return;
>>>>>>> branch 'master' of https://gitlab.tubit.tu-berlin.de/AIfI-1819/Gruppe8.git
			Sheep newSheep = new Sheep(4);
			newSheep.putSelfInGrid(grid, loc);
			
		}
		
	}
	
}
