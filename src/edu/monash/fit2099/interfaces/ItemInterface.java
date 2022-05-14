package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {
	
	/**
	 * set the pickup variable in BasicWeapon to be true
	 */
	public default void setPickUp() {};
	
	/**
	 * set the pickup variable in BasicWeapon to be false
	 */
	public default void setDrop() {};
	
	/**
	 * apply side effect of the item to the actor
	 * @param ZombieActor an actor
	 * @throws Exception when ZombieActor is null
	 */
	public default void sideEffect(Actor ZombieActor) throws Exception{};
	/**
	 * called inside PlayerPickUpItemAction, only add the item to the inventory if it returns true 
	 * @return true if the item can be added to inventory, otherwise return false
	 */
	public default boolean canAddtoInventory() {return true;}
	/**
	 * change the actor's attribute
	 * @param actor an actor object
	 */
	public default void changePlayerAttribute(Actor actor) {}
}
