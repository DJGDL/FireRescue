package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SmileDruppel implements Entity {
	private Vector positie;
	private BufferedImage image;

	SmileDruppel() {
		positie = new Vector(0, 0);

		File smileDruppel = new File("Smile_Druppel.png");

		try {
			image = ImageIO.read(smileDruppel);
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
		g.drawImage(image, Variabelen.XSD1 + (int) (positie.getX()), Variabelen.YSD1 + (int) (positie.getY()),
				Variabelen.XSD2 + (int) (positie.getX()), Variabelen.YSD2 + (int) (positie.getY()), Variabelen.srcX1,
				Variabelen.srcY1, Variabelen.srcXSD2, Variabelen.srcYSD2, null);

	}
}
