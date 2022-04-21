package be.groep16.firerescue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fire {
	Fire() {
		try {
			JFrame f = new JFrame("Vuurbal");
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, Variabelen.XB, Variabelen.YB);
			File vuur = new File("brandweerman.png");
			System.out.println(vuur.exists());
			BufferedImage img = ImageIO.read(vuur);
			JLabel pic = new JLabel(new ImageIcon(img));
			panel.add(pic);
			f.add(panel);
			f.pack();
			f.setVisible(true);

		} catch (IOException e) {
		}
	}
}
