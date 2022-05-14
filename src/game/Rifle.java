package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Rifle class extends WeaponItem. It applies a damage that rewards patience.
 * when player aim for 1 round, the damage doubles,
 * when player aim for 2 rounds, instantly kill the zombie actor.
 * it applies a primitive damage when player do not specify they want to use this weapon to shoot. 
 * 
 * @author Xinyi Li
 *
 */
public class Rifle extends WeaponItem {
	/**
	 * determines if rifle is picked up
	 */
	private boolean pickUp;
	/**
	 * the damage when player chose to shoot immediately
	 */
	private int snipeDamage=35;
	/**
	 * constructor
	 */
	public Rifle() {
		super("Rifle", '{',10, "whack");
		pickUp=false;
	}
	/**
	 * returns the rifle's attack action
	 */
	@Override
	public List<Action> getAllowableActions(){
		List<Action> actions=new ArrayList<>();
		if (pickUp) {
			actions.add(new RifleAttackAction(this));			
		}
		return actions;
	}
	
	/**
	 * @return PlayerPickUpItemAction if portable
	 */
	@Override 
	public PickUpItemAction getPickUpAction() {
		if(portable)
			try {
				return new PlayerPickUpItemAction(this);
				}catch (Exception e) {
		        	e.printStackTrace();
		        }
		
		return null;
	}
	/**
	 *@return PlayerDropItemAction if portable
	 */
	@Override 
	public DropItemAction getDropAction() {
		if(portable)
			try {
				return new PlayerDropItemAction(this);
				}catch (Exception e) {
		        	e.printStackTrace();
		        }
		
		return null;
	}
	
	/**
	 *set pickUp to true, called inside PlayerPickUpItemAction
	 */
	@Override
	public void setPickUp() {   
		pickUp=true;
		
	}
	
	/**
	 *set pickUp to false, called inside PlayerDropItemAction
	 */
	@Override
	public void setDrop() {
		pickUp=false;
		
	}
	
	/**
	 * @return the damage of shotgun, called inside RifleAction
	 */
	public int getRifleDamage() {
		return snipeDamage;
	}
}
