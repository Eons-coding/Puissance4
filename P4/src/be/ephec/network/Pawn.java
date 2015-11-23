package be.ephec.network;

public class Pawn extends Coordinate {
	int color; //TODO passer la couleur via le Player

	Pawn(int x1, int y1, int color1) {
		super(x1, y1);
		setColor(color1);		
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
