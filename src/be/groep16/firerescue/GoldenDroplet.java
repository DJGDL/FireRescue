package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GoldenDroplet implements Entity {
	private static BufferedImage image = null;

	private static final int YSmileDroplet1 = -Variabelen.HoogteDroplet;
	private static final int YSmileDroplet2 = 0;
	private static final int sourceXSmileDroplet2 = 260;
	private static final int sourceYSmileDroplet2 = 280;

	private int xSmileDroplet1 = 0;
	private int xSmileDroplet2 = 0;
	private Vector positie;
	private float velocity;

	GoldenDroplet() {
		reset(0);

		if (image == null) {
			// File goldenDroplet = new File("GoldenDroplet.png");
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
		g.drawImage(image, xSmileDroplet1 + (int) (positie.getX()), YSmileDroplet1 + (int) (positie.getY()),
				xSmileDroplet2 + (int) (positie.getX()), YSmileDroplet2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, sourceXSmileDroplet2, sourceYSmileDroplet2, null);

	}

	@Override
	public void reset(float difficulty) {
		xSmileDroplet1 = Variabelen.RANDOM.nextInt(Variabelen.BreedteScherm - Variabelen.BreedteDroplet);
		xSmileDroplet2 = xSmileDroplet1 + Variabelen.BreedteDroplet;

		positie = new Vector(0, 0);
		velocity = Math.min(1.5f + 0.00055f * difficulty, 7);
	}

	@Override
	public boolean isDead() {
		return positie.getY() > Variabelen.HoogteScherm + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(Variabelen.BreedteDroplet, Variabelen.HoogteDroplet);
		r.x = xSmileDroplet1 + (int) positie.getX();
		r.y = YSmileDroplet1 + (int) positie.getY();

		return r;
	}
}
