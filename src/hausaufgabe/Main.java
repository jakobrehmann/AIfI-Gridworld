package hausaufgabe;

import gridworld.framework.actor.ActorWorld;
import gridworld.framework.actor.Bug;
import gridworld.framework.actor.Rock;

public class Main {

	public static void main(String[] args) {
		
		
		ActorWorld world = new ActorWorld();
        world.add(new Sheep());
        
        world.show();
	}

}
