package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Creates a Mambo Marie actor which has an UNDEAD capabilities
 * Returns an action that can be done by the actor
 * @author Song Xen Hwa
 * 
 */
public class MamboMarie extends ZombieActor {
	
	private Behaviour [] behaviours = {
			new ChantingBehaviour(),
			new WanderBehaviour()};
	
	/**
	 * Create a Mambo Marie actor with display character 'M' and 200 max hit Points
	 * 
	 */
	public MamboMarie() {
		super("Mambo Marie",'M' ,200 ,ZombieCapability.UNDEAD );
	}
	
	/**
	 * Mambo Marie has a skill to spawn 5 random zombie every 10 turns
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Mambo Marie is
	 * @param display the Display where the Mambo Marie's utterances will be displayed
	 * @return an action done by the Mambo Marie actor
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
	
	@Override
	public int getHitpoint() {
		return hitPoints;
	}
}