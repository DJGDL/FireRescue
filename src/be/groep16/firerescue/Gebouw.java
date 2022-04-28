package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gebouw implements Entity {
	private Vector positie;
	private BufferedImage image;

	Gebouw() {
		positie = new Vector(0, 0);

		File gebouw = new File("gebouw.png");

		try {
			image = ImageIO.read(gebouw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, 0);

	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, Variabelen.XG1 + (int) (positie.getX()), Variabelen.YG1 + (int) (positie.getY()),
				Variabelen.XG2 + (int) (positie.getX()), Variabelen.YG2 + (int) (positie.getY()), Variabelen.srcX1,
				Variabelen.srcY1, Variabelen.srcXG2, Variabelen.srcYG2, null);

	}
}
