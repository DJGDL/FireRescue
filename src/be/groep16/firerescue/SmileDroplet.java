package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SmileDroplet implements Entity {
	private Vector positie;
	private BufferedImage image;

	SmileDroplet() {
		positie = new Vector(0, 0);

		File smileDroplet = new File("Smile_Druppel.png");

		try {
			image = ImageIO.read(smileDroplet);
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
		g.drawImage(image, Variabelen.XSmileDroplet1 + (int) (positie.getX()), Variabelen.YSmileDroplet1 + (int) (positie.getY()),
				Variabelen.XSmileDroplet2 + (int) (positie.getX()), Variabelen.YSmileDroplet2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, Variabelen.sourceXSmileDroplet2, Variabelen.sourceYSmileDroplet2, null);

	}
}
