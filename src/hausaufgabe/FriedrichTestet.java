package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.ActorWorld;
import gridworld.framework.grid.BoundedGrid;
import gridworld.framework.grid.Location;

public class FriedrichTestet {

	public static void main(String[] args) {
		
//		Location Loc1 = new Location(4, 10);
//		Location Loc2 = new Location(4, 7);
//		Location Loc3 = new Location(4, 3);
		

		ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(50, 50));

		WoolStorage storage = WoolStorage.getInstance();
		world.add(storage);

		ExcrementStorage storage2 = ExcrementStorage.getInstance();
		world.add(storage2);
		Location locBiogas = storage2.getLocation();

		world.add(new Werewolf());
		world.add(new SheepShearer(storage));
		world.add(new SheepShearer(storage));
		world.add(new Farmer());
		world.add(new Tractor(storage2, locBiogas));
//		world.add(new Tractor(storage2, locBiogas));
		world.add(new CreatorFarmer());
		
		world.add(new Sheep());
		world.add(new Bird());
//		world.add(loc, occupant);
		world.show();

	}

}

