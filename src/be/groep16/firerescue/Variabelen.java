package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {

	public static final Random rand = new Random();

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
	 * Coördinaten van de steen
	 */
	public static final int XRock1 = rand.nextInt(BreedteScherm - BreedteRock);
	public static final int XRock2 = XRock1 + BreedteRock;
	public static final int YRock1 = -HoogteRock;
	public static final int YRock2 = 0;
	public static final int sourceXRock2 = 1378;
	public static final int sourceYRock2 = 1378;
	/*
	 * Coördinaten van de druppel en smile druppel
	 */
	public static final int XDroplet1 = rand.nextInt(BreedteScherm - BreedteDroplet);
	public static final int XDroplet2 = XDroplet1 + BreedteDroplet;
	public static final int YDroplet1 = -HoogteDroplet;
	public static final int YDroplet2 = 0;
	public static final int sourceXDroplet2 = 133;
	public static final int sourceYDroplet2 = 132;
	public static final int XSmileDroplet1 = rand.nextInt(BreedteScherm - BreedteDroplet);
	public static final int XSmileDroplet2 = XSmileDroplet1 + BreedteDroplet;
	public static final int YSmileDroplet1 = -HoogteDroplet;
	public static final int YSmileDroplet2 = 0;
	public static final int sourceXSmileDroplet2 = 890;
	public static final int sourceYSmileDroplet2 = 890;
	/*
	 * Coördinaten van de vuurbal
	 */
	public static final int XFire1 = rand.nextInt(BreedteScherm - BreedteFire);
	public static final int XFire2 = XFire1 + BreedteFire;
	public static final int YFire1 = -HoogteFire;
	public static final int YFire2 = 0;
	public static final int sourceXFire2 = 548;
	public static final int sourceYFire2 = 720;
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
}
