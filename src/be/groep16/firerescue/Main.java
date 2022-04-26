package be.groep16.firerescue;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Timer timer;
	ArrayList<Entity> entities;
	
	public Main() {
		entities = new ArrayList<>();
		timer = new Timer(Variabelen.UPDATE_SPEED, this);
		timer.start();
		
		Druppel d = new Druppel();
		entities.add(d);
		add(d);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (Entity entity: entities) {
			entity.onUpdate(Variabelen.UPDATE_SPEED);
		}
		
		for (Entity entity: entities) {
			Rectangle bounds = entity.getComponent().getBounds();
			bounds.x = entity.getPosition().x();
			bounds.y = entity.getPosition().y();
			entity.getComponent().setBounds(bounds);
		}
	}

}
