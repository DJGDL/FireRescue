package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rock implements Entity {
	private Vector positie;
	private BufferedImage image;

	Rock() {
		positie = new Vector(0, 0);

		File rock = new File("Steen.png");

		try {
			image = ImageIO.read(rock);
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
		g.drawImage(image, Variabelen.XRock1 + (int) (positie.getX()), Variabelen.YRock1 + (int) (positie.getY()),
				Variabelen.XRock2 + (int) (positie.getX()), Variabelen.YRock2 + (int) (positie.getY()), Variabelen.sourceX1,
				Variabelen.sourceY1, Variabelen.sourceXRock2, Variabelen.sourceYRock2, null);

	}
}
