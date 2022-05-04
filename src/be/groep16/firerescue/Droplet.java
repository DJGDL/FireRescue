package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Droplet implements Entity {
	private static BufferedImage image = null;

	private static final int Y_DROPLET_1 = -Variabelen.HoogteDroplet;
	private static final int Y_DROPLET_2 = 0;
	private static final int sourceXDroplet2 = 133;
	private static final int sourceYDroplet2 = 132;

	private int xDroplet1 = 0;
	private int xDroplet2 = 0;
	private Vector positie;

	public Droplet() {
		reset();

		if (image == null) {
			File droplet = new File("Druppel.png");

			try {
				image = ImageIO.read(droplet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, 1);

	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, xDroplet1 + (int) (positie.getX()), Y_DROPLET_1 + (int) (positie.getY()),
				xDroplet2 + (int) (positie.getX()), Y_DROPLET_2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, sourceXDroplet2, sourceYDroplet2, null);

	}

	public void reset() {
		xDroplet1 = Variabelen.RANDOM.nextInt(Variabelen.BreedteScherm - Variabelen.BreedteDroplet);
		xDroplet2 = xDroplet1 + Variabelen.BreedteDroplet;

		positie = new Vector(0, 0);
	}

	public boolean isDead() {
		return positie.getY() > Variabelen.HoogteScherm + 1;
	}

	@Override
	public Rectangle getBoundingBox() {
		Rectangle r = new Rectangle(Variabelen.BreedteDroplet, Variabelen.HoogteDroplet);
		r.x = (int) positie.getX();
		r.y = (int) positie.getY();
		
		return r;
	}


}
