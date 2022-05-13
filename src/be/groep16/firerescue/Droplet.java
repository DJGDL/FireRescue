package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Droplet implements Entity {
	private static BufferedImage image = null;

	private static final int YDroplet1 = -Variabelen.HoogteDroplet;
	private static final int YDroplet2 = 0;
	private static final int sourceXDroplet2 = 133;
	private static final int sourceYDroplet2 = 132;

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
		g.drawImage(image, xDroplet1 + (int) (positie.getX()), YDroplet1 + (int) (positie.getY()),
				xDroplet2 + (int) (positie.getX()), YDroplet2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, sourceXDroplet2, sourceYDroplet2, null);

	}

	@Override
	public void reset(float difficulty) {
		xDroplet1 = Variabelen.RANDOM.nextInt(Variabelen.BreedteScherm - Variabelen.BreedteDroplet);
		xDroplet2 = xDroplet1 + Variabelen.BreedteDroplet;

		positie = new Vector(0, 0);
		velocity = Math.min(1.5f + 0.00055f * difficulty, 7);
	}

	public boolean isDead() {
		return positie.getY() > Variabelen.HoogteScherm + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(Variabelen.BreedteDroplet, Variabelen.HoogteDroplet);
		r.x = xDroplet1 + (int) positie.getX();
		r.y = YDroplet1 + (int) positie.getY();

		return r;
	}

}
