package hausaufgabe;

public interface FarmWorldRunnerInterface {

	/*
	 * Erstellt eine neue ActorWorld mit einem Grid der Größe x*y (x Felder
	 * horizontal, y Felder vertikal)
	 */
	public void createNewWorldWithGridSize(int x, int y);

	/*
	 * Setzt ein Animal auf das mit x (horizontale Position) und y (vertikale
	 * Position) bestimmte Feld, falls das Feld im Grid existiert und leer ist.
	 */
	public void addAnimalIfFieldEmpty(int x, int y);

	/*
	 * Setzt ein Sheep auf das mit x (horizontale Position) und y (vertikale
	 * Position) bestimmte Feld, falls das Feld im Grid existiert und leer ist.
	 */
	public void addSheepIfFieldEmpty(int x, int y);

	/*
	 * Setzt ein Lamb auf das mit x (horizontale Position) und y (vertikale
	 * Position) bestimmte Feld, falls das Feld im Grid existiert und leer ist.
	 */
	public void addLambIfFieldEmpty(int x, int y);

	/*
	 * Setzt einen Farmer auf das mit x (horizontale Position) und y (vertikale
	 * Position) bestimmte Feld, falls das Feld im Grid existiert und leer ist.
	 */
	public void addFarmerIfFieldEmpty(int x, int y);

	/*
	 * Setzt einen CreatorFarmer auf das mit x (horizontale Position) und y
	 * (vertikale Position) bestimmte Feld, falls das Feld im Grid existiert und
	 * leer ist.
	 */
	public void addCreatorFarmerIfFieldEmpty(int x, int y);

	/*
	 * Setzt ein WoolStorage auf das mit x (horizontale Position) und y (vertikale
	 * Position) bestimmte Feld, falls das Feld im Grid existiert und leer ist und
	 * noch kein WoolStorage existiert.
	 */
	public void addWoolStorageIfFieldEmpty(int x, int y);

	/*
	 * Setzt einen SheepShearer auf das mit x (horizontale Position) und y
	 * (vertikale Position) bestimmte Feld, falls das Feld im Grid existiert und
	 * leer ist.
	 */
	public void addSheepShearerIfFieldEmpty(int x, int y);

	/*
	 * Setzt einen Actor des im letzten Aufgabenteil beschriebenen Typs auf das mit
	 * x (horizontale Position) und y (vertikale Position) bestimmte Feld, falls das
	 * Feld im Grid existiert und leer ist.
	 */
	public void addGroupSpecificActorIfFieldEmpty(int x, int y);

	/*
	 * Setzt eine Flower auf das mit x (horizontale Position) und y (vertikale
	 * Position) bestimmte Feld, falls das Feld im Grid existiert und leer ist.
	 */
	public void addFlowerIfFieldEmpty(int x, int y);

	/*
	 * Gibt die toString()-Methode des Actors zurück, der sich auf dem mit x
	 * (horizontale Position) und y (vertikale Position) bestimmten Feld befindet.
	 * Gibt null zurück, falls das Feld im Grid nicht existiert oder leer ist.
	 */
	public String getToStringOfActorInField(int x, int y);

	/* Laesst die Simulation n Steps weiterlaufen. */
	public void runNSteps(int n);
}
