package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class LeadSheep extends Sheep {
    private Grid<Actor> grid;
    private Location location;
    
    
	public LeadSheep(Grid<Actor> grid) {
		super() ;
		this.grid = grid ;	
		this.location = chooseLocation() ;
		int new_age = ((Animal) grid.get(location)).getAge() ;
		this.setAge(new_age);
		
		this.setColor(Color.RED);
		this.putSelfInGrid(grid, location) ;

	}
	
	private Location chooseLocation() {
		int max_age = 0 ;
		Location max_loc = null;
		
        for (Location loc : grid.getOccupiedLocations()) {
        	if (grid.get(loc) instanceof Sheep) {
        		Sheep a = (Sheep) grid.get(loc) ;
        		int age = a.getAge() ;
        		if (age > max_age) {
        			max_age = age ; 
        			max_loc = loc ;	
        		}
        	}
        }
		return max_loc;
	}
	
	
}
