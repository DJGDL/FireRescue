package be.groep16.firerescue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Brandweerman {
	Brandweerman() 
	 {
	  try 
	  {
	   JFrame f = new JFrame("Gebouw");
	   JPanel panel = new JPanel();
	   panel.setBounds(0, 0, 57, 100);
	   BufferedImage img = ImageIO.read(new File("brandweerman.png"));
	   JLabel pic = new JLabel(new ImageIcon(img));
	   panel.add(pic);
	   f.add(panel);
	   f.setSize(57, 100);
	   f.setLayout(null);
	   f.setVisible(true);
	  } 
	  catch (IOException e) {}
	 }
	 public static void main(String args[]) 
	 {
	  new Brandweerman();
	 }
}
