package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Droplet implements Entity {
	private static BufferedImage image = null;

	private Vector positie;

	public Droplet() {
		positie = new Vector(0, 0);

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
		g.drawImage(image, Variabelen.XDroplet1 + (int) (positie.getX()), Variabelen.YDroplet1 + (int) (positie.getY()),
				Variabelen.XDroplet2 + (int) (positie.getX()), Variabelen.YDroplet2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, Variabelen.sourceXDroplet2, Variabelen.sourceYDroplet2, null);

	}
}
