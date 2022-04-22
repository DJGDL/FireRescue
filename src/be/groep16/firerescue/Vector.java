package be.groep16.firerescue;

public record Vector(int x, int y) {
	
	public Vector add(Vector o) {
		return new Vector(this.x + o.x, this.y + o.y);
	}
	
	public Vector add(int x, int y) {
		return new Vector(this.x + x, this.y + y);
	}
}
