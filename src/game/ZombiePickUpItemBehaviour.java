package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * this class is the zombie's pickupitemBehaviour, a zombie should only pick up weaponitems, not crops
 * @author Xinyi
 *
 */
public class ZombiePickUpItemBehaviour implements Behaviour {

	public ZombiePickUpItemBehaviour() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the first weaponitem that can be picked up by the zombie
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		Location here=map.locationOf(actor);
		//System.out.println(actor+" has "+actor.getInventory().size()+" things!");
		if (actor.getInventory().size()==0) {
			Actions actions = new Actions();
			for (Item item : here.getItems()) {
				if (item instanceof WeaponItem) {
					actions.add(item.getPickUpAction());
				}
			}
			if (actions.size()>0) {
				return actions.getUnmodifiableActionList().get(0);     //avoid privacy leak
			}
		}
		return null;
	}

}