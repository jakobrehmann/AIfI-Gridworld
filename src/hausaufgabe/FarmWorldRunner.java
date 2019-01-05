package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.ActorWorld;
import gridworld.framework.actor.Bug;
import gridworld.framework.actor.Flower;
import gridworld.framework.actor.Rock;
import gridworld.framework.grid.BoundedGrid;
import gridworld.framework.grid.Location;

public class FarmWorldRunner {

	public static void main(String[] args) {

		ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(20, 20));
		world.add(new Sheep());
		world.add(new Lamb());
		world.add(new Sheep());
//		world.add(new CreatorFarmer());
		WoolStorage storage = WoolStorage.getInstance();
		world.add(storage);

		ExcrementStorage storage2 = ExcrementStorage.getInstance();

		world.add(storage2);
		Location locBiogas = storage2.getLocation();

//		world.add(new SheepShearer(storage));
//		world.add(new SheepShearer(storage));
//		world.add(new Farmer());
		world.add(new Tractor(storage2, locBiogas));

//		System.out.println(locBiogas);

//		world.add(new Farmer());

		world.show();
	}
}
