package be.ephec.network;

import java.util.Arrays;

public class Board{
	int gameboard[][];
	int pawnLeft;
	
	Board (int height, int width){
		this.gameboard = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				gameboard[i][j] = 0;
				System.out.print(gameboard[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
public static void main(String[] args){
		Board test = new Board(5, 5);
		//test.PrintBoard();
		//System.out.println("Check si 0 present: "+checkColumn(test.gameboard[0]));
		test.addPawn(2, 1);
		test.addPawn(2, 2);
		test.addPawn(2, 1);
		test.addPawn(2, 2);
		test.addPawn(2, 1);
		test.addPawn(2, 2);
		test.addPawn(1, 1);
		test.addPawn(4, 2);
		test.addPawn(2, 1);
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
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return i;
			} 
		}
		return -1;
	}
	
	public boolean addPawn(int column, int playerId){
		int a = checkColumn(this.gameboard[column]);
		if (a != -1) {
			this.gameboard[column][a] = playerId;
			return true;
		}
		System.out.println("Erreur: Colonne "+column+" pleine");
		return false;
	}
	
	private int getPawnLeft() {
		return pawnLeft;
	}
	private void setPawnLeft(int pawnLeft) {
		this.pawnLeft = pawnLeft;
	}
	
	
	
}
