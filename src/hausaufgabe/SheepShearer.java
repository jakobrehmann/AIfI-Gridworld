package hausaufgabe;

import java.util.ArrayList;

import gridworld.framework.actor.Actor;
import gridworld.framework.actor.Flower;

public class SheepShearer extends Farmer {
	
	private WoolStorage storage;

	public SheepShearer(WoolStorage storage) {
		// TODO Auto-generated constructor stub
		super();
		this.storage = storage;
	}
	
	@Override
	public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (a instanceof Flower)
                a.removeSelfFromGrid();
            
            else if (a instanceof Sheep) {
            	if (((Sheep)a).getLastTimeSheared() >= 3) {
            		((Sheep)a).getsSheared();
            		storage.putWool();
            	}
            }
        }
    }
	

}
