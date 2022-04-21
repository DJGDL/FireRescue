package be.groep16.firerescue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gebouw{
	Gebouw() 
	 {
	  try 
	  {
	   JFrame f = new JFrame("Gebouw");
	   JPanel panel = new JPanel();
	   panel.setBounds(0, 0, Variabelen.XG, Variabelen.YG);
	   BufferedImage img = ImageIO.read(new File("gebouw.png"));
	   JLabel pic = new JLabel(new ImageIcon(img));
	   panel.add(pic);
	   f.add(panel);
	   f.setSize(Variabelen.XG, Variabelen.YG);
	   f.setLayout(null);
	   f.setVisible(true);
	  } 
	  catch (IOException e) {}
	 }
}
