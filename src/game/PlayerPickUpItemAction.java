package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * extends PickUpItemAction class, and when execute, it first set the item state to be picked up,
 * then execute the the same thing as PickUpItemAction.
 * 
 * @author Xinyi
 *
 */
public class PlayerPickUpItemAction extends PickUpItemAction {
	
	/**
	 * @param item an item
	 * @throws Exception item to be picked up is null in basicweapon
	 */
	public PlayerPickUpItemAction(Item item) throws Exception {
		
		super(item);
		if (item==null) {
			throw new Exception("item is null in playerPickUpAction");
		}
	}
	/**
	 * set the item state to be picked up,
	 * remove the item from the map,
	 * add it to the actor's inventory if item can be added to inventory
	 * change the player's attribute with this item if it can;
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		item.setPickUp();
		map.locationOf(actor).removeItem(item);
		if (item.canAddtoInventory()) {
			actor.addItemToInventory(item);	
		}
		
		item.changePlayerAttribute(actor);
		return menuDescription(actor);
	}
	

}