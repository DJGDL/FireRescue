package be.groep16.firerescue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
	private Timer timer;
	private long prevTime = 0;
	private GameManager gameManager;
	
	JLabel Score;
	JLabel Lives;

	public Main() {
		gameManager = new GameManager();
		addKeyListener(gameManager);
		
		setFocusable(true);
		timer = new Timer(Variabelen.UPDATE_SPEED, this);
		timer.start();

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JLabel Score = new JLabel("score:");
		JLabel Lives = new JLabel("Lives:");
		JPanel mainPanel = new Main();
		frame.add(mainPanel);
		Border border = BorderFactory.createLineBorder(Color.black, 3);
		
		Score.setBackground(Color.gray);
		Score.setOpaque(true);
		Score.setBorder(border);
		Score.setHorizontalAlignment(JLabel.LEFT);
		Score.setVerticalAlignment(JLabel.CENTER);
		Score.setBounds(5, 5, 75, 35);
		
		Lives.setBackground(Color.gray);
		Lives.setOpaque(true);
		Lives.setBorder(border);
		Lives.setHorizontalAlignment(JLabel.LEFT);
		Lives.setVerticalAlignment(JLabel.CENTER);
		Lives.setBounds(Variabelen.BreedteScherm - 80, 5, 75, 35);
		
		frame.setMinimumSize(new Dimension(Variabelen.BreedteScherm, Variabelen.HoogteScherm));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainPanel.setPreferredSize(new Dimension(Variabelen.BreedteScherm, Variabelen.HoogteScherm));
		mainPanel.add(Score);
		mainPanel.add(Lives);
		mainPanel.setLayout(null);

		frame.pack();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		long currTime = System.currentTimeMillis();
		long deltaTime = prevTime == 0 ? 0 : currTime - prevTime;
		prevTime = currTime;
		
		gameManager.onUpdate(deltaTime);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		gameManager.onDraw(g);
		
	}
	

}
