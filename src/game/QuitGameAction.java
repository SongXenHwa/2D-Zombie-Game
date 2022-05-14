package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * QuitGameAction provides the option for player to quit the game instantly.
 * 
 * @author Xinyi Li
 *
 */
public class QuitGameAction extends Action {

	/**
	 * exit the game when executed
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		System.out.println(actor+" quited the Game. Game over");
		System.exit(0);
		return null;
	}

	/**
	 * menu description for quit game
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor+" quits the game";
	}

}
