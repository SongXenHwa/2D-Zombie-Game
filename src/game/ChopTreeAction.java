package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;


/**
 * Bonus Mark
 * Allow player to chop trees and gain 2 wood
 * @author Song Xen Hwa
 *
 */
public class ChopTreeAction extends Action{
	
	private int y;
	private int x;
	
	/**
	 * constructor
	 * @param x  the x value of the tree's location
	 * @param y  the y value of the tree's location
	 */
	public ChopTreeAction(int x, int y) {
		this.x= x;
		this.y = y;
	}
	
	/**
	 *  This function allows player to set a trap on the map
	 * @param actor Player
	 * @param map GameMap
	 * @return a string consisting the players action
	 * 
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Item wood = new PortableItem("wood",'W');
		map.at(x, y).setGround(new Dirt());
		for (int i = 0; i < 2;i++) {
			actor.addItemToInventory(wood);
		}
		return menuDescription(actor);
		
	}

	/**
	 * Prints a string description in the player's menu
	 * @param actor Player
	 * @return a string consisting player's action
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " chops tree and gain 2 wood.";
	}

}
