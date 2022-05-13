package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Firefighter implements Entity {
	private Vector positie;
	private float speed;
	private BufferedImage image;
	private Rectangle boundingBox;

	private boolean keyLeft = false;
	private boolean keyRight = false;
	
	public static final int BREEDTE_FIREFIGHTER = 56;
	public static final int HOOGTE_FIREFIGHTER = 100;

	public int xFirefighter1 = Variabelen.BREEDTE_SCHERM / 2 - BREEDTE_FIREFIGHTER / 2;
	public int xFirefighter2 = Variabelen.BREEDTE_SCHERM / 2 + BREEDTE_FIREFIGHTER / 2;
	public static final int Y_FIREFIGHTER_1 = Variabelen.HOOGTE_SCHERM - HOOGTE_FIREFIGHTER;
	public static final int Y_FIREFIGHTER_2 = Variabelen.HOOGTE_SCHERM;
	public static final int SOURCE_X_FIREFIGHTER_2 = 57;
	public static final int SOURCE_Y_FIREFIGHTER_2 = 100;

	public boolean direction = true; // true --> firefighter looks right

	Firefighter() {
		positie = new Vector(0, 0);

		setDifficulity(0); // sets speed with 0 difficulity
		boundingBox = new Rectangle(BREEDTE_FIREFIGHTER - 20, HOOGTE_FIREFIGHTER);

		// Loading new firefighter image
		URL firefighter = getClass().getResource("brandweerman.png");

		try {
			image = ImageIO.read(firefighter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		if (keyRight && positie.getX() < ((Variabelen.BREEDTE_SCHERM / 2) - (BREEDTE_FIREFIGHTER / 2)))
			positie = positie.add(speed * deltaTime / 10, 0);

		if (keyLeft && positie.getX() > boundingBox.getWidth() - 5 - Variabelen.BREEDTE_SCHERM / 2)
			positie = positie.add(-speed * deltaTime / 10, 0);

		if (direction) {
			boundingBox.x = xFirefighter1 + 20 + (int) positie.getX();
			boundingBox.y = Y_FIREFIGHTER_1 + (int) positie.getY();
		} else {
			boundingBox.x = xFirefighter1 + (int) positie.getX();
			boundingBox.y = Y_FIREFIGHTER_1 + (int) positie.getY();
		}

	}

	public void onKeyDown(int keycode) {
		if (keycode == KeyEvent.VK_LEFT) {
			keyLeft = true;
			direction = false;
		}
		if (keycode == KeyEvent.VK_RIGHT) {
			keyRight = true;
			direction = true;
		}
	}

	public void onKeyUp(int keycode) {
		if (keycode == KeyEvent.VK_LEFT) {
			keyLeft = false;
		}
		if (keycode == KeyEvent.VK_RIGHT) {
			keyRight = false;
		}
	}

	@Override
	public void onDraw(Graphics g) {
		if (direction) {
			g.drawImage(image, xFirefighter1 + (int) (positie.getX()), Y_FIREFIGHTER_1 + (int) (positie.getY()),
					xFirefighter2 + (int) (positie.getX()), Y_FIREFIGHTER_2 + (int) (positie.getY()), Variabelen.SOURCE_X1,
					Variabelen.SOURCE_Y1, SOURCE_X_FIREFIGHTER_2, SOURCE_Y_FIREFIGHTER_2, null);

		} else {
			g.drawImage(image, xFirefighter2 + (int) (positie.getX()), Y_FIREFIGHTER_1 + (int) (positie.getY()),
					xFirefighter1 + (int) (positie.getX()), Y_FIREFIGHTER_2 + (int) (positie.getY()), Variabelen.SOURCE_X1,
					Variabelen.SOURCE_Y1, SOURCE_X_FIREFIGHTER_2, SOURCE_Y_FIREFIGHTER_2, null);
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setDifficulity(float difficulity) {
		speed = (float) Math.min(1.5 + 0.00035 * difficulity, 5);
	}

	public void reset(float difficulty) {
		xFirefighter1 = Variabelen.BREEDTE_SCHERM / 2 - BREEDTE_FIREFIGHTER / 2;
		xFirefighter2 = Variabelen.BREEDTE_SCHERM / 2 + BREEDTE_FIREFIGHTER / 2;
	}
}
