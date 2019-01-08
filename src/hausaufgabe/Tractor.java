package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;
import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Location;

public class Tractor extends Farmer {

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

//			for (Actor a : actors) {
//
//				if (a instanceof Flower) {
//
//					a.removeSelfFromGrid();
//
//				}
//
//			}

//			int tempLoc = getLocation().getDirectionToward(locBiogas);
//			System.out.println(tempLoc);
//			Location nextLoc = getAdjacentLocation(tempLoc);
//			moveTo(nextLoc);
//
//			ArrayList<Actor> checkExcrementStorage = getActors();
//
//			for (Actor a : checkExcrementStorage) {
//
//				if (a instanceof ExcrementStorage) {
//
//					System.out.println("Ich bin angekommen!");
//					excrementStationFull = false;
//
//					for (int i = 0; i < 5; i++) {
//
//						storage.putExcrement();
//
//					}
//
//					ExcCounter = 0;
//					makeNoMove = true;
//					
//				
//
//				} else {
//					
//					
//					int tempLoc = getLocation().getDirectionToward(locBiogas);
//					System.out.println(tempLoc);
//					Location nextLoc = getAdjacentLocation(tempLoc);
//					moveTo(nextLoc);
////					System.out.println("Ich hänge hier fest! #001");
//					
//					
//				}
//
//			}

		}

	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

	public void goToExcrementStorage(Location locBiogas) {

		ArrayList<Actor> checkAroundYou;
		checkAroundYou = getActors();

		for (Actor check : checkAroundYou) {

			if (check instanceof Flower) {

				check.removeSelfFromGrid();

			}

			if (check instanceof ExcrementStorage) {

				excrementStationFull = false;
				ExcCounter = 0;
				makeNoMove = true;

				for (int i = 0; i < 5; i++) {

					storage.putExcrement();
					// test
					// teste

				}

			} else {

				// go to storage

			}

		}

//		ArrayList<Actor> checkExcrementer = getActors();
//
//		for (Actor b : checkExcrementer) {
//
//			if (b instanceof checkExcrementer) {
//
//				System.out.println("Ich bin angekommen!");
//				excrementStationFull = false;
//
//				for (int i = 0; i < 5; i++) {
//
//					storage.putExcrement();
//
//				}
//
//				ExcCounter = 0;
//				makeNoMove = true;
//
//			}
//
//		}

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