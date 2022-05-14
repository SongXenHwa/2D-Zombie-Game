package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This class handles the Action to craft a basic weapon.
 * it is initialized in BasicWeapon's getAllowableActions.
 * @author Xinyi
 *
 */
public class CraftAction extends Action {
	/**
	 * a basic weapon
	 */
	private BasicWeapon weapon;
	
	/**
	 * set the parameter to the attribute.
	 * @param weapon an object of type BasicWeapon
	 * @throws Exception handles null parameter weapon 
	 */

	public CraftAction(BasicWeapon weapon) throws Exception{
		if (weapon==null) {
			throw new Exception("Basic Weapon is null when crafting");
		}
		this.weapon=weapon;
	}
	/**
	 * add the crafted object of type WeaponItem to the actor's inventory, 
	 * and delete the older weapon.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.addItemToInventory(weapon.CraftTo());
		actor.removeItemFromInventory(weapon);
		return menuDescription(actor);
	}

	/**
	 *@return a description that the crafting has happened.
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" crafts the "+weapon.getName()+" into a "+weapon.getCraftToName();
	}

}
