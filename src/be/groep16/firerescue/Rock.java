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
		g.drawImage(image, Variabelen.XR1 + (int) (positie.x()), Variabelen.YR1 + (int) (positie.y()),
				Variabelen.XR2 + (int) (positie.x()), Variabelen.YR2 + (int) (positie.y()), Variabelen.srcX1,
				Variabelen.srcY1, Variabelen.srcXR2, Variabelen.srcYR2, null);

	}
}
