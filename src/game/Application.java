package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the zombie apocalypse game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new NewWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"....................................##########..................................",
		"..........................###########........#####..............................",
		"............++...........##......................########.......................",
		"..............++++.......#..............................##......................",
		".............+++...+++...#...............................#......................",
		".........................##..............................##.....................",
		"..........................#...............................#.....................",
		".........................##...............................##....................",
		".........................#...............................##.....................",
		".........................###..............................##....................",
		"...........................####.....................#######.....................",
		"..............................#########.........#####...........................",
		"............+++.......................#.........#...............................",
		".............+++++....................#.........#...............................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"#...............................................................................");
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);


		List<String> townMap = Arrays.asList(
				"....++..........................................................................",
				".++++++++.................+++......+++..........................................",
				"++++++++...............+++###......###+++++++++++++++++++.......................",
				"...+++++++.............++++##......##++++++++++++++++++++++.........+++.........",
				".......................++####......#######################++.......+++++........",
				".......................++#...............................#++........++++........",
				".......................++#...............................#++....................",
				".......................++#...............................#++....................",
				".......................++#...............................#++....................",
				".......................++#...............................#++....................",
				".......................++#...............................#++....................",
				".........+++...........++#...............................#++....................",
				"........+++++..........++#...............................#++....................",
				".........+++...........++#######################.......###++....................",
				"........................++++++++++++++++++++++##.......##+++....................",
				".........................++++++++++++++++++++###.......###++....................",
				".............................................+++.......+++......................",
				"................................................................................",
				"......................................................+++.......................",
				"................................................................................",
				".............++++++......................................................++.....",
				"................++++++..................................................++.++...",
				"...................++++++................................................++++...",
				"..........................................................................++....",
				"#...............................................................................");
		GameMap town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);
		
		// creating car, 'Q' in both maps
		TownItem car = new TownItem("Car", 'Q', false);
        car.addAction(new MoveActorAction(town.at(51,15), "to Town!"));
        gameMap.at(48, 16).addItem(car);
				
        TownItem car1 = new TownItem("Car", 'Q', false);
        car1.addAction(new MoveActorAction(gameMap.at(42,15), "to Open Space!"));
        town.at(47,17).addItem(car1);
        
		Actor player = new Player("Player", '@', 500);
		world.addPlayer(player, gameMap.at(42,15)); 
		
	    // Place some random humans
		String[] humans = {"Carlton", "May", "Vicente", "Andrea", "Wendy", 
				"Elina", "Winter", "Clem", "Jacob", "Jaquelyn"};
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x,  y).addActor(new Human(name));	
		}
//		 Adding humans in town map
		String[] humansTown = {"Steve", "April", "Aaron", "Ian", "Monash", 
				"Clayton", "Garfield", "Caufield"};
		int xcoor, ycoor;
		for (String name : humansTown) {
			do {
				xcoor = (int) Math.floor(Math.random() * 20.0 + 30.0);
				ycoor = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} 
			while (town.at(xcoor, ycoor).containsAnActor());
			town.at(xcoor,  ycoor).addActor(new Human(name));	
		}
				
		//Place 2 random farmers
		String[] farmers = {"Farmer Bob", "Farmer Carla"};
		int x2, y2;
		for (String name: farmers) {
			do {
				x2 = (int) Math.floor(Math.random()* 20.0 + 30.0);
				y2 = (int)Math.floor(Math.random()* 7.0 + 5.0);
			}
			while (gameMap.at(x2, y2).containsAnActor());
			gameMap.at(x2,  y2).addActor(new Farmer(name));
		}
		
		// Adding farmer in town map
		String[] farmersTown = {"Farmer Gucci", "Farmer Soggy"};
		int x3, y3;
		for (String name: farmersTown) {
			do {
				x3 = (int) Math.floor(Math.random()* 20.0 + 30.0);
				y3 = (int)Math.floor(Math.random()* 7.0 + 5.0);
			}
			while (town.at(x3, y3).containsAnActor());
			town.at(x3,  y3).addActor(new Farmer(name));
		}
		
		// place a simple weapon
		gameMap.at(71, 20).addItem(new Plank());
		gameMap.at(64, 13).addItem(new Plank());
		gameMap.at(73,15).addItem(new Shotgun());
		gameMap.at(73, 16).addItem(new Rifle());
		gameMap.at(73, 19).addItem(new ShotgunBulletBox(5));
		gameMap.at(73, 18).addItem(new RifleBulletBox(6));
		town.at(56, 5).addItem(new RifleBulletBox(3));
		town.at(55, 5).addItem(new ShotgunBulletBox(8));
		town.at(55, 6).addItem(new ShotgunBulletBox(5));
		town.at(56, 6).addItem(new RifleBulletBox(6));
		town.at(26, 12).addItem(new Plank());
		town.at(26, 11).addItem(new Plank());

		
		gameMap.at(0,0).addActor(new Zombie("Groan"));
		gameMap.at(0,18).addActor(new Zombie("Boo"));
		gameMap.at(10,4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50,18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaah"));
		gameMap.at(62,12).addActor(new Zombie("Akpoiojds"));
		town.at(11,20).addActor(new Zombie("Tufndhgs"));
		town.at(15,1).addActor(new Zombie("Smnergmns"));
		town.at(0,12).addActor(new Zombie("YGBesfm"));
		town.at(75,20).addActor(new Zombie("HniuarwWh"));
		town.at(70,0).addActor(new Zombie("Qdjsbfsdf"));
		world.run();
	}
}
