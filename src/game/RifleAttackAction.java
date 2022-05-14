package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * 
 * RifleAttackAction class is the action that sniper rifle has. 
 * It applies the damage to the target depends on the turns of aiming.
 * When aim for 1 turn, the damage doubles, when aim for 2 turns, instantly kills the target
 * 
 * @author Xinyi Li
 *
 */
public class RifleAttackAction extends Action {
	private Rifle rifle;
	private Actor target;
	private int waitTurn;
	private int instantShoot=-1;
	private int actorHitpoint;
	/**
	 * constructor
	 * 
	 * @param rifle rifle item that called this action
	 */
	public RifleAttackAction(Rifle rifle) {
		this.rifle=rifle;
	}
	/**
	 * constructor
	 * 
	 * @param rifle rifle item that called this action
	 * @param zombie target the rifle is aiming at
	 */
	public RifleAttackAction(Rifle rifle,Actor zombie) {
		this.rifle=rifle;
		this.target=zombie;
	}
	/**
	 * constructor
	 * 
	 * @param rifle rifle item that called this action
	 * @param zombie target the rifle is aiming at
	 * @param instantAttack an integer between 0 1 where 1 means attack, 0 means wait a turn to aim
	 */
	private RifleAttackAction(Rifle rifle,Actor zombie, int instantAttack) {
		assert instantAttack==0 || instantAttack==1 : "Instant attack is neither 0 or 1";
		
		this.rifle=rifle;
		this.target=zombie;
		this.instantShoot=instantAttack;
	}
	/**
	 * constructor
	 * 
	 * @param rifle rifle item that called this action
	 * @param zombie target the rifle is aiming at
	 * @param instantAttack integer between 0 1 where 1 means attack, 0 means wait a turn to aim
	 * @param waitTurn integer between 1 and 2. represents the round that has been waited to aim
	 */
	private RifleAttackAction(Rifle rifle,Actor zombie, int instantAttack, int waitTurn) {
		assert instantAttack==0 || instantAttack==1 : "Instant attack is neither 0 or 1";
		assert waitTurn==1 || waitTurn==2 : "Wait turn is neither 1 or 2";
		this.rifle=rifle;
		this.target=zombie;
		this.instantShoot=instantAttack;
		this.waitTurn=waitTurn;
	}

	/**
	 *	if target choose to shoot, based on the waitTurn apply damage to the target
	 *  else if target choose to aim and wait, return the wait message
	 *  
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		assert target!=null : "Target can not be null when rifle attack action executes";
		assert instantShoot==1 || instantShoot==0 : "instantShoot can only be 0 or 1 when rifle attack action executes";
		int damage=0;
		String message="";
		actorHitpoint=actor.getHitpoint();
		if (instantShoot==1) {
			if (actor.canHaveAmmo() && actor.getRifleAmmoNumber()<=0) { 
				message=actor+" has no bullet.";
			}
			else {
				actor.setRifleAmmoNumber(actor.getRifleAmmoNumber()-1);
				if (waitTurn==0) {
					target.hurt(rifle.getRifleDamage());
					damage=rifle.getRifleDamage();
					
				}
				else if (waitTurn==1) {
					target.hurt(2*rifle.getRifleDamage());
					damage=2*rifle.getRifleDamage();
					
				}
				else {
//					System.out.println(target.getHitpoint());
					target.hurt(target.getHitpoint());
//					System.out.println(target.getHitpoint());
					Actions dropActions = new Actions();
					for (Item item : target.getInventory())
						dropActions.add(item.getDropAction());
					for (Action drop : dropActions)		
						drop.execute(target, map);
					map.removeActor(target);
					return actor+" killed "+target;
				}
				String targetName=target.toString();
				if (!target.isConscious()) {
					Actions dropActions = new Actions();
					for (Item item : target.getInventory())
						dropActions.add(item.getDropAction());
					for (Action drop : dropActions)		
						drop.execute(target, map);
					map.removeActor(target);	
				}
				target=null;
				return actor+" shoots "+targetName+" for "+damage+" damages.";
			}
			
		}

		else {message=actor+" aims a turn to shoot. ";}
		return message;
	}

	/**
	 * menu description for rifle actions
	 */
	@Override
	public String menuDescription(Actor actor) {
		String message="";
		if (target==null) {
			message=actor+" starts aiming direction with the sniper rifle";
		}
		if (target!=null && instantShoot==-1) {
			message=actor +" select "+target+" as the target";
		}
		if (instantShoot==1) {
			message=actor+" shoots straight away";
		}
		else if (instantShoot==0) {
			message=actor+" spends a turn aiming "+target;
		}
		return message;
		
	}
	/**
	 * return the sub menu for all the target that has capability {@code ZombieCapability.UNDEAD} to shoot at
	 */
	@Override
	public Actions getNextActions(GameMap map) {
		Actions actionList=new Actions();
		if (target==null) {
			ArrayList<Actor> targetList=new ArrayList<>();
			for (int x=map.getXRange().min();x<=map.getXRange().max();x++) {
				for (int y=map.getYRange().min();y<=map.getYRange().max();y++) {
					if (map.isAnActorAt(map.at(x, y))&&map.getActorAt(map.at(x, y)).hasCapability(ZombieCapability.UNDEAD)){
						targetList.add(map.getActorAt(map.at(x, y)));
					}
				}
			}
			
			for (Actor actorInstance:targetList) {
				actionList.add(new RifleAttackAction(rifle,actorInstance));
			}
			if (actionList.size()!=0) {
				return actionList;
			}
			
		}

		return null;
		
	}
	/**
	 * return the second sub menu
	 * that contains shoot action and aim action, or just shoot action if wait turn equals 2
	 */
	@Override
	public Actions getSecondNextActions(GameMap map,Actor thisPlayer,Action lastAction) {
		assert target!=null : "target should be set by the second sub menu opened for rifle attack action";
		Actions actionList=new Actions();
		
		
		if (lastAction.getTarget()!=target || (lastAction.getHitpoint()!=thisPlayer.getHitpoint())) {  
			actionList.add(new RifleAttackAction(rifle,target,1));
			actionList.add(new RifleAttackAction(rifle,target,0));

			return actionList;
		}
		else if (lastAction.getTarget()==target && lastAction.getwaitTurn()==0) {
			actionList.add(new RifleAttackAction(rifle,target,1,lastAction.getwaitTurn()+1));
			actionList.add(new RifleAttackAction(rifle,target,0,lastAction.getwaitTurn()+1));

			return actionList;
		}
		else if (lastAction.getTarget()==target && lastAction.getwaitTurn()==1) {
			actionList.add(new RifleAttackAction(rifle,target,1,lastAction.getwaitTurn()+1));

			return actionList;
		}
		return null;
	}
	
	/**
	 * get the wait turn of this action
	 */
	@Override
	public int getwaitTurn() {   
		return waitTurn;
	}
	
	/**
	 * get the target of this action
	 */
	@Override
	public Actor getTarget() {
		return target;
	}
	/**
	 * get the hit point of the actor that uses the rifle that triggered this action
	 */
	@Override
	public int getHitpoint() {
		return actorHitpoint;
	}

	

}