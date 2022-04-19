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
	   JFrame f = new JFrame("Brandweerman");
	   JPanel panel = new JPanel();
	   panel.setBounds(0, 0, 57, 100);
	   File bwm = new File("brandweerman.png");
	   System.out.println(bwm.exists());
	   BufferedImage img = ImageIO.read(bwm);
	   JLabel pic = new JLabel(new ImageIcon(img));
	   panel.add(pic);
	   f.add(panel);
	   //f.setSize(57, 100);
	   //f.setLayout(null);
	   f.pack();
	   f.setVisible(true);
	  } 
	  catch (IOException e) {}
	 }
	 public static void main(String args[]) 
	 {
	  new Brandweerman();
	 }
}
