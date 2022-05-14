package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Bonus Mark
 * Player will be able to repair any traps nearby as long as it has 2 wood in it inventory
 * @author Song Xen Hwa
 */
public class RepairTrapAction extends Action{

	private int y;
	private int x;
	private final int WOOD_COUNT = 2;
	
	/**
	 * constructor
	 * @param x  the x value of the trap's location
	 * @param y  the y value of the trap's location
	 */
	public RepairTrapAction(int x, int y) {
		this.x= x;
		this.y = y;
	}
	
	
	/**
	 *  This function allows player to repair a trap on the map
	 * @param actor Player
	 * @param map GameMap
	 * @return a string consisting the players action
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.at(x, y).setGround(new Trap('O'));
		int counter = 0;
		int i = 0;
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
	 * @return a strng consisting player's action
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " repair deactivated trap.";
	}

}
