package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * This interface provides the ability to add methods to Action, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ActionInterface {
	/**
	 * called inside Player's playturn method, generate submenu after the main menu
	 * @param map the current game map
	 * @return the actions displayed in the submenu for the first round in the current turn
	 */
	public default Actions getNextActions(GameMap map) {return null;}
	
	/**
	 * called inside Player's playturn method, generate second round submenu after the the first round submenu
	 * @param map the current gamemap
	 * @param actor an actor who called this method
	 * @param lastAction the last action that the actor took
	 * @return the actions displayed in the submenu of the second round in the current turn
	 */
	public default Actions getSecondNextActions(GameMap map, Actor actor,Action lastAction) {return null;}
	/**
	 * get how long this actor has been waited to aim the target
	 * @return the turn that this actor has been waited to aim the target.
	 */
	public default int getwaitTurn() {return 0;}
	/**
	 * get the target that the actor is aiming at
	 * @return the target that the actor is aiming at
	 */
	public default Actor getTarget() {return null;}
	/**
	 * get the hit point of the actor itself who has this action
	 * @return the hit point of the actor itself who has this action;
	 */
	public default int getHitpoint() {return -1;}
}
