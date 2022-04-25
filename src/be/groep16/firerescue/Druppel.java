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
public class Druppel extends JPanel implements Entity {
	private Vector positie;
	Druppel() {
		positie = new Vector(Variabelen.XD,Variabelen.Y);
		
		File druppel = new File("Druppel.png");

		try {
			BufferedImage img = ImageIO.read(druppel);
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
