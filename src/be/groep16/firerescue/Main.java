package be.groep16.firerescue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener, KeyListener {
	private Timer timer;
	private ArrayList<Entity> entities;
	private Brandweerman player;
	
	public Main() {
		entities = new ArrayList<>();
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(Variabelen.UPDATE_SPEED, this);
		timer.start();
		
		Gebouw gebouw = new Gebouw();
		entities.add(gebouw);
		Fire f = new Fire();
		entities.add(f);
		Druppel d = new Druppel();
		entities.add(d);
		Rock r = new Rock();
		entities.add(r);
		SmileDruppel sd = new SmileDruppel();
		entities.add(sd);
		player = new Brandweerman();
		entities.add(player);
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Main());
		
		frame.setMinimumSize(new Dimension(Variabelen.BScherm, Variabelen.HScherm));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Entity entity: entities) {
			entity.onUpdate(Variabelen.UPDATE_SPEED);
		}
		
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Entity entity: entities) {
			entity.onDraw(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		player.onKeyDown(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.onKeyUp(e.getKeyCode());
	}
	
	

}
