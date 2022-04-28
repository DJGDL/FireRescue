package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {
	
	static Random rand = new Random();
	/*
	 * Breedte en hoogte scherm
	 */
	public static final int BScherm = 500;
	public static final int HScherm = 500;
	/*
	 * Breedte en Hoogte van alle foto's op beeld
	 */
	public static final int BreedteR = 66;
	public static final int HoogteR = 66;
	public static final int BreedteD = 33;
	public static final int HoogteD = 33;
	public static final int BreedteF = 33;
	public static final int HoogteF = 43;
	/*
	 * Coördinaten van de steen
	 */
	public static final int XR1 = rand.nextInt(BScherm - 2*BreedteR);
	public static final int XR2 = XR1 + BreedteR; 
	public static final int YR1 = -HoogteR;
	public static final int YR2 = 0;
	public static final int srcXR2 = 1378;
	public static final int srcYR2 = 1378;
	/*
	 * Coördinaten van de druppel
	 */
	public static final int XD1 = rand.nextInt(BScherm - 2*BreedteD);
	public static final int XD2 = XD1 + BreedteD;
	public static final int YD1 = -HoogteD;
	public static final int YD2 = 0;
	public static final int srcXD2 = 133;
	public static final int srcYD2 = 132;
	/*
	 * Coördinaten van de vuurbal
	 */
	public static final int XF1 = rand.nextInt(BScherm - 2*BreedteF);
	public static final int XF2 = XF1 + BreedteF;
	public static final int YF1 = -HoogteF;
	public static final int YF2 = 0;
	public static final int srcXF2 = 548;
	public static final int srcYF2 = 720;
	
	/*
	 * Eerste coördinaat van elke gebruikte foto
	 */
	public static final int srcX1 = 0;
	public static final int srcY1 = 0;
	
	
	
	public static final int UPDATE_SPEED = 10; // ms
}
