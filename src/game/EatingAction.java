package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/** Heal 20 hit points of the actor by eating foods, '?'
 * Called in the EattingBehaviourClass for Humans and Farmers to perform eating action
 * Provide an option in the menu for the player to select
 * @author Song Xen Hwa 
*/
public class EatingAction extends Action {
	
	/**
	 * Constructor
	 * @param food  the item that is meant to be eaten by the actor
	 */
	
	protected Item food;
	public EatingAction(Item food) {
		this.food= food;
	}
	/**
	 * Remove the food item in inventory if actor is the player
	 * Actor heals by 20 hit points
	 * 
	 * @param actor actor which is performing the EatingBehaviour
	 * @param map	the game map
	 * @return 		a string consisting the actor healed by 20 points
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.getDisplayChar()== '@') {
			actor.removeItemFromInventory(food);
		}
		actor.heal(20);
		return actor + " heals by 20 points.";
	}
	
	/**
	 * Displays the action as an option in the menu for player to perform it when available
	 * @return a string consisting on the action that can be done by the player
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " eat food and heal itself.";

	}
	
}
	
