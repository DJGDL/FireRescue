package be.groep16.firerescue;

import java.util.Stack;

public class GameManager {
	private Stack<Droplet> activeDroplet;
	private Stack<Droplet> nonActiveDroplet;
	
	
	private Droplet getNewDroplet() {
		if (nonActiveDroplet.size() > 0) {
			Droplet newDroplet = nonActiveDroplet.pop();
			activeDroplet.push(newDroplet);
			
			return newDroplet;
		} else {
			Droplet newDroplet = new Droplet();
			activeDroplet.push(newDroplet);
			
			return newDroplet;
		}
	}
	public GameManager() {
		activeDroplet = new Stack<>();
		nonActiveDroplet = new Stack<>();
	}
	
	public void onUpdate(long deltaTime) {
		// gamelogica
		for (Droplet droplet: activeDroplet) {
			droplet.onUpdate(deltaTime);
		}
	}
	
	public void onDraw() {
		//
	}
}
