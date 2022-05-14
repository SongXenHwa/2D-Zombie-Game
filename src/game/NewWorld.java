package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;


/**
 * @author Xinyi Li, Song Xen Hwa
 * This class overrides the World class's stillRunning() function.
 * Instead of just checking if player's alive, it detects when player lose or win.
 */
public class NewWorld extends World {
	private int mamboMarieTurns = -1;
	private boolean mamboMarieOnMap = false;
	private boolean mamboMarieAlive = true;
	MamboMarie[] mamboMarie = {null};
	/**
	 * hasPrint stores whether the end game message has been printed.
	 */
	protected boolean hasPrint=false;
	/**
	 * @param display the map displayed to the user 
	 */
	public NewWorld(Display display) {
		super(display);
		
		 
	}
	/**
	 *if there's no actor who has capability {@code ZombieCapability.UNDEAD}, 
	 *end game, print player wins message
	 *
	 *if there's no actor who has capability {@code ZombieCapability.AlIVE} except the player, 
	 *end game, print player loses message
	 */
	@Override
	protected boolean stillRunning() {
		boolean hasZombie=false;
		boolean hasHuman=false;
		for (Actor actor : actorLocations) {
			if (actor.hasCapability(ZombieCapability.UNDEAD) || mamboMarieAlive == true) {
				hasZombie=true;
			}

			if (actor.hasCapability(ZombieCapability.ALIVE) && actor!=player) {
				hasHuman=true;
			}
		}
		if (hasHuman==false || !actorLocations.contains(player)) {
			if (!hasPrint) {
				System.out.println("Player loses.");
			}
			
			hasPrint=true;
			return false;
		}
		if (hasZombie==false) {
			if (!hasPrint) {
				System.out.println("Player wins.");
			}
			hasPrint=true;
			return false;
		}
		return actorLocations.contains(player);
	}
	
	/**
	 * spawn the mambo marie while the game is still running
	 */
	public void run() {
		if (player == null)
			throw new IllegalStateException();

		// initialize the last action map to nothing actions;
		for (Actor actor : actorLocations) {
			lastActionMap.put(actor, new DoNothingAction());
		}

		// This loop is basically the whole game
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);

			// Process all the actors.
			for (Actor actor : actorLocations) {
				if (stillRunning())
					processActorTurn(actor);
			}

			// Tick over all the maps. For the map stuff.
			for (GameMap gameMap : gameMaps) {
				gameMap.tick();
			}
			spawnMamboMarie();
		}
		display.println(endGameMessage());
	}
	
	/**
	 * spawn the mambo marie if the probability is less than 0.05 
	 * checks if the mambo marie is still alive or on the map, if not chane the ariable to false
	 * resets the turns so that the mambo marie can spawn again after the 30 turn vanishing
	 */
	public void spawnMamboMarie() {
		double prob = Math.random();
		GameMap map = super.actorLocations.locationOf(player).map();
		if (mamboMarieAlive == true) {
			if (prob <= 0.05 ){
				if((mamboMarieOnMap == false)&&(mamboMarie[0] == null)) {
					mamboMarie[0] = new MamboMarie();
					map.at(0,24).addActor(mamboMarie[0]);
					mamboMarieOnMap = true;
				}else if((mamboMarieOnMap == false)&&(mamboMarie[0] != null)){
					map.at(0,24).addActor(mamboMarie[0]);
					mamboMarieOnMap = true;
				}	
			}
			
			try {
				if (mamboMarie[0].isConscious() == false) {
					mamboMarieAlive = false;
					mamboMarieOnMap = false;
				}
			}catch(Exception e) {
				
			}
			
		}
		if(mamboMarieOnMap == true) {
			mamboMarieTurns ++;
		}
		
		if(mamboMarieTurns == 30) {
			mamboMarieTurns = -1;
			map.removeActor(mamboMarie[0]);
			mamboMarieOnMap = false;
		}
//		System.out.println(mamboMarieTurns);
	}
}
	
