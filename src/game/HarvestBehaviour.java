package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/** Provide an option for farmers to harvest ripen crops
 *  
 * @author Song Xen Hwa 
*/
public class HarvestBehaviour implements Behaviour{
	
	/**
	 * Returns a HarvestAction that harvest a ripen crop which is located surrounding the actor.
	 * 
	 * @param actor	the Actor that performs eating action
	 * @param map	the GameMap
	 * @return 		the result of calling HarvestAction(x+i,y+j) method
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {	
		int x = map.locationOf(actor).x();
		int y = map.locationOf(actor).y();
		
		for (int i =-1 ; i< 2; i++) {
			for (int j  =-1 ; j< 2; j++) {
				try {
					if (map.at(x+i, y+j).getGround().getDisplayChar() == 'r') {
						return new HarvestAction(x+i,y+j);
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
		return null;
		
	}

}
