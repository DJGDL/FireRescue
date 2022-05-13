package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Fire implements Entity {
	private static BufferedImage image;
	
	public static final int BREEDTE_FIRE = 33;
	public static final int HOOGTE_FIRE = 43;
	
	private static final int Y_FIRE_1 = -HOOGTE_FIRE;
	private static final int Y_FIRE_2 = 0;
	private static final int SOURCE_X_FIRE_2 = 548;
	private static final int SOURCE_Y_FIRE_2 = 720;

	private int xFire1 = 0;
	private int xFire2 = 0;
	private Vector positie;
	private float velocity;

	public Fire() {
		reset(0);

		if (image == null) {
			// Loading new fire image
			URL fire = getClass().getResource("Vuurbal.png");

			try {
				image = ImageIO.read(fire);
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
		g.drawImage(image, xFire1 + (int) (positie.getX()), Y_FIRE_1 + (int) (positie.getY()),
				xFire2 + (int) (positie.getX()), Y_FIRE_2 + (int) (positie.getY()), Variabelen.SOURCE_X1,
				Variabelen.SOURCE_Y1, SOURCE_X_FIRE_2, SOURCE_Y_FIRE_2, null);

	}

	@Override
	public void reset(float difficulty) {
		xFire1 = Variabelen.RANDOM.nextInt(Variabelen.BREEDTE_SCHERM - BREEDTE_FIRE);
		xFire2 = xFire1 + BREEDTE_FIRE;

		positie = new Vector(0, 0);
		velocity = Math.min(1.5f + 0.000525f * difficulty, 12);
	}

	@Override
	public boolean isDead() {
		return positie.getY() > Variabelen.HOOGTE_SCHERM + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(BREEDTE_FIRE / 3, (HOOGTE_FIRE * 3) / 5);
		r.x = xFire1 + BREEDTE_FIRE / 3;
		r.y = Y_FIRE_1 + (int) ((HOOGTE_FIRE * 2) / 5) + (int) positie.getY();

		return r;
	}
}
