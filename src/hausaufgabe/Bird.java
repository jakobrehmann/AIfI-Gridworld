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
		byte turns = 0;
		Location loc = getLocation();
		
		while (turns <= 4) {
			loc = getValidLocation(loc);
			if (loc != null && loc.compareTo(getLocation()) != 0) {
				moveTo(loc);
				return;
			}
			
			loc =  moveVirtuallyToBoundary();
			setDirection(getDirection()+Location.RIGHT);
			turns++;
		}		
		removeSelfFromGrid();
	}
	
	private Location moveVirtuallyToBoundary() {
		int row;
		int col;
		
		switch(getDirection()) {
			
			case Location.NORTH:	row = 0;
									col = getLocation().getCol();
								
			case Location.SOUTH: 	row = getGrid().getNumRows() - 1;
									col = getLocation().getCol();
								
			case Location.WEST:		row = getLocation().getRow();
									col = 0;
						
			default: 				row = getLocation().getRow();
									col = getGrid().getNumCols() - 1;
		}
		Location boundary = new Location(row, col);
		return boundary;
				
	}

	private Location getValidLocation(Location loc) {
		int direction = getDirection();
        
        if (direction == Location.NORTH){
        	   
        	for (int i = loc.getRow() - 1; i >= 0; i--) {
        		Location futureLoc = new Location(i, loc.getCol());
        		
        		if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {
        			return futureLoc;
        		}
        	}
        }
        
        if (direction == Location.SOUTH){
     	   
        	for (int i = loc.getRow() + 1; i <= getGrid().getNumRows() -1; i++) {
        		Location futureLoc = new Location(i, loc.getCol());
        		
        		if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {
        			return futureLoc;
        		}
        	}
        }
        
        if (direction == Location.WEST){
     	   
        	for (int i = loc.getCol() - 1; i >= 0; i--) {
        		Location futureLoc = new Location(loc.getRow(), i);
        		
        		if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {
        			return futureLoc;
        		}
        	}
        }
        
        if (direction == Location.EAST) {
     	   
        	for (int i = loc.getCol() + 1; i <=  getGrid().getNumCols()-1; i++) {
        		Location futureLoc = new Location(loc.getRow(), i);
        		
        		if (getGrid().get(futureLoc) == null || getGrid().get(futureLoc) instanceof Flower) {
        			return futureLoc;
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
				setDirection(Location.WEST);
			} else {
				setDirection(Location.EAST);
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
