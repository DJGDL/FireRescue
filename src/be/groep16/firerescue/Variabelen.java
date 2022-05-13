package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {

	public static final Random RANDOM = new Random();
	public static final boolean DEBUG_MODE = false;

	/*
	 * Breedte en Hoogte Building
	 */
	public static final int BREEDTE_BUILDING = 440;
	public static final int HOOGTE_BUILDING = 700;
	
	/*
	 * Breedte en hoogte scherm
	 */
	public static final int BREEDTE_SCHERM = BREEDTE_BUILDING;
	public static final int HOOGTE_SCHERM = HOOGTE_BUILDING;

	/*
	 * Coördinaten van het gebouw
	 */
	public static final int X_BUILDING_1 = 0;
	public static final int X_BUILDING_2 = BREEDTE_BUILDING;
	public static final int Y_BUILDING_1 = 0;
	public static final int Y_BUILDING_2 = HOOGTE_BUILDING;
	public static final int SOURCE_X_BUILDING_2 = 401;
	public static final int SOURCE_Y_BUILDING_2 = 660;
	/*
	 * Eerste coördinaat van elke gebruikte foto
	 */
	public static final int SOURCE_X1 = 0;
	public static final int SOURCE_Y1 = 0;

	public static final int UPDATE_SPEED = 10; // ms
	public static final int SPAWN_SPEED = 1400; // ms
}
