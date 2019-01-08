package hausaufgabe;

import gridworld.framework.actor.Actor;
/*
 * Singleton Design Pattern
 */

final class WoolStorage extends Actor {

	private int amountOfWool;
	private static WoolStorage instance;

	private WoolStorage() {

		amountOfWool = 0;

	}

	static {

		instance = new WoolStorage();

	}

	static WoolStorage getInstance() {

		return instance;

	}

	void putWool() {

		amountOfWool++;

	}

	@Override
	public void act() {

		moveTo(getLocation());

	}

	@Override
	public String toString() {

		return "" + amountOfWool;
	}

}
