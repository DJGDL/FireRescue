package be.groep16.firerescue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LinePanel extends JPanel implements ActionListener
{
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        f.setSize(500,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setTitle("Brandweerman Sam");

        f.setLocation(100, 100); //standaard in de hoek van het scherm
        JPanel hoofdpaneel = new LinePanel();
        f.add(hoofdpaneel);
        f.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}