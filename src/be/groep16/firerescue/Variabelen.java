package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {
	/**
	 * Breedte gebouw
	 */
	static Random rand = new Random();
	public static final int XR = rand.nextInt(-500, 500);
	public static final int XD1 = rand.nextInt(0, 500);
	public static final int XD2 = XD1 + 66;
	public static final int YD2 = 0;
	public static final int srcXD2 = 133;
	public static final int srcYD2 = 132;
	public static final int XF = rand.nextInt(-500, 500);
	public static final int Y1 = -66;
	public static final int srcX1 = 0;
	public static final int srcY1 = 0;
	
	
	
	public static final int UPDATE_SPEED = 10; // ms
}
