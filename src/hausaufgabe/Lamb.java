package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

/**
 * A <code>Lamb</code> is an <code>Animal</code> that moves like an Animal, and 
 * and turns into a <code>Sheep</code> at age 4.
 *
 * @author Jakob, Friedrich, Marcel
 */
final class Lamb extends Animal implements Eatable {

	public Lamb() {

		super();
		this.setColor(Color.GRAY);
		
	}

	/**
	 * The movement of Lamb is specified by class Animal. When the Lamb reaches age 4, 
	 * it places a Sheep in its current location and removes itself. 
	 */
	@Override
	public void act() {
		
		super.act();

		if (super.getAge() == 4) {
			
			Location loc = getLocation();
			Grid<Actor> gr = getGrid();
			
			if (gr == null) {
				
				return;
				
			}
			
			Sheep newSheep = new Sheep(4);
			newSheep.putSelfInGrid(gr, loc);
			
		}
		
	}
	
}
