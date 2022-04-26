package be.groep16.firerescue;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fire extends JPanel implements Entity {
	private Vector positie;
	Fire() {
		positie = new Vector(Variabelen.XF, Variabelen.Y);

		File fire = new File("Vuurbal.png");

		try {
			BufferedImage img = ImageIO.read(fire);
			JLabel pic = new JLabel(new ImageIcon(img));
			add(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onUpdate(long deltaTime) {
		positie = positie.add(0, 1);

	}

	@Override
	public Component getComponent() {

		return this;
	}

	@Override
	public Vector getPosition() {

		return positie;
	}
}
