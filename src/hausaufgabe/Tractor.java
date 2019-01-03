package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Tractor extends Farmer {

	public Tractor() {

		this.setColor(Color.RED);

	}

	public void act() {

		if (canMove()) {

			if (Math.random() < 0.6) {

				move();

			} else if (Math.random() < 0.5) {

				turn_right();

			} else {

				turn_left();

			}

		} else {

			turn_right();

		}

	}

	public void turn_right() {

		setDirection(getDirection() + Location.HALF_RIGHT);

	}

	public void turn_left() {

		setDirection(getDirection() + Location.HALF_LEFT);

	}

	public void move() {

		Grid<Actor> gr = getGrid();

		if (gr == null) {

			return;

		}

		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());

		if (gr.isValid(next)) {

			moveTo(next);

		} else {

			removeSelfFromGrid();

		}

		// Flower flower = new Flower(getColor());
		// flower.putSelfInGrid(gr, loc);

	}

	public boolean canMove() {

		Grid<Actor> gr = getGrid();

		if (gr == null) {

			return false;

		}

		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());

		if (!gr.isValid(next)) {

			return false;

		}

		Actor neighbor = gr.get(next);
		return (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Excrement);
		// ok to move into empty location or onto flower
		// not ok to move onto any other actor
	}

}
