package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Zombie's roaring action, called by RoarBehaviour with 10% chance.
 * @author Xinyi
 *
 */
public class RoarAction extends Action {

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " says Braaaaains";
	}

}