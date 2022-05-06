package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {

	public static final Random RANDOM = new Random();
	public static final boolean DEBUG_MODE = true;

	/*
	 * Breedte en Hoogte van alle foto's op beeld
	 */
	public static final int BreedteFirefighter = 56;
	public static final int HoogteFirefighter = 100;
	public static final int BreedteRock = 99;
	public static final int HoogteRock = 99;
	public static final int BreedteDroplet = 33;
	public static final int HoogteDroplet = 33;
	public static final int BreedteFire = 33;
	public static final int HoogteFire = 43;
	public static final int BreedteBuilding = 440;
	public static final int HoogteBuilding = 700;
	public static final int BreedteHeart = 800;
	public static final int HoogteHeart = 725;
	/*
	 * Breedte en hoogte scherm
	 */
	public static final int BreedteScherm = BreedteBuilding;
	public static final int HoogteScherm = HoogteBuilding;
	/*
	 * Coördinaten Brandweerman
	 */
	public static final int XFirefighter1 = BreedteScherm / 2 - BreedteFirefighter / 2;
	public static final int XFirefighter2 = BreedteScherm / 2 + BreedteFirefighter / 2;
	public static final int YFirefighter1 = HoogteScherm - HoogteFirefighter;
	public static final int YFirefighter2 = HoogteScherm;
	public static final int sourceXFirefighter2 = 57;
	public static final int sourceYFirefighter2 = 100;
	/*
	 * Coördinaten van het gebouw
	 */
	public static final int XBuilding1 = 0;
	public static final int XBuilding2 = BreedteBuilding;
	public static final int YBuilding1 = 0;
	public static final int YBuilding2 = HoogteBuilding;
	public static final int sourceXBuilding2 = 401;
	public static final int sourceYBuilding2 = 660;
	/*
	 * Eerste coördinaat van elke gebruikte foto
	 */
	public static final int sourceX1 = 0;
	public static final int sourceY1 = 0;

	public static final int UPDATE_SPEED = 10; // ms
	public static final int SPAWN_SPEED = 4000; //ms
}
