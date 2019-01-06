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
	
    public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }

	@Override
	public void processActors(ArrayList<Actor> actors) {

		// System.out.println(Temp1);

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

			int tempLoc = getDirectionToward(locBiogas);
			Location nextLoc = getAdjacentLocation(tempLoc);
			moveTo(nextLoc);

			Location tempLoc1 = getLocation();

			if (tempLoc1.hashCode() == locBiogas.hashCode()) {

				Temp1 = 0;
				Temp2 = 1;

			}

		}

		// int loca1 = getLocation().getRow();

		// System.out.println(ExcrementStorage.amountOfExcrement);

	}

	public ExcrementStorage getStorage() {

		return storage;

	}

	public void setStorage(ExcrementStorage storage) {

		this.storage = storage;

	}
	
    public int getDirectionToward(Location target)
    {
        int dx = target.getCol() - getLocation().getCol();
        int dy = target.getRow() - getLocation().getRow();
        // y axis points opposite to mathematical orientation
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = Location.RIGHT - angle;
        // prepare for truncating division by 45 degrees
        compassAngle += Location.HALF_RIGHT / 2;
        // wrap negative angles
        if (compassAngle < 0)
            compassAngle += Location.FULL_CIRCLE;
        // round to nearest multiple of 45
        return (compassAngle / Location.HALF_RIGHT) * Location.HALF_RIGHT;
    }
	
    
    public Location getAdjacentLocation(int direction)
    {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + Location.HALF_RIGHT / 2) % Location.FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += Location.FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / Location.HALF_RIGHT) * Location.HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == Location.EAST)
            dc = 1;
        else if (adjustedDirection == Location.SOUTHEAST)
        {
            dc = 1;
            // dr = 1;
        }
        else if (adjustedDirection == Location.SOUTH)
            dr = 1;
        else if (adjustedDirection == Location.SOUTHWEST)
        {
            // dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == Location.WEST)
            dc = -1;
        else if (adjustedDirection == Location.NORTHWEST)
        {
            dc = -1;
            // dr = -1;
        }
        else if (adjustedDirection == Location.NORTH)
            dr = -1;
        else if (adjustedDirection == Location.NORTHEAST)
        {
            // dc = 1;
            dr = -1;
        }
        return new Location(getLocation().getRow() + dr, getLocation().getCol() + dc);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}