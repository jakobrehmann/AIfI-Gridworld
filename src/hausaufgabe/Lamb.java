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
 * A <code>Lamb</code> is an actor that moves randomly turns into a <code>Sheep</code> at age 4.
 */

class Lamb extends Animal {
	
	public Lamb() {
		
		super();
		this.setColor(Color.GRAY);
	}
	
	public void act() {
		super.act() ;
		
		if (this.age == 4){
			Location loc = getLocation();
			Grid<Actor> gr = getGrid() ;
			this.removeSelfFromGrid();
			Sheep newSheep = new Sheep(4) ;
			newSheep.putSelfInGrid(gr, loc);
		}
	}
}
