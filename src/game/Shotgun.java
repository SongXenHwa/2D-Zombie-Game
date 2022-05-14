package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * 
 * Shotgun class extends WeaponItem. It applies a range damage when the player chose to aim at a direction. 
 * If player choose to attack a target with this weapon, it applies a primitive damage. 
 * 
 * @author Xinyi Li
 */
public class Shotgun extends WeaponItem {
	/**
	 * determines if the shotgun is currently picked up
	 */
	private boolean pickUp;
	/**
	 * range damage
	 */
	private int rangeDamage=20;
	/**
	 * constructor that sets its name, char, primitive damage 
	 * and verb. pickUp is set to false in default.
	 */
	public Shotgun() { 
		super("Shotgun", '<',10, "whack");
		pickUp=false;
		
	}
	
	/**
	 *@return the ShotgunAction if it has been picked up
	 */
	@Override
	public List<Action> getAllowableActions(){
		List<Action> actions=new ArrayList<>();
		if (pickUp) {
			actions.add(new ShotgunAction(this));			
		}
		return actions;
	}
	
	/**
	 *@return PlayerPickUpItemAction if portable
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
	 * @return the range damage of shotgun, called inside ShotgunAction
	 */
	public int getRangeDamage() {
		return rangeDamage;
	}
}