package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/** Provide an option for humans and farmers to eat and gain life
 *  
 * @author Song Xen Hwa 
*/
public class EatingBehaviour implements Behaviour{

	private int hitPoint;
	private int maxHitPoint;

	/** Constructor
	 * 
	 * @param hitPoint    	actor's current hit point
	 * @param maxHitPoint   actor's maximum hit point
	 */
	public EatingBehaviour(int hitPoint, int maxHitPoint) {
		this.hitPoint = hitPoint;
		this.maxHitPoint = maxHitPoint;
	}
	/**
	 * Returns an Eating Action that eats the food if the hitpoint is lesser than the maximum 
	 * and there is food position where the actor is.
	 * 
	 * @param actor	the Actor that performs eating action
	 * @param map	the GameMap
	 * @return 		the result of calling EatingAction(item) method
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		
		for (Item item: map.locationOf(actor).getItems()) {
			if (item != null && item.getDisplayChar()=='?' && this.hitPoint < this.maxHitPoint) {
				map.locationOf(actor).removeItem(item);
				return new EatingAction(item);
				
			}
		}
	
		return null;
	}

}
