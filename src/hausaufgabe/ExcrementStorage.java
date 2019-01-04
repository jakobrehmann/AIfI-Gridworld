package hausaufgabe;

import gridworld.framework.actor.Actor;

final class ExcrementStorage extends Actor {

	private int amountOfExcrement;
	private static ExcrementStorage instance;

	private ExcrementStorage() {
		amountOfExcrement = 0;
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