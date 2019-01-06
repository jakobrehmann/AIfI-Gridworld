package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.ActorWorld;

import gridworld.framework.grid.BoundedGrid;
import gridworld.framework.grid.Location;

public class FriedrichTestet {

	public static void main(String[] args) {

		ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(40, 40));

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
		world.add(new CreatorFarmer());

		world.show();

		/* Location eins = storage2.getLocation();
		Location zwei = storage2.getLocation();

		if (eins == zwei) {
		

			System.out.println("Jippie!");
			
			
			
			



		} */

	}
}
