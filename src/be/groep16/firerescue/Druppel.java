package be.groep16.firerescue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Druppel {
	Druppel() {
		try {
			JFrame f = new JFrame("Druppel");
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, Variabelen.XB, Variabelen.YB);
			File druppel = new File("Druppel.png");
			System.out.println(druppel.exists());
			BufferedImage img = ImageIO.read(druppel);
			JLabel pic = new JLabel(new ImageIcon(img));
			panel.add(pic);
			f.add(panel);
			f.pack();
			f.setVisible(true);

		} catch (IOException e) {
		}
	}
}
