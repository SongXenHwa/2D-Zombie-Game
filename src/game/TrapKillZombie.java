package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Bonus Mark
 * Kills zombie and remove them from map
 * @author Song Xen Hwa
 *
 */
public class TrapKillZombie extends Action{

	/**
	 * Kills zombie and remove then from map
	 * @param actor Player
	 * @param map GameMap
	 * @return a string consisting the result
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).setGround(new Trap('X'));
		map.removeActor(actor);
		return menuDescription(actor);
	}

	/**
	 * Prints a string description about the trap killing zombie
	 * @param actor Zombie
	 * @return a string consisting the actions
	 * 
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " is killed from the trap";
	}
	

}
