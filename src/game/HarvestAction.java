package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/** Change the ripen crop, 'r' to a new portable item that can be collected, food, '?'
 * Player will automatically add the portable item created into it's inventory
 * Farmer will harvest the crop and create a portable item at the crop's location
 * @author Song Xen Hwa 
 */
public class HarvestAction extends Action {


	private int y;
	private int x;

	/**
	 * constructor
	 * @param x  the x value of the crop's location
	 * @param y  the y value of the crop's location
	 */
	public HarvestAction(int x, int y) {
		this.x= x;
		this.y = y;
	}

	/**
	 * This method is to create a new portable item, food, '?'
	 * Set the ground for the crop back as dirt
	 * 
	 * @param actor  the farmer actor which is performing the HarvestingBehaviour
	 * @param map    the game map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Item food = new PortableItem("food",'?');
		if(actor.getDisplayChar() == '@') {
			actor.addItemToInventory(food);
		}
		else if (actor.getDisplayChar() == 'F'){
			map.at(x,y).addItem(food);
		}
		map.at(x,y).setGround(new Dirt());
		return menuDescription(actor);

	}

	/**
	 * Displays the action as an option in the menu for player to perform it when available
	 * Returns a string when called in execute()
	 *  
	 * @return a string consisting on the action that can be done by the player
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " harvest a crop";
	}
}