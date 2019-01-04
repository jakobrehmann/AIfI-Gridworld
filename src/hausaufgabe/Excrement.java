package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;
import gridworld.framework.grid.Grid;
import gridworld.framework.grid.Location;

public class Excrement extends Animal {

	private int time;

	public Excrement() {

		this.setColor(Color.ORANGE);
		time = 0;

	}

	@Override
	public void act() {

		Grid<Actor> gr = getGrid();
		Location loc = getLocation();

		if (time >= 9) {

			removeSelfFromGrid();

			Flower flower = new Flower();
			flower.putSelfInGrid(gr, loc);

		}

		time++;

	}

}
