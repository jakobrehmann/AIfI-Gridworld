package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Location;

final class Tractor extends Farmer {

	private ExcrementStorage storage;
	private int ExcCounter;
	private Location locBiogas;
	private boolean excrementStationFull;
	private boolean makeNoMove;

	public Tractor(ExcrementStorage storage, Location locBiogas) {

		super();
		this.setStorage(storage);
		this.setColor(Color.RED);
		this.locBiogas = locBiogas;
		this.excrementStationFull = false;
		this.makeNoMove = false;

	}

	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Actor> actors = getActors();
		processActors(actors);

		if (!excrementStationFull && !makeNoMove) {

			ArrayList<Location> moveLocs = getMoveLocations();
			Location loc = selectMoveLocation(moveLocs);
			makeMove(loc);

		}

	}

	@Override
	public void processActors(ArrayList<Actor> actors) {

		if (!excrementStationFull) {

			makeNoMove = false;
//			System.out.println("Ich bin wieder raus!");

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid();

				}

				else if (a instanceof Excrement) {

					if (!excrementStationFull) {

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

			makeMove(goToExcrementStorage(locBiogas));

		}

	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

	public Location goToExcrementStorage(Location locBiogas) {

		ArrayList<Actor> checkAroundYou;
		checkAroundYou = getActors();
		Location bestLocation = null;

		for (Actor check : checkAroundYou) {

			if (check instanceof ExcrementStorage) {

				System.out.println("hier");

				excrementStationFull = false;
				ExcCounter = 0;
				makeNoMove = true;

				for (int i = 0; i < 5; i++) {

					storage.putExcrement();

				}

			} else {

				System.out.println("hier2");

				ArrayList<Location> possibleLocation = getGrid().getEmptyAdjacentLocations(getLocation());
				ArrayList<Location> sortPossibleLocation = new ArrayList<Location>();

				double minDistance = 100000.00;

				for (Location bestLoc : possibleLocation) {

					int tempDirection = bestLoc.getDirectionToward(locBiogas);

					if ((tempDirection % 90) == 0) {

						sortPossibleLocation.add(bestLoc);
						System.out.println("freie Richtungen:" + bestLoc);

					}

				}

				sortPossibleLocation.add(getLocation());

				for (Location location : sortPossibleLocation) {

					double deltaRow = location.getRow() - locBiogas.getRow();
					double deltaCol = location.getCol() - locBiogas.getCol();
					double distance = Math.sqrt(deltaRow * deltaRow + deltaCol * deltaCol); // distance formula

					if (distance < minDistance) {

						minDistance = distance;
						bestLocation = location;

					}

				}

				System.out.println(sortPossibleLocation);

			}

		}

		return bestLocation;

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

	@Override
	public String toString() {

		return getClass().getSimpleName() + " [ExcrementCounter = " + ExcCounter + "]";

	}

}