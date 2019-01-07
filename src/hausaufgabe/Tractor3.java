package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Tractor3 extends Farmer {

	private ExcrementStorage storage;
	private int ExcCounter;
	private Location locBiogas;
//	private Location nextMove;
	private int notFull; // notFull = 0 (nicht voll); notFull = 1 (voll)
	private int createNewExcrementStorage;

	public Tractor3(ExcrementStorage storage, Location locBiogas) {

		super();
		this.setStorage(storage);
		this.setColor(Color.RED);
		this.locBiogas = locBiogas;
		this.notFull = 0;

	}

	@Override
	public void act() {
		
//		System.out.println(createNewExcrementStorage);
//		System.out.println(notFull);
//		System.out.println(Error1);
//		System.out.println();
		

		if (getGrid() == null) {

			return;

		}

		ArrayList<Actor> actors = getActors();

		if (createNewExcrementStorage == 1) {
			
			System.out.println("Error1");

			ExcrementStorage excrementStorage = ExcrementStorage.getInstance();
			Grid<Actor> grid = getGrid();
			excrementStorage.putSelfInGrid(grid, locBiogas);
			this.storage = excrementStorage;
			ExcrementStorage.amountOfExcrement = 0;
			createNewExcrementStorage = 0;
		

		}

		if (notFull == 0) {
			
			System.out.println("Error4");

			for (Actor a : actors) {

				removeAllFlowers(a);

				if (notFull == 0) {

					storage.putExcrement();
					a.removeSelfFromGrid();
					ExcCounter++;

					if (ExcCounter == 5) {

						notFull = 1;

					}

				}

			}

		} else {
			
			System.out.println("Error5");

			for (Actor a : actors) {

				removeAllFlowers(a);

			}

		}

		// processActors(actors);

		if (notFull == 0) {
			
			System.out.println("Error3");

			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);

		} else {
			
			System.out.println("Error2");

			int tempLoc = getLocation().getDirectionToward(locBiogas);
			Location nextLoc = getLocation().getAdjacentLocation(tempLoc);
			moveTo(nextLoc);

			Location tempLoc1 = getLocation();

			if (tempLoc1.hashCode() == locBiogas.hashCode()) {

				notFull = 0;
				createNewExcrementStorage = 1;

			}

		}

	}

	private void removeAllFlowers(Actor a) {

		if (a instanceof Flower) {

			a.removeSelfFromGrid();

		}

	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

}