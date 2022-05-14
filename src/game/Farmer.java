package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/** Create a farmer actor
 * Return an action that can be done by the farmer among the list created
 * @author Song Xen Hwa 
*/
public class Farmer extends Human {
	private ArrayList<Behaviour> behaviours=new ArrayList<>();
//	private Behaviour behaviour = new SowingBehaviour();
	

	/**
	 * Create a farmer actor with the input name with display character 'F' and 200 max hit Points
	 * 
	 * @param name  the name for the farmer created
	 */
	public Farmer(String name) {
		super(name, 'F', 200);
		behaviours.add(new EatingBehaviour(this.hitPoints,this.maxHitPoints));
//		behaviours.add(new SowingBehaviour());
		behaviours.add(new HarvestBehaviour());
		behaviours.add(new SowingBehaviour());
		behaviours.add(new WanderBehaviour());
		
	}
	
	
	/**
	 * Farmer have a probability of 0.33 to sow a crop. 
	 * Eating and harvesting can only be done when actor is directly above the item.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Farmer is
	 * @param display the Display where the Farmer's utterances will be displayed
	 * @return an action done by the farmer actor
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {		
			
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}	
	

}

