package hausaufgabe;

import gridworld.framework.actor.ActorWorld;
import gridworld.framework.actor.Bug;
import gridworld.framework.actor.Rock;
import gridworld.framework.grid.BoundedGrid;

public class Main {

	public static void main(String[] args) {
		
		
		ActorWorld world = new ActorWorld();
		world.setGrid(new BoundedGrid(20, 20));
        world.add(new Sheep());
        
        world.show();
	}
	

}
