package be.groep16.firerescue;

public class Vector {
	private float x;
	private float y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public Vector add(Vector o) {
		return new Vector(this.x + o.x, this.y + o.y);
	}
	
	public Vector add(float x, float y) {
		return new Vector(this.x + x, this.y + y);
	}
}
