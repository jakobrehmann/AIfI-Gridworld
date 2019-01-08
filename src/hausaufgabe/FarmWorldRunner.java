package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.ActorWorld;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.BoundedGrid;
import gridworld.framework.grid.Location;

public class FarmWorldRunner implements FarmWorldRunnerInterface {

	private ActorWorld world;

	public static void main(String[] args) {
		FarmWorldRunner runner = new FarmWorldRunner();
		runner.createNewWorldWithGridSize(20, 20);
		runner.addLambIfFieldEmpty(10, 10);

	}

	@Override
	public void createNewWorldWithGridSize(int x, int y) {
		// TODO Auto-generated method stub
		world = new ActorWorld(new BoundedGrid<Actor>(x, y));

	}

	@Override
	public void addAnimalIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new Animal());
		}
	}

	@Override
	public void addSheepIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new Sheep());
		}

	}

	@Override
	public void addLambIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new Lamb());
		}

	}

	@Override
	public void addFarmerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new Farmer());
		}

	}

	@Override
	public void addCreatorFarmerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new CreatorFarmer());
		}

	}

	@Override
	public void addWoolStorageIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, WoolStorage.getInstance());
		}

	}

	@Override
	public void addSheepShearerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {

			ArrayList<Location> list = world.getGrid().getOccupiedLocations();

			for (Location location : list) {

				if (world.getGrid().get(location) instanceof WoolStorage) {

					WoolStorage storage = (WoolStorage) world.getGrid().get(location);
					world.add(loc, new SheepShearer(storage));
					break;
				}
			}
		}
	}

	// Marcel Heine actor
	@Override
	public void addGroupSpecificActorIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

//		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
//			world.add(loc, new Bird()); // Marcel Heine actor
//		world.add(loc, new Werewolf()); // Jakob Rehmann actor
//		world.add(loc, new Tractor()); // Friedrich Voelkers actor	 

//		}
		
		

	}

	@Override
	public void addFlowerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
		Location loc = new Location(x, y);

		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new Flower());
		}

	}

	@Override
	public String getToStringOfActorInField(int x, int y) {
		// TODO Auto-generated method stub
		
		Location loc = new Location(x, y);	
		
		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) != null) {
			
			 Actor actor = world.getGrid().get(loc);
			 return actor.toString();
		}
		return null;
	}

	@Override
	public void runNSteps(int n) {
		// TODO Auto-generated method stub

	}
}
