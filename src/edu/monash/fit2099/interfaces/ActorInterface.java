package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	/**
	 * get shotgun ammunition
	 * @return shotgun ammunition number
	 */
	public default int getShotgunAmmoNumber() {return 0;}
	/**
	 * set shotgun ammunition
	 * @param number integer greater than 0
	 */
	public default void setShotgunAmmoNumber(int number) {};
	/**
	 * get rifle ammunition
	 * @return rifle ammunition number
	 */
	public default int getRifleAmmoNumber() {return 0;}
	/**
	 * set rifle ammunition
	 * @param number integer greater than 0
	 */
	public default void setRifleAmmoNumber(int number) {};
	
	/**
	 * detect if the actor can have ammunition or not
	 * @return true if the actor can have ammunition, otherwise return false
	 */
	public default boolean canHaveAmmo() {return false;}
	/**
	 * get the hitpoint of the actor
	 * @return hit point of the actor
	 */
	public default int getHitpoint() {return -1;}
	public default boolean HaveKilledMambo() {return false;}

}
