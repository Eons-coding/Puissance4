package be.ephec.network;

public class Board{
	int gameboard[][];
	int pawnLeft, line, column;
	
	Board (int height, int width){
		int gameboard[][] = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				gameboard[i][j] = 0;
				//System.out.print(gameboard[i][j] + " ");
			}
			//System.out.println("");
		}
	}
	
public static void main(String[] args){
		Board test = new Board(5, 6);
		test.PrintBoard(test.gameboard);
	}
	
	public void PrintBoard(int[][] boardToPrint){
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				boardToPrint[i][j] = 0;
				System.out.print(boardToPrint[i][j] + " ");
			}
			System.out.println("");
		}
	}


	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	private int getPawnLeft() {
		return pawnLeft;
	}
	private void setPawnLeft(int pawnLeft) {
		this.pawnLeft = pawnLeft;
	}
	
	
	
}
