package hausaufgabe;

import java.awt.Color;
import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;

/**
 * @author Jakob, Friedrich, Marcel
 */
final class SheepShearer extends Farmer {

	private WoolStorage storage;

	public SheepShearer(WoolStorage storage) {

		super();
		this.storage = storage;
		this.setColor(Color.BLUE);

	}

	@Override
	public void processActors(ArrayList<Actor> actors) {

		for (Actor a : actors) {

			// remove all flowers around the SheepShearer
			if (a instanceof Flower) {

				a.removeSelfFromGrid();

			}

			else if (a instanceof Sheep) {

				// shear the sheep when its shearable
				if (((Sheep) a).isShearable()) {

					storage.putWool();
					((Sheep) a).setTimeSinceShear(0);

				}

			}

		}

	}

}
