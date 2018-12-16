/* 
 * Problem 1.1 - Tier
 * @author Jakob
 */

package hausaufgabe;

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
	
	
	// Adapted from Class Bug
	@Override
	public void act()
    {
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
    }

    /**
     * Tests whether this bug can move forward into a location that is empty
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null); 
        // ok to move into empty location
        // not ok to move onto any other actor
    }
    
    @Override
    public String toString()
    {
        return getClass().getName() + "[age=" + age +" years]";
    }
}
