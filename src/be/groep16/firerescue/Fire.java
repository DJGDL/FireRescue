package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fire implements Entity {
	private Vector positie;
	private BufferedImage image;

	Fire() {
		positie = new Vector(0, 0);

		File fire = new File("Vuurbal.png");

		try {
			image = ImageIO.read(fire);
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
		g.drawImage(image, (int)(positie.x()), (int)(positie.y()), null);
	}
}
