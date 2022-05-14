package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/** Fertilise the crop object by reducing the number of remaining turns
 * @author Song Xen Hwa 
*/
public class FertiliseAction extends Action {
private Crop crop;
	
	/**
	 * Constructor
	 * @param crop	Crop object
	 */
	public FertiliseAction(Crop crop) {
		this.crop = crop;
	}

	/**
	 * This method is used to fertilize crop
	 * 
	 * @param actor  the farmer actor which is performing the SowingBehaviour 
	 * @param map    the game map
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		this.crop.fertilise();
		return menuDescription(actor);
	}

	/**
	 * This method is used to return a string when called in execute()
	 * 
	 * @param actor  the farmer actor which is performing the SowingBehaviour 
	 * @return a string consisting on the action done by the actor
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " fertilise crop";
	}

}
