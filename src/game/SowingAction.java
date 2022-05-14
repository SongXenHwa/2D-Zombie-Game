package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/** 
 * Returns a string on the action done by the actor
 * @author Song Xen Hwa 
*/
public class SowingAction extends Action {
	
	/**
	 * Returns the menuDescription method which returns a string of action done by actor
	 * @param actor the actor which is performing SowingBehaviour()
	 * @param map   the game map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
		
	}
		
	/**
	 * Returns a string on the action done by the actor
	 * 
	 * @param actor	the Actor that performs sowing action
	 * @return 		a string consisting of the action done by the actor
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " sowed a crop.";
	}


}
