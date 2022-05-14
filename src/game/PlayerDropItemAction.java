package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * extends DropItemAction class, and when execute, it first set the item state to be dropped,
 * then execute the the same thing as DropItemAction.
 * @author Xinyi
 *
 */
public class PlayerDropItemAction extends DropItemAction {

	/**
	 * same as the constructor in DropItemAction
	 * @param item an item object
	 * @throws Exception item to be dropped is null in basicweapon
	 */
	public PlayerDropItemAction(Item item)throws Exception {
		super(item);
		if (item==null) {
			throw new Exception("item is null in playerDropItemAction");
		}
	}
	/**
	 * set the item state to be dropped,
	 * remove the item from the actor's inventory and create it on the location of actor in the map.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		item.setDrop();
		actor.removeItemFromInventory(item);
		map.locationOf(actor).addItem(item);
		return menuDescription(actor);
	}
}
