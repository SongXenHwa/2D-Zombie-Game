package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/** Add an action into the list of the farmer actor that can be done
 * Change the dirt to new crop objects
 * Decrease the number of turn remaining for the crop b calling FertiliseAction()
 * 
 * @author Song Xen Hwa 
*/
public class SowingBehaviour implements Behaviour {
	static HashMap<String,Crop> crops = new HashMap<String,Crop>();
	/**
	 * Used to fertilising crops if actor is standing on an unripe crop
	 * Used to sow new crops if surrounding actor are dirt with probability of 33%
	 * 
	 * @param actor the actor performing the action
	 * @param map   the game map 
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		double probability = Math.random();
		int x = map.locationOf(actor).x();
		int y = map.locationOf(actor).y();
		if (map.at(x,y).getGround().getDisplayChar() == 'c'){
			return new FertiliseAction(crops.get(x+" "+y));
		}
		else if (probability <= 0.33) {
			Crop crop = new Crop();
			for (int i =-1 ; i< 2; i++) {
				for (int j  =-1 ; j< 2; j++) {
					try {
						if (map.at(x+i,y+j).getGround().getDisplayChar() == '.') {
							crops.put((x+i)+" "+(y+j), crop);
							map.at(x+i, y+j).setGround(crop);
							return new SowingAction();
						}
					}catch (Exception e) {
						continue;
					}
				}
			}
		}
		return null;
		
	}
	
	
}
