package be.groep16.firerescue;

public record Vector(float x, float y) {
	
	public Vector add(Vector o) {
		return new Vector(this.x + o.x, this.y + o.y);
	}
	
	public Vector add(float x, float y) {
		return new Vector(this.x + x, this.y + y);
	}
}
