package be.groep16.firerescue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Rock extends JPanel implements Entity {
	private Vector positie;
	Rock() {
		positie = new Vector(Variabelen.XR, Variabelen.Y);

		File rock = new File("Steen.png");

		try {
			BufferedImage img = ImageIO.read(rock);
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
	public void onDraw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}
