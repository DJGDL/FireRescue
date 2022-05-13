package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Building implements Entity {
	private Vector positie;
	private BufferedImage image;

	private Rectangle r;

	Building() {
		positie = new Vector(0, 0);
		r = new Rectangle(0, 0);

		// Loading new building image
		URL building = getClass().getResource("gebouw.png");

		try {
			image = ImageIO.read(building);
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
		g.drawImage(image, Variabelen.XBuilding1 + (int) (positie.getX()),
				Variabelen.YBuilding1 + (int) (positie.getY()), Variabelen.XBuilding2 + (int) (positie.getX()),
				Variabelen.YBuilding2 + (int) (positie.getY()), Variabelen.sourceX1, Variabelen.sourceY1,
				Variabelen.sourceXBuilding2, Variabelen.sourceYBuilding2, null);

	}

	@Override
	public Rectangle getBoundingBox() {
		return r;
	}
}
