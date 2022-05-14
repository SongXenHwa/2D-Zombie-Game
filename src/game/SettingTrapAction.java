package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Bonus Mark
 * Allows player set new traps on the ground if they have 4 wood in their inventory
 * @author Song Xen Hwa
 */
public class SettingTrapAction extends Action{

	private final int WOOD_COUNT = 4;
	
	/**
	 *  This function allows player to set a trap on the map
	 * @param actor Player
	 * @param map GameMap
	 * @return a string consisting the players action
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int counter = 0;
		int i = 0;
		map.locationOf(actor).setGround(new Trap('O'));
		while (i < actor.getInventory().size()){
			if (counter< WOOD_COUNT && actor.getInventory().get(i).getDisplayChar() == 'W') {
				actor.removeItemFromInventory(actor.getInventory().get(i));
				counter++;
			}else {
			i++;}
		}
		return menuDescription(actor);
	}

	/**
	 * Prints a string description in the player's menu
	 * @param actor Player
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sets a trap.";
	}
	

}
