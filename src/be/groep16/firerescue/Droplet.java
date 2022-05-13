package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Droplet implements Entity {
	private static BufferedImage image = null;
	
	public static final int BREEDTE_DROPLET = 33;
	public static final int HOOGTE_DROPLET = 33;

	private static final int Y_DROPLET_1 = -HOOGTE_DROPLET;
	private static final int Y_DROPLET_2 = 0;
	private static final int SOURCE_X_DROPLET_2 = 133;
	private static final int SOURCE_Y_DROPLET_2 = 132;

	private int xDroplet1 = 0;
	private int xDroplet2 = 0;
	private Vector positie;
	private float velocity;

	public Droplet() {
		reset(0);

		if (image == null) {
			// Loading new Droplet image
			URL droplet = getClass().getResource("Druppel.png");

			try {
				image = ImageIO.read(droplet);
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
		g.drawImage(image, xDroplet1 + (int) (positie.getX()), Y_DROPLET_1 + (int) (positie.getY()),
				xDroplet2 + (int) (positie.getX()), Y_DROPLET_2 + (int) (positie.getY()), Variabelen.SOURCE_X1,
				Variabelen.SOURCE_Y1, SOURCE_X_DROPLET_2, SOURCE_Y_DROPLET_2, null);

	}

	@Override
	public void reset(float difficulty) {
		xDroplet1 = Variabelen.RANDOM.nextInt(Variabelen.BREEDTE_SCHERM - BREEDTE_DROPLET);
		xDroplet2 = xDroplet1 + BREEDTE_DROPLET;

		positie = new Vector(0, 0);
		velocity = Math.min(1.5f + 0.000475f * difficulty, 11);
	}

	public boolean isDead() {
		return positie.getY() > Variabelen.HOOGTE_SCHERM + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(BREEDTE_DROPLET, HOOGTE_DROPLET);
		r.x = xDroplet1 + (int) positie.getX();
		r.y = Y_DROPLET_1 + (int) positie.getY();

		return r;
	}

}
