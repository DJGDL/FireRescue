package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GoldenDroplet implements Entity {
	private static BufferedImage image = null;
	
	public static final int BREEDTE_GOLDEN_DROPLET = 33;
	public static final int HOOGTE_GOLDEN_DROPLET = 33;

	private static final int Y_GOLDEN_DROPLET_1 = -HOOGTE_GOLDEN_DROPLET;
	private static final int Y_GOLDEN_DROPLET_2 = 0;
	private static final int SOURCE_X_GOLDEN_DROPLET_2 = 260;
	private static final int SOURCE_Y_GOLDEN_DROPLET_2 = 280;

	private int xGoldenDroplet1 = 0;
	private int xGoldenDroplet2 = 0;
	private Vector positie;
	private float velocity;

	GoldenDroplet() {
		reset(0);

		if (image == null) {
			// Loading new GoldenDroplet image
			URL goldenDroplet = getClass().getResource("GoldenDroplet.png");

			try {
				image = ImageIO.read(goldenDroplet);
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
		g.drawImage(image, xGoldenDroplet1 + (int) (positie.getX()), Y_GOLDEN_DROPLET_1 + (int) (positie.getY()),
				xGoldenDroplet2 + (int) (positie.getX()), Y_GOLDEN_DROPLET_2 + (int) (positie.getY()), Variabelen.SOURCE_X1,
				Variabelen.SOURCE_Y1, SOURCE_X_GOLDEN_DROPLET_2, SOURCE_Y_GOLDEN_DROPLET_2, null);

	}

	@Override
	public void reset(float difficulty) {
		xGoldenDroplet1 = Variabelen.RANDOM.nextInt(Variabelen.BREEDTE_SCHERM - BREEDTE_GOLDEN_DROPLET);
		xGoldenDroplet2 = xGoldenDroplet1 + BREEDTE_GOLDEN_DROPLET;

		positie = new Vector(0, 0);
		velocity = Math.min(1.5f + 0.000475f * difficulty, 11);
	}

	@Override
	public boolean isDead() {
		return positie.getY() > Variabelen.HOOGTE_SCHERM + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(BREEDTE_GOLDEN_DROPLET, HOOGTE_GOLDEN_DROPLET);
		r.x = xGoldenDroplet1 + (int) positie.getX();
		r.y = Y_GOLDEN_DROPLET_1 + (int) positie.getY();

		return r;
	}
}
