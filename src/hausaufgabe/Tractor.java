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
	private int Temp1;
	private int Temp2;

	public Tractor(ExcrementStorage storage, Location locBiogas) {

		super();
		this.setStorage(storage);
		this.setColor(Color.RED);
		this.locBiogas = locBiogas;
		this.Temp1 = 0;
		this.Temp2 = 0;

	}

	@Override
	public void processActors(ArrayList<Actor> actors) {
		
		System.out.println(Temp1);
		
		if (Temp2 == 1) {
			
			ExcrementStorage excrementStorage = ExcrementStorage.getInstance();
			Grid<Actor> grid = getGrid();
			excrementStorage.putSelfInGrid(grid, locBiogas);
			this.storage = excrementStorage;
			ExcrementStorage.amountOfExcrement = 0;
			Temp2 = 0;
			
		}

		if (Temp1 != 1) {

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid();
					// System.out.println("Blume gesammelt");

				}

				else if (a instanceof Excrement) {

					if (Temp1 == 0) {

						storage.putExcrement();
						a.removeSelfFromGrid();
						ExcCounter++;

						if (ExcCounter == 5) {

							Temp1 = 1;

						}

					}

				}

			}

		} else {

			// muss zur anlage

			for (Actor a : actors) {

				if (a instanceof Flower) {

					a.removeSelfFromGrid();
					// System.out.println("Blume gesammelt");

				}

			}

			
			ExcCounter = 0;

			int TempLoc = getLocation().getDirectionToward(locBiogas);
			Location nextLoc = getLocation().getAdjacentLocation(TempLoc);
			moveTo(nextLoc);

			Location TempLoc2 = getLocation();

			if (TempLoc2.hashCode() == locBiogas.hashCode()) {

				Temp1 = 0;
				Temp2 = 1;

			}

		}

		// System.out.println(ExcrementStorage.amountOfExcrement);

	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

}