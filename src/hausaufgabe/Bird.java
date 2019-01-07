package hausaufgabe;

import java.util.ArrayList;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

final class Bird extends Animal{
	
	public Bird () {
		super();		
	}
	
	@Override
	public void act() {
		
		if (getGrid() == null) {
			return;
		}
		
		ArrayList<Actor> hugeNeighbors = check90DegreeFields();
		
		if (hugeNeighbors.isEmpty()) {
			return;
		} else {
			fleeFromNeigbors(hugeNeighbors);	
		}
		
		// if ()
	}	
	
	private void fleeFromNeigbors(ArrayList<Actor> list) {
		setOppositeDirection(list.get(0)); // not really random if there are more than one TerrifyinglyHuge Actor
		fleeMove();
	}
	
	private void fleeMove() {
		byte turnArounds = 0;
		
		while (turnArounds <= 4) {
			Location loc = getValidLocation();
			
			if (loc != null) {
				moveTo(loc);
				return;
			}
			
			setDirection(Location.RIGHT);
			turnArounds++;
		}		
		removeSelfFromGrid();
	}
	
	private Location getValidLocation() {
		int direction = getDirection();
        
        if (direction == Location.NORTH){
        	   
        	for (int i = getLocation().getRow(); i >= 0; i--) {
        		Location loc = new Location(i, getLocation().getCol());
        		
        		if (getGrid().get(loc) == null || getGrid().get(loc) instanceof Flower) {
        			return loc;
        		}
        	}
        }
        
        if (direction == Location.SOUTH){
     	   
        	for (int i = getLocation().getRow(); i <= getGrid().getNumRows() -1; i++) {
        		Location loc = new Location(i, getLocation().getCol());
        		
        		if (getGrid().get(loc) == null || getGrid().get(loc) instanceof Flower) {
        			return loc;
        		}
        	}
        }
        
        if (direction == Location.EAST){
     	   
        	for (int i = getLocation().getCol(); i >= 0; i--) {
        		Location loc = new Location(getLocation().getRow(), i);
        		
        		if (getGrid().get(loc) == null || getGrid().get(loc) instanceof Flower) {
        			return loc;
        		}
        	}
        }
        
        if (direction == Location.WEST){
     	   
        	for (int i = getLocation().getCol(); i <=  getGrid().getNumCols()-1; i++) {
        		Location loc = new Location(getLocation().getRow(), i);
        		
        		if (getGrid().get(loc) == null || getGrid().get(loc) instanceof Flower) {
        			return loc;
        		}
        	}
        }       
        return null;
    }

	private void setOppositeDirection(Actor actor) {
		if (getLocation().getRow() != actor.getLocation().getRow()) {
			
			if (getLocation().compareTo(actor.getLocation()) == -1) {
				setDirection(Location.NORTH);
			} else {
				setDirection(Location.SOUTH);
			}
		} else {
			
			if (getLocation().compareTo(actor.getLocation()) == -1) {
				setDirection(Location.EAST);
			} else {
				setDirection(Location.WEST);
			}
		}
	}

	private ArrayList<Actor> check90DegreeFields() {
		ArrayList<Actor> terrifyinglyHugeNeighbors = new ArrayList<Actor>();
		Grid<Actor> grid = getGrid();
		
		for (Location neighborLoc : grid.getOccupiedAdjacentLocations(getLocation())) {
			if (grid.get(neighborLoc) instanceof TerrifyinglyHuge) {
				terrifyinglyHugeNeighbors.add(grid.get(neighborLoc));
			}
		}
		
		ArrayList<Actor> terrifyinglyHugeNeighbors90Degree = new ArrayList<Actor>();
		
		for (Actor neighbor : terrifyinglyHugeNeighbors) {
			
			if (((Location)neighbor.getLocation()).getRow() == ((Location)this.getLocation()).getRow() || ((Location)neighbor.getLocation()).getCol() == ((Location)this.getLocation()).getCol()) {
				terrifyinglyHugeNeighbors90Degree.add(neighbor);
			}
		}
		
		return terrifyinglyHugeNeighbors90Degree;
	}
	        
}
