package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Tractor extends Farmer {

	private ExcrementStorage storage;
	private int ExcCounter;
	private Location locBiogas;
	private boolean excrementStationFull;
	private boolean mustCreateNewExcrementStation;

	private int steps = 0;

	public Tractor(ExcrementStorage storage, Location locBiogas) {

		super();
		this.setStorage(storage);
		this.setColor(Color.RED);
		this.locBiogas = locBiogas;
		this.excrementStationFull = false;
		this.mustCreateNewExcrementStation = false;

	}

	public void act() {

		steps++;
//		System.out.println(excrementStationFull);
//		System.out.println(mustCreateNewExcrementStation);
//		
		if (steps % 100 == 0 ) {
			
			System.out.println(steps);
			
		}

		if (getGrid() == null) {

			return;

		}

		ArrayList<Actor> actors = getActors();
		processActors(actors); // !!!!!!!!!!!!

		if (!excrementStationFull) {

			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);

		}

	}

	@Override
	public void processActors(ArrayList<Actor> actors) {

		if (mustCreateNewExcrementStation) {

			ExcrementStorage excrementStorage = ExcrementStorage.getInstance();
			Grid<Actor> grid = getGrid();

			Location tempLoc2 = getLocation();

			if (tempLoc2.hashCode() != locBiogas.hashCode()) {

				excrementStorage.putSelfInGrid(grid, locBiogas);

			} else {

				ArrayList<Location> moveLocs = getMoveLocations();
				Location loc = selectMoveLocation(moveLocs);
				makeMove(loc);
				excrementStorage.putSelfInGrid(grid, locBiogas);

			}

			this.storage = excrementStorage;
			ExcrementStorage.amountOfExcrement = 0;
			mustCreateNewExcrementStation = false;

		}

		if (!excrementStationFull) {

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid(); // !!!!!!!!!

				}

				else if (a instanceof Excrement) {

					if (!excrementStationFull) {

						storage.putExcrement();
						a.removeSelfFromGrid();
						ExcCounter++;

						if (ExcCounter == 5) {

							excrementStationFull = true;

						}

					}

				}

			}

		} else {

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid();

				}

			}

			ExcCounter = 0;

			int tempLoc = getLocation().getDirectionToward(locBiogas);

			if ((tempLoc % 90) != 0) {

				Location nextLoc = getAdjacentLocation(tempLoc);
				moveTo(nextLoc);

			} else {

				tempLoc = tempLoc + 45;
				Location nextLoc = getAdjacentLocation(tempLoc);
				moveTo(nextLoc);

			}

			Location tempLoc1 = getLocation();

			if (tempLoc1.hashCode() == locBiogas.hashCode()) {

				excrementStationFull = false;
				mustCreateNewExcrementStation = true;

			}

		}

	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

	public Location getAdjacentLocation(int direction) {

		int adjustedDirection = (direction + Location.HALF_RIGHT / 2) % Location.FULL_CIRCLE;

		if (adjustedDirection < 0) {

			adjustedDirection += Location.FULL_CIRCLE;

		}

		adjustedDirection = (adjustedDirection / Location.HALF_RIGHT) * Location.HALF_RIGHT;
		int dc = 0;
		int dr = 0;

		if (adjustedDirection == Location.EAST || adjustedDirection == Location.SOUTHEAST) {

			dc = 1;

		} else if (adjustedDirection == Location.SOUTH || adjustedDirection == Location.SOUTHWEST) {

			dr = 1;

		} else if (adjustedDirection == Location.WEST || adjustedDirection == Location.NORTHWEST) {

			dc = -1;

		} else if (adjustedDirection == Location.NORTH || adjustedDirection == Location.NORTHEAST) {

			dr = -1;

		}

		return new Location(getLocation().getRow() + dr, getLocation().getCol() + dc);

	}

}