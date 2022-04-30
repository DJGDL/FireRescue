package be.groep16.firerescue;

import java.util.Random;
import java.util.Stack;

public class GameManager {
	private Stack<Droplet> activeDroplet;
	private Stack<Droplet> nonActiveDroplet;
	private Stack<Fire> activeFire;
	private Stack<Fire> nonActiveFire;
	private Stack<Rock> activeRock;
	private Stack<Rock> nonActiveRock;
	private Stack<SmileDroplet> activeSmileDroplet;
	private Stack<SmileDroplet> nonActiveSmileDroplet;
	private int object;
	
	
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
	private Fire getNewFire() {
		if (nonActiveFire.size() > 0) {
			Fire newFire = nonActiveFire.pop();
			activeFire.push(newFire);
			
			return newFire;
		} else {
			Fire newFire = new Fire();
			activeFire.push(newFire);
			
			return newFire;
		}
	}
	private Rock getNewRock() {
		if (nonActiveRock.size() > 0) {
			Rock newRock = nonActiveRock.pop();
			activeRock.push(newRock);
			
			return newRock;
		} else {
			Rock newRock = new Rock();
			activeRock.push(newRock);
			
			return newRock;
		}
	}
	private SmileDroplet getNewSmileDroplet() {
		if (nonActiveSmileDroplet.size() > 0) {
			SmileDroplet newSmileDroplet = nonActiveSmileDroplet.pop();
			activeSmileDroplet.push(newSmileDroplet);
			
			return newSmileDroplet;
		} else {
			SmileDroplet newSmileDroplet = new SmileDroplet();
			activeSmileDroplet.push(newSmileDroplet);
			
			return newSmileDroplet;
		}
	}
	public GameManager() {
		activeDroplet = new Stack<>();
		nonActiveDroplet = new Stack<>();
		activeFire = new Stack<>();
		nonActiveFire = new Stack<>();
		activeRock = new Stack<>();
		nonActiveRock = new Stack<>();
		activeSmileDroplet = new Stack<>();
		nonActiveSmileDroplet = new Stack<>();
	}
	
	public void onUpdate(long deltaTime) {
		Random rand = new Random();
		object = rand.nextInt()%10;
		if (object < 5)
			getNewRock();
		if (object >= 5 && object < 7)
			getNewFire();
		if (object >= 7 && object <9)
			getNewDroplet();
		if (object >= 9)
			getNewSmileDroplet();
		for (Droplet droplet: activeDroplet) {
			droplet.onUpdate(deltaTime);
		}
		for (Fire fire: activeFire) {
			fire.onUpdate(deltaTime);
		}
		for (Rock rock: activeRock) {
			rock.onUpdate(deltaTime);
		}
		for (SmileDroplet smileDroplet: activeSmileDroplet) {
			smileDroplet.onUpdate(deltaTime);
		}
	}
	
	public void onDraw() {
		//
	}
}
