package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {
	/**
	 * Breedte gebouw
	 */
	static Random rand = new Random();
	public static final int XR = rand.nextInt(-500, 500);
	public static final int XD = rand.nextInt(-500, 500);
	public static final int XF = rand.nextInt(-500, 500);
	public static final int Y = 0;
	//public static final int YG = 660;
	//public static final int XB = 57;
	//public static final int YB = 100;
	//public static final int XD = 25;
	//public static final int YD = 25;
	
	public static final int UPDATE_SPEED = 10; // ms
}
