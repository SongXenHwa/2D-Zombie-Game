package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

/**
 * A vehicle for player to travel between maps
 * @author Song Xen Hwa
 *
 */
public class TownItem extends Item{

	public TownItem(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
	}
	
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

	@Override
	public void setPickUp() {
		
	}

	@Override
	public void setDrop() {
		
	}

	@Override
	public void sideEffect(Actor ZombieActor) {
		
	}
}

