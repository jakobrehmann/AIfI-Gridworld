package hausaufgabe;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.ActorWorld;
import gridworld.framework.grid.BoundedGrid;
import gridworld.framework.grid.Location;

public class FarmWorldRunner implements FarmWorldRunnerInterface {

	private ActorWorld world;

	public static void main(String[] args) {

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
Location loc  = new Location(x, y);
		
		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new Farmer());
		}

	}

	@Override
	public void addCreatorFarmerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
Location loc  = new Location(x, y);
		
		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new CreatorFarmer());
		}

	}

	@Override
	public void addWoolStorageIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
Location loc  = new Location(x, y);
		
		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, WoolStorage.getInstance());
		}

	}

	@Override
	public void addSheepShearerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub
Location loc  = new Location(x, y);
		
		if (world.getGrid().isValid(loc) && world.getGrid().get(loc) == null) {
			world.add(loc, new SheepShearer(world.);
		}
	}

	@Override
	public void addGroupSpecificActorIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addFlowerIfFieldEmpty(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getToStringOfActorInField(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void runNSteps(int n) {
		// TODO Auto-generated method stub

	}
}
