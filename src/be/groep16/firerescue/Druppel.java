package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Druppel implements Entity {
	private Vector positie;
	private BufferedImage image;

	Druppel() {
		positie = new Vector(0, 0);

		File druppel = new File("Druppel.png");

		try {
			image = ImageIO.read(druppel);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, 1);

	}

	@Override
	public void onDraw(Graphics g) {
		g.drawImage(image, Variabelen.XD1 + (int) (positie.x()), Variabelen.YD1 + (int) (positie.y()),
				Variabelen.XD2 + (int) (positie.x()), Variabelen.YD2 + (int) (positie.y()), Variabelen.srcX1,
				Variabelen.srcY1, Variabelen.srcXD2, Variabelen.srcYD2, null);

	}
}
