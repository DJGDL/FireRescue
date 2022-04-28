package be.groep16.firerescue;

import java.util.Random;

public class Variabelen {
	
	static Random rand = new Random();
	
	/*
	 * Breedte en Hoogte van alle foto's op beeld
	 */
	public static final int BreedteB = 56;
	public static final int HoogteB = 100;
	public static final int BreedteR = 99;
	public static final int HoogteR = 99;
	public static final int BreedteD = 33;
	public static final int HoogteD = 33;
	public static final int BreedteF = 33;
	public static final int HoogteF = 43;
	public static final int BreedteG = 440;
	public static final int HoogteG = 700;
	/*
	 * Breedte en hoogte scherm
	 */
	public static final int BScherm = BreedteG;
	public static final int HScherm = HoogteG;
	/*
	 * Coördinaten Brandweerman
	 */
	public static final int XB1 = BScherm/2 - BreedteB/2;
	public static final int XB2 = BScherm/2 + BreedteB/2;
	public static final int YB1 = HScherm - HoogteB;
	public static final int YB2 = HScherm;
	public static final int srcXB2 = 57;
	public static final int srcYB2 = 100;
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
	 * Coördinaten van de druppel en smile druppel
	 */
	public static final int XD1 = rand.nextInt(BScherm - 2*BreedteD);
	public static final int XD2 = XD1 + BreedteD;
	public static final int YD1 = -HoogteD;
	public static final int YD2 = 0;
	public static final int srcXD2 = 133;
	public static final int srcYD2 = 132;
	public static final int XSD1 = rand.nextInt(BScherm - 2*BreedteD);
	public static final int XSD2 = XSD1 + BreedteD;
	public static final int YSD1 = -HoogteD;
	public static final int YSD2 = 0;
	public static final int srcXSD2 = 890;
	public static final int srcYSD2 = 890;
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
	 * Coördinaten van het gebouw
	 */
	public static final int XG1 = 0;
	public static final int XG2 = BreedteG;
	public static final int YG1 = 0;
	public static final int YG2 = HoogteG;
	public static final int srcXG2 = 401;
	public static final int srcYG2 = 660;
	/*
	 * Eerste coördinaat van elke gebruikte foto
	 */
	public static final int srcX1 = 0;
	public static final int srcY1 = 0;
	
	
	
	public static final int UPDATE_SPEED = 10; // ms
}
