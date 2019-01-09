package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Location;

final class Tractor2 extends Farmer {

	private ExcrementStorage storage;
	private int excrementCounter;
	private Location locBiogas;
	private boolean excrementStationFull;

	public Tractor2(ExcrementStorage storage, Location locBiogas) {

		setStorage(storage);
		setColor(Color.RED);
		this.locBiogas = locBiogas;
		excrementCounter = 0;
		excrementStationFull = false;

	}

	@Override
	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Actor> actor = getActors();
		processActors(actor);

		if (!excrementStationFull) {

			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);

		}

	}

	@Override
	public void processActors(ArrayList<Actor> actors) {

		boolean isNearExcremenetStation = false;

		for (Actor a : actors) {

			if (a instanceof Flower) {

				a.removeSelfFromGrid();

			}

			if (a instanceof ExcrementStorage) {

				isNearExcremenetStation = true;

			}

		}

		if (!excrementStationFull) {

			for (Actor a : actors) {

				if (a instanceof Excrement) {

					if (!excrementStationFull) {

						a.removeSelfFromGrid();
						excrementCounter++;

						if (excrementCounter == 5) {

							excrementStationFull = true;

						}

					}

				}

			}

		} else {

			if (isNearExcremenetStation) {

				for (int i = 0; i < 5; i++) {

					storage.putExcrement();

				}

				excrementCounter = 0;
				excrementStationFull = false;

			} else {

				goToExcrementStorage();

			}

		}

	}

	private void goToExcrementStorage() {

		ArrayList<Location> possibleNextLocationsSort = new ArrayList<Location>();
		ArrayList<Location> possibleNextLocations = new ArrayList<Location>();

		double minDistance = 100000.00;
		Location nextLocation = null;
		possibleNextLocations = null;

		possibleNextLocations = getGrid().getEmptyAdjacentLocations(getLocation());

		for (Location bestLoc : possibleNextLocations) {

			int tempDirection = bestLoc.getDirectionToward(getLocation());

			if ((tempDirection % 90) == 0) {

				possibleNextLocationsSort.add(bestLoc);

			}

		}

		possibleNextLocationsSort.add(getLocation());

		for (Location location : possibleNextLocationsSort) {

			double deltaRow = location.getRow() - locBiogas.getRow();
			double deltaCol = location.getCol() - locBiogas.getCol();
			double distance = Math.sqrt(deltaRow * deltaRow + deltaCol * deltaCol);

			if (distance < minDistance) {

				minDistance = distance;
				nextLocation = location;

			}

		}

		possibleNextLocationsSort.clear();
		moveTo(nextLocation);
	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

	@Override
	public String toString() {

		return getClass().getSimpleName() + " [ExcrementCounter = " + excrementCounter + "]";

	}

}