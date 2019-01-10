package hausaufgabe;

import java.awt.Color;

import gridworld.framework.actor.Actor;

/**
 * @author Friedrich
 */
final class ExcrementStorage extends Actor {

	static int amountOfExcrement;
	private static ExcrementStorage instance;

	private ExcrementStorage() {

		amountOfExcrement = 0;
		setColor(Color.ORANGE);

	}

	static {

		instance = new ExcrementStorage();

	}

	static ExcrementStorage getInstance() {

		return instance;

	}

	void putExcrement() {

		amountOfExcrement++;

	}

	@Override
	public void act() {

		moveTo(getLocation());

	}

	@Override
	public String toString() {

		return "" + amountOfExcrement;

	}

}