/* 
 * Problem 1.1 - Tier
 * Problem 1.7 - Herdentrieb
 * @author Jakob
 */

package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;


/**
 * An <code>Animal</code> is an actor with an age that can move and turn.
 */

class Animal extends Actor{
	int age ;
	
	public Animal(int age) {
		this.age = age ;
	}
	
	public Animal() {
		this(0) ;
	}
	
	public int getAge() {
		return age ;
	}
	
	
	
	// Adapted from Class Critter
	@Override
	public void act() {
		if (getGrid() == null)
			 return;
	    ArrayList<Location> moveLocs = getMoveLocations();
	    
	    ArrayList<Location> herdLocs = getHerdLocations(moveLocs) ; // Herd Behavior : if Sheep/Lambs are nearby, only those move locations are included in moveLocs
	   
	    if (herdLocs.size() > 0) {
	    	moveLocs = herdLocs ;
	    }
	    
	    Location loc = selectMoveLocation(moveLocs);
	    makeMove(loc);
	    age++;
	}

	public ArrayList<Location> getMoveLocations(){
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }
	
	
    public ArrayList<Location> getHerdLocations(ArrayList<Location> locs) {
        ArrayList<Location> herdLocs = new ArrayList<Location>();
        Grid<Actor> gr = getGrid() ;
        
        for (Location loc : locs) {
        	ArrayList<Location> neighborLocs = gr.getValidAdjacentLocations(loc) ;
        	neighborLocs.remove(getLocation()) ; // removes the Sheep who we are trying to move
        	for (Location neighborLoc : neighborLocs){
        		 if ((gr.get(neighborLoc) instanceof Sheep) || (gr.get(neighborLoc) instanceof Lamb)) {
        			 herdLocs.add(loc);
        			 break ;
        		 } 
             }
        }
        return herdLocs ;       
    }

    public Location selectMoveLocation(ArrayList<Location> locs){
        int n = locs.size();
        if (n == 0)
            return getLocation();      
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }

    
    public void makeMove(Location loc){
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
    
    @Override
    public String toString()
    {
        return getClass().getName() + "[age=" + age +" years]";
    }
}
