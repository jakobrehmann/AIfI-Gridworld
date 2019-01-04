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

	public Tractor(ExcrementStorage storage, Location locBiogas) {

		super();
		this.setStorage(storage);
		this.setColor(Color.RED);
		this.locBiogas = locBiogas;

	}

	/*
	 * public Tractor(ExcrementStorage storage) {
	 * 
	 * super(); this.setStorage(storage); this.setColor(Color.RED);
	 * 
	 * 
	 * }
	 */

	@Override
	public void processActors(ArrayList<Actor> actors) {

		// System.out.println(locBiogas);

		if (ExcCounter < 5) {

			for (Actor a : actors) {
				if (a instanceof Flower) {
					a.removeSelfFromGrid();
				}

				else if (a instanceof Excrement) {

					storage.putExcrement();
					a.removeSelfFromGrid();
					ExcCounter++;
					

				}

			}

		} else {

			Location locTemp = getLocation();

			if (locTemp == locBiogas) {
				
				ExcCounter = 0;
				System.out.println("ich bin hier");

			} else {

				moveTo(locBiogas);

			}

		}
		
		// System.out.println("Bisher so viel: " + ExcCounter);;
		
	}

	/*
	 * public void act() {
	 * 
	 * if (canMove()) {
	 * 
	 * if (Math.random() < 0.6) {
	 * 
	 * move();
	 * 
	 * } else if (Math.random() < 0.5) {
	 * 
	 * turn_right();
	 * 
	 * } else {
	 * 
	 * turn_left();
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * turn_right();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public void turn_right() {
	 * 
	 * setDirection(getDirection() + Location.HALF_RIGHT);
	 * 
	 * }
	 * 
	 * public void turn_left() {
	 * 
	 * setDirection(getDirection() + Location.HALF_LEFT);
	 * 
	 * }
	 * 
	 * public void move() {
	 * 
	 * Grid<Actor> gr = getGrid();
	 * 
	 * if (gr == null) {
	 * 
	 * return;
	 * 
	 * }
	 * 
	 * Location loc = getLocation(); Location next =
	 * loc.getAdjacentLocation(getDirection());
	 * 
	 * if (gr.isValid(next)) {
	 * 
	 * moveTo(next);
	 * 
	 * } else {
	 * 
	 * removeSelfFromGrid();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public boolean canMove() {
	 * 
	 * Grid<Actor> gr = getGrid();
	 * 
	 * if (gr == null) {
	 * 
	 * return false;
	 * 
	 * }
	 * 
	 * Location loc = getLocation(); Location next =
	 * loc.getAdjacentLocation(getDirection());
	 * 
	 * if (!gr.isValid(next)) {
	 * 
	 * return false;
	 * 
	 * }
	 * 
	 * Actor neighbor = gr.get(next); return (neighbor == null) || (neighbor
	 * instanceof Flower) || (neighbor instanceof Excrement);
	 * 
	 * }
	 */

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}

}
