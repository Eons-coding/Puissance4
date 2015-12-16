package be.ephec.network;

public class Pawn extends Coordinate {
	int playerId; //TODO passer la couleur via le Player

	Pawn(int x1, int y1, int player) {
		super(x1, y1);
		setplayerId(player);		
	}

	public int getplayerId() {
		return playerId;
	}

	public void setplayerId(int playerId) {
		this.playerId = playerId;
	}

}
