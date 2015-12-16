package be.ephec.network;

public class Coordinate {
	int x;
	int y;
	
	Coordinate(int x1, int y1){
		setX(x1);
		setY(y1);
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
