package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GreatRock implements Entity {
	private static BufferedImage image;
	
	public static final int BREEDTE_GREAT_ROCK = 99;
	public static final int HOOGTE_GREAT_ROCK = 99;

	public static final int Y_GREAT_ROCK_1 = -HOOGTE_GREAT_ROCK * 2;
	public static final int Y_GREAT_ROCK_2 = 0;
	public static final int SOURCE_X_GREAT_Rock_2 = 1378;
	public static final int SOURCE_Y_GEAT_ROCK_2 = 1378;

	private int xRock1 = 0;
	private int xRock2 = 0;
	private Vector positie;
	private float velocity;

	public GreatRock() {
		reset(0);

		if (image == null) {
			// Loading new GreatRock image
			URL greatRock = getClass().getResource("Steen.png");

			try {
				image = ImageIO.read(greatRock);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, velocity);

	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, xRock1 + (int) (positie.getX()), Y_GREAT_ROCK_1 + (int) (positie.getY()),
				xRock2 + (int) (positie.getX()), Y_GREAT_ROCK_2 + (int) (positie.getY()), Variabelen.SOURCE_X1,
				Variabelen.SOURCE_Y1, SOURCE_X_GREAT_Rock_2, SOURCE_Y_GEAT_ROCK_2, null);

	}

	@Override
	public void reset(float difficulty) {
		xRock1 = Variabelen.RANDOM.nextInt(Variabelen.BREEDTE_SCHERM - (BREEDTE_GREAT_ROCK * 2));
		xRock2 = xRock1 + BREEDTE_GREAT_ROCK * 2;

		positie = new Vector(0, 0);
		velocity = Math.min(1f + 0.00025f * difficulty, 6);
	}

	@Override
	public boolean isDead() {
		return positie.getY() > Variabelen.HOOGTE_SCHERM + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle((int) ((BREEDTE_GREAT_ROCK * 4) / 5), (int) ((HOOGTE_GREAT_ROCK * 4) / 5));
		r.x = xRock1 + (int) ((BREEDTE_GREAT_ROCK * 6) / 10) + (int) positie.getX();
		r.y = Y_GREAT_ROCK_1 + (int) ((HOOGTE_GREAT_ROCK * 6) / 10) + (int) positie.getY();

		return r;
	}
}
