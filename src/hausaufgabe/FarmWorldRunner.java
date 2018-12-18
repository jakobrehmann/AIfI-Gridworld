package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.ActorWorld;
import gridworld.framework.actor.Bug;
import gridworld.framework.actor.Flower;
import gridworld.framework.actor.Rock;
import gridworld.framework.grid.BoundedGrid;

public class FarmWorldRunner {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(20, 20));
	    world.add(new Animal(12));
	    world.add(new Animal(5));
	    world.add(new Animal());
	    
	    
	    
	    world.add(new Flower());
	    world.add(new Flower());
	    world.add(new Flower());
	    world.add(new Flower());
	    world.add(new Flower());
	    world.add(new Flower());
	    
	    
	    world.add(new Farmer());
	    world.add(new Farmer());
	    
	    

	    world.show();
	}
}

