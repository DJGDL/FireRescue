package be.groep16.firerescue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rock {
	Rock() {
		try {
			JFrame f = new JFrame("Steen");
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, Variabelen.XB, Variabelen.YB);
			File steen = new File("steen.png");
			System.out.println(steen.exists());
			BufferedImage img = ImageIO.read(steen);
			JLabel pic = new JLabel(new ImageIcon(img));
			panel.add(pic);
			f.add(panel);
			f.pack();
			f.setVisible(true);

		} catch (IOException e) {
		}
	}
}
