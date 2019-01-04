/* 
 * Problem 1.2 - Sheep
 * @author Marcel
 */

package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

class Sheep extends Animal { // Jakob - removed final, so LeadSheep can extend

	private int lastTimeSheared;
	private int lastExcrement;

	public Sheep(int age) {

		super(age);
		lastExcrement = 0;
		lastTimeSheared = 0;
		setColor(Color.GRAY);

	}

	public Sheep() {

		this(0);
		lastExcrement = 0;

	}

	// Adapted from Class Animal
	@Override
	public void act() {
		
		Location loc_act = getLocation();
		Grid<Actor> grid_act = getGrid();
		
		super.act();

		if (Math.random() < 0.2) {

			setNewLamb();

		}

		if (super.getAge() >= 10 && Math.random() < 0.1666666) {

			dies();

		}

		if (lastExcrement > 10 && Math.random() > 0.5) {

			Excrement excrement = new Excrement();
			excrement.putSelfInGrid(grid_act, loc_act);
			lastExcrement = 0;

		}
		
		// Lead Sheep Generation
		
		if (!existLead()) { //if existLead is false, this happens
			LeadSheep ls = new LeadSheep(grid_act) ;
			
		}
		
		

		lastExcrement++;
	}

	private void dies() {

		Location loc = getLocation();
		Grid<Actor> grid = getGrid();
		this.removeSelfFromGrid();

		Flower flower = new Flower(Color.GREEN);
		flower.putSelfInGrid(grid, loc);

	}

	// Adapted from Class Critter
	private ArrayList<Location> getLocationsForNewLamb() {
		Grid<Actor> grid = getGrid();
		if (grid == null)
			return null ;
		return grid.getEmptyAdjacentLocations(getLocation());
	}

	private void setNewLamb() {
		if (getLocationsForNewLamb().isEmpty()) {
			return;
		}
		Location newLambPosition = getLocationsForNewLamb().get(0);
		Lamb newLamb = new Lamb();
		newLamb.putSelfInGrid(getGrid(), newLambPosition);
	}

	boolean isShearable() {
		if (super.getAge() - lastTimeSheared > 2) {
			lastTimeSheared = 0;
			return true;
		}
		return false;
	}
	
	
    private boolean existLead() {
        Grid<Actor> gr = getGrid();
        
        ArrayList<Location> locs ;
        if (gr == null)
            return true;
        
        locs = gr.getOccupiedLocations(); 
        
        for (Location loc : locs) {
        	if (gr.get(loc) instanceof LeadSheep)
        		return true ;
        }
        return false ;
    }

	/* private void shit() {

		// Location loc = getLocation();
		// Grid<Actor> grid = getGrid();
		Excrement excrement = new Excrement();
		excrement.putSelfInGrid(grid, loc);
		lastExcrement = 0;

	} */

}
