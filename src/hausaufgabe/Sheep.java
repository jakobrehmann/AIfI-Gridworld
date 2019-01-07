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

class Sheep extends Animal implements TerrifyinglyHuge { 

	private int timeSinceShear;
	private int lastExcrement;

	public Sheep(int age) {

		super(age);
		lastExcrement = 0;
		timeSinceShear = 3;
		setColor(Color.GRAY);

	}

	public Sheep() {
		
		this(0);
	}
	
	void setTimeSinceShear(int timeSinceShear) {
		this.timeSinceShear = timeSinceShear;
	}

	// Adapted from Class Animal
	@Override
	public void act() {
		
		Location loc = getLocation();
		Grid<Actor> grid = getGrid();
		
		super.act();
		
		// Because super.act() includes a method (makeMove) which also removes the sheep, this method 
		// needs a control condition.
		if (getGrid() == null) {
			
			return;
			
		}
		
		// Generate New Lamb
		if (Math.random() < 0.2) {

			setNewLamb();
			
		}



		// Generate Excrement 
		if (lastExcrement > 10 && Math.random() > 0.5) {

			makesExcrement(loc, grid);

		}
		
		// Generate Lead Sheep, if none exists		
		if (!existLead()) {
			
			new LeadSheep(grid) ;
			
		}
		
		// Death of Sheep
		if (super.getAge() >= 10 && Math.random() < 0.1666666) {

			dies();

		}

		lastExcrement ++ ;
		timeSinceShear ++ ;
	}

	private void makesExcrement(Location loc, Grid<Actor> grid) {
		Excrement excrement = new Excrement();
		excrement.putSelfInGrid(grid, loc);
		lastExcrement = 0;
	}

	private void dies() {
		
		if (getGrid() == null) {
					
					return;
					
				}
		
		Location loc = getLocation();
		Grid<Actor> grid = getGrid();
		removeSelfFromGrid();	

		Flower flower = new Flower(Color.GREEN);
		flower.putSelfInGrid(grid, loc);
		
	}

	// Adapted from Class Critter
	private ArrayList<Location> getLocationsForNewLamb() {
		
		Grid<Actor> grid = getGrid();
		if (grid == null) {
			
			return null ;
			
		}
			
		return grid.getEmptyAdjacentLocations(getLocation());
	}

	private void setNewLamb() {
		
		if (getLocationsForNewLamb() == null || getLocationsForNewLamb().isEmpty()) {
			
			return;
			
		}
		
		Location newLambPosition = getLocationsForNewLamb().get(0);
		Lamb newLamb = new Lamb();
		newLamb.putSelfInGrid(getGrid(), newLambPosition);
		
	}

	boolean isShearable() {
		
		if (timeSinceShear > 2) {
			
			return true;
			
		}
		return false;
	}
	
	
    private boolean existLead() {
    	
        Grid<Actor> grid = getGrid();
        
        ArrayList<Location> locs ;
        if (grid == null)
            return true;
        
        locs = grid.getOccupiedLocations(); 
        
        for (Location loc : locs) {
        	if (grid.get(loc) instanceof LeadSheep)
        		return true ;
        }
        return false ;
    }
    
    @Override
    public String toString() {
		return getClass().getSimpleName() + " [age = " + getAge() + " years, Shearable?: " + isShearable() + "]";
	}

}
