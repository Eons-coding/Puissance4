package be.ephec.network;

public class Board{
		
	Board (int height, int width){
		int[][] gameboard = new int[height][width];
		Pawn last;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				gameboard[i][j] = 0;
			}
		}
	}
	
public static void main(String[] args){
		Board test = new Board(5, 5);
		
		//test.PrintBoard();
		//System.out.println("Check si 0 present: "+checkColumn(test.gameboard[0]));
		test.addPawn(1, 1);
		test.addPawn(2, 2);
		test.addPawn(3, 3);
		test.addPawn(4, 4);
		test.addPawn(0, 9);
		test.addPawn(2, 2);
		test.addPawn(2, 5);
		test.addPawn(2, 6);
		test.addPawn(2, 7);
		test.addPawn(2, 8);
		test.PrintBoard();
		
	}
	
	public void PrintBoard(){
		System.out.println("Printing board:");
		for (int row = 0; row < this.gameboard.length; ++row) {
			for (int column = 0; column < this.gameboard[row].length; ++column) {
				System.out.print(this.gameboard[column][row] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * @author xavier
	 * Check si la colonne contient un espace vide.
	 * Pars du principe que l'indice 0 correspond au bas de la colonne.
	 * @param arr
	 * @return la position du dernier espace libre ou -1 si plein
	 */
	public static int checkColumn(int[] arr) {
		for (int i = (arr.length-1); i >= 0; i--) {
			if (arr[i] == 0) {
				return i;
			} 
		}
		return -1;
	}
	
	public boolean addPawn(int column, int playerId){
		int a = checkColumn(this.gameboard[column]);
		if (playerId != 1 && playerId != 2) {
			System.out.println("Id du joueur invalide");
			return false;
		}
		if (a != -1) {
			this.gameboard[column][a] = playerId;
			this.last.setX(column);
			this.last.setY(a);
			this.last.setplayerId(playerId);
			System.out.println("Joueur "+playerId+": Pion placé en "+column+":"+a);
			this.victory();
			return true;
		}
		System.out.println("Erreur: Colonne "+column+" pleine");
		return false;
	}
	
	
	/*
	 * Create Exception
	 * checkColumn
	 * checkRow
	 * checkDiagonal
	 */
	public void victory(){
		
	
	}
	
	
	
}
