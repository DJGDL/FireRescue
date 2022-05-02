package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener, KeyListener {
	private Timer timer;
	private ArrayList<Entity> entities;
	private Firefighter player;
	private long prevTime;
	

	int count = 0;
	JLabel score;




	public Main() {
		entities = new ArrayList<>();
		addKeyListener(this);
		setFocusable(true);
		timer = new Timer(Variabelen.UPDATE_SPEED, this);
		timer.start();

		Building building = new Building();
		entities.add(building);
		Fire fire = new Fire();
		entities.add(fire);
		Droplet droplet = new Droplet();
		entities.add(droplet);
		Rock rock = new Rock();
		entities.add(rock);
		SmileDroplet smileDroplet = new SmileDroplet();
		entities.add(smileDroplet);
		player = new Firefighter();
		entities.add(player);

	}
		


	public static void main(String[] args) {
		JFrame frame = new JFrame();

		JLabel score = new JLabel("score:");
		JPanel mainPanel = new Main();
		frame.add(mainPanel);
		Border border = BorderFactory.createLineBorder(Color.black, 3);
		
		score.setBackground(Color.gray);
		score.setOpaque(true);
		score.setBorder(border);
		score.setHorizontalAlignment(JLabel.LEFT);
		score.setVerticalAlignment(JLabel.CENTER);
		score.setBounds(5, 5, 75, 35);
		
		frame.setMinimumSize(new Dimension(Variabelen.BreedteScherm, Variabelen.HoogteScherm));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainPanel.setPreferredSize(new Dimension(Variabelen.BreedteScherm, Variabelen.HoogteScherm));
		mainPanel.add(score);
		mainPanel.setLayout(null);

		frame.pack();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		long currTime = System.currentTimeMillis();
		long deltaTime = currTime - prevTime;
		prevTime = currTime;
		
		for (Entity entity : entities) {
			entity.onUpdate(deltaTime);
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Entity entity : entities) {
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
