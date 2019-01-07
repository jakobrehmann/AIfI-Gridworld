package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Tractor2 extends Farmer {

	private ExcrementStorage storage;
	private int ExcCounter;
	private Location locBiogas;
	private Location nextMove;
	private int notFull;

	public Tractor2(ExcrementStorage storage, Location locBiogas) {

		super();
		this.setStorage(storage);
		this.setColor(Color.RED);
		this.locBiogas = locBiogas;
		this.notFull = 0;

	}

	public void act() {

		if (getGrid() == null) {

			return;

		}

		ArrayList<Actor> actors = getActors();

		if (notFull == 0) {

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid();

				}

				else if (a instanceof Excrement) {

					if (notFull == 0) {

						storage.putExcrement();
						a.removeSelfFromGrid();
						ExcCounter++;

						if (ExcCounter == 5) {

							notFull = 1;

						}

					}

				}

			}

		} else if (notFull == 1) {

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid();

				}

			}

		}

		// ArrayList<Actor> actors = getActors();
		// processActors(actors);

		ArrayList<Location> moveLocs = getMoveLocations();

		if (notFull == 0) {

			nextMove = selectMoveLocation(moveLocs);
			makeMove(nextMove);

		}	else if (notFull == 1) {

			goToExcrementStation();
			
		}

	}

	private void goToExcrementStation() {
		
		int tempLoc = getLocation().getDirectionToward(locBiogas);
		Location nextLoc = getLocation().getAdjacentLocation(tempLoc);
		moveTo(nextLoc);
		
	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

}
