package be.ephec.p4;

class Computer {

	int p;
	int p2;
	byte[][] matJeu;
	byte[][] matJeu2;
	//int colResult;
	int nbCoups;
	boolean joueurBase; // cf commentaire de PerdGagne
	boolean[] forbiddenCols = new boolean[7];

	/**
	 * Construit un objet Computer
	 * @param niveau Difficulté
	 */
	public Computer(int niveau) {
		this.p = 2 * niveau;
		this.p2 = niveau;
	}
	
	/**
	 * Fait jouer l'ordinateur
	 * @param joueur Booleen représentant si c'est un joueur ou un ordinateur qui joue
	 * @return Colonne que l'ordinateur a jouée
	 */
	public int ordiJoue(boolean joueur) {
	
		forbiddenCols = new boolean[7];
		int ligne;
		int col;
		int max = -5001;
		int res = 0;
		byte jVal = 1; // Variable contenant la valeur du joueur
		if (joueur)
			jVal = 2;
		
		nbCoups++;
		for (col = 0; col < matJeu[0].length; col++) {
			ligne = searchLine(col);
			if (ligne != -1) {
				if (nbCoups == (matJeu.length * matJeu[0].length) - 1)
					return col + 1; // c'est la seule colonne que l'on peut jouer
				
				matJeu[ligne][col] = jVal;
				
				res = perdGagne(!joueurBase, p - 1);
				if (res == 1) {
					Saisie.infoMsgOk("Beeeee", "titr");
					return col + 1;					
				}
				if (res == -1) //ENREGISTRER LE COUP
					forbiddenCols[col] = true;
				
				matJeu[ligne][col] = 0;  // UNDO
			}
		}
		nbCoups--;
		
		//TODO TESTING
		int colResult = -2; // POUR TESTER
		for (col = 0; col < matJeu[0].length; col++) {
			ligne = searchLine(col);
			if (ligne != -1) {
				
				matJeu[ligne][col] = jVal;
				nbCoups++;
				res = miniMax(!joueurBase, p2 - 1);
				System.out.println(res);
				if (res > max) {
					max = res;
					colResult = col;
				}
				matJeu[ligne][col] = 0;  // UNDO
				nbCoups--;
				
			}
		}
		
		System.out.println("-----");
		return colResult + 1;
		
	}

	
	/** 
	 * Evalue le statut de victoire pour la partie
	 * @param joueur Booleen évaluant la présence d'un joueur
	 * @param profondeur
	 * @return 0 si aucun des deux joueurs n'est gagnant et 1 ou -1 selon le joueur qui a gagné 
	 */
	public int perdGagne(boolean joueur, int profondeur) {
		
		int ligne;
		int col;
		int minMax;
		if (joueur == joueurBase)
			minMax = -1;  // on part du minimum
		else
			minMax = 1;
		byte jVal = 1; // Variable contenant la valeur du joueur
		if (joueur)
			jVal = 2;
		
		if (profondeur == 0 || nbCoups == (matJeu.length * matJeu[0].length) - 1) {
		
			for (col = 0; col < matJeu[0].length; col++) {
				ligne = searchLine(col);
				if (ligne != -1) {
					matJeu[ligne][col] = jVal;
					nbCoups++;
					if (joueurGagne(jVal, ligne, col)) {
						
						matJeu[ligne][col] = 0;  // UNDO
						nbCoups--;
						if (joueur == joueurBase)
							return 1;
						else
							return -1;
						
					}
					matJeu[ligne][col] = 0;  // UNDO
					nbCoups--;
					
				}
			}
			return 0;
		
		} else {
		
			for (col = 0; col < matJeu[0].length; col++) {
				ligne = searchLine(col);
				if (ligne != -1) {
					matJeu[ligne][col] = jVal;
					nbCoups++;
					if (joueurGagne(jVal, ligne, col)) {
						
						matJeu[ligne][col] = 0;  // UNDO
						nbCoups--;
						if (joueur == joueurBase)
							return 1;
						else
							return -1;  //peut-Ãªtre enregistrer ce coup-lÃ 
						
					}
					int v = perdGagne(!joueur, profondeur - 1);
					matJeu[ligne][col] = 0;  // UNDO
					nbCoups--;
					
					if (joueur == joueurBase && v == 1)
						return 1;
					if (joueur != joueurBase && v == -1)
						return -1;
					
				}
			}
			//return minMax;
			return 0;
			
		}

	}
	
	/**
	 * 
	 * @param joueur
	 * @param profondeur
	 * @return
	 */
	public int miniMax(boolean joueur, int profondeur) {
		int val = -5001;
		if (joueur != joueurBase)
			val = - val;
		int note;
		int ligne;
		int col;
		// Variable contenant la valeur du joueur
		byte jVal = 1;
		if (joueur)
			jVal = 2;
		
		if (profondeur == 0 || nbCoups == matJeu.length * matJeu[0].length) {
			return evalGrille(jVal);
		} else {  // profondeur = 0, on est arrivÃ© au bout de l'arbre
			
			for (col = 0; col < matJeu[0].length; col++) {
				ligne = searchLine(col);
				if (ligne != -1 && !forbiddenCols[col]) {
					matJeu[ligne][col] = jVal;
					nbCoups++;
					boolean g = joueurGagne(jVal, ligne, col);
					if (!g)
						note = miniMax(!joueur, profondeur - 1);
					else {
						if (joueur == joueurBase)
							note = 5000;
						else
							note = -5000;
					}
					matJeu[ligne][col] = 0;
					nbCoups--;
					if (joueur == joueurBase && note > val)
						val = note;
					if (joueur != joueurBase && note < val)
						val = note;
					
				}
			}
			return val;
			
		}
		
	}
	
	/**
	 * Evalue la position sur la grille
	 * @param joueur Joueur en cours
	 * @return Valeur de la position 
	 */
	public int evalGrille(byte joueur) {
		int valeur = 0;
		for (int row = 0; row < matJeu.length; row++) {
			for (int col = 0; col < matJeu[0].length; col++) {
				if (matJeu[row][col] == 0)
					valeur += evalCase(row, col, joueur);
			}
		}
		return valeur;
	}
	
	/**
	 * Evalue la condition de victoire sur base de la case 
	 * @param row Ligne à évaluer
	 * @param col Colonne à évaluer
	 * @param joueur Joueur en cours
	 * @return 
	 */
	public int evalCase(int row, int col, byte joueur) {
		int valeur;
		valeur = nbAlignVertic(row, col, joueur)
			+ nbAlignHoriz(row, col, joueur)
			+ nbAlignSlash(row, col, joueur)
			+ nbAlignAntiSlash(row, col, joueur);
		
		int tmp = (- joueur) + 3; // changement de joueur : (-1)*(joueur - 2) + 1
		byte otherJ = (byte)tmp;
		valeur = valeur
			- nbAlignVertic(row, col, otherJ)
			- nbAlignHoriz(row, col, otherJ)
			- nbAlignSlash(row, col, otherJ)
			- nbAlignAntiSlash(row, col, otherJ);
		
		double d = Math.pow(2, matJeu.length - row);
		valeur = valeur * (int)Math.floor(d);
		
		return valeur;
	}
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param joueur
	 * @return
	 */
	public int nbAlignVertic(int row, int col, byte joueur) {
		int val = 0;
		boolean b = true;
		while (row + 1 < matJeu.length && b) {
			row++;
			if (matJeu[row][col] == joueur)
				val++;
			else
				b = false;
		}
		val = modifierVal(val);
		return val;
	}
	
	public int nbAlignHoriz(int row, int col, byte joueur) {
		int val = 0;
		int c = col;
		boolean b = true;
		while (c - 1 >= 0 && b) {
			c--;
			if (matJeu[row][c] == joueur)
				val++;
			else
				b = false;
		}
		
		b = true;
		c = col;
		while (c + 1 < matJeu[0].length && b) {
			c++;
			if (matJeu[row][c] == joueur)
				val++;
			else
				b = false;
		}
		val = modifierVal(val);
		return val;
		
	}
	
	public int nbAlignSlash(int row, int col, byte joueur) {
		int val = 0;
		int c = col;
		int r = row;
		boolean b = true;
		while (c - 1 >= 0 && r + 1 < matJeu.length && b) {
			c--;
			r++;
			if (matJeu[r][c] == joueur)
				val++;
			else
				b = false;
		}
		
		b = true;
		c = col;
		r = row;
		while (c + 1 < matJeu[0].length && r - 1 >= 0 && b) {
			c++;
			r--;
			if (matJeu[r][c] == joueur)
				val++;
			else
				b = false;
		}
		val = modifierVal(val);
		return val;
	}
	
	public int nbAlignAntiSlash(int row, int col, byte joueur) {
		int val = 0;
		int c = col;
		int r = row;
		boolean b = true;
		while (c + 1 < matJeu[0].length && r + 1 < matJeu.length && b) {
			c++;
			r++;
			if (matJeu[r][c] == joueur)
				val++;
			else
				b = false;
		}
		
		b = true;
		c = col;
		r = row;
		while (c - 1 >= 0 && r - 1 >= 0 && b) {
			c--;
			r--;
			if (matJeu[r][c] == joueur)
				val++;
			else
				b = false;
		}
		val = modifierVal(val);
		return val;
	}
	
	public static int modifierVal(int a) {
		switch(a) {
			case 0: return 0;
			case 1: return 4;
			case 2: return 10;
			case 3: return 50;
			default: return 60;
		}
	}
	
	/**
	 * Evalue la condition de victoire pour le joueur en cours
	 * @param joueur Joueur en cours
	 * @param ligneM Ligne correspondant à la position dans la matrice
	 * @param colM Colonne correspondant à la position dans la matrice
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean joueurGagne(byte joueur, int ligneM, int colM) {
		if (horiGagne(joueur, ligneM, colM) || vertGagne(joueur, ligneM, colM) || diag1Gagne(joueur, ligneM, colM) || diag2Gagne(joueur, ligneM, colM))
			return true;
		
		return false;
	}
	
	/**
	 * Evalue la condition de victoire horizontale
	 * @param jVal Valeur du joueur
	 * @param ligneM Ligne correspondant à la position dans la matrice
	 * @param colM Colonne correspondant à la position dans la matrice
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean horiGagne(byte jVal, int ligneM, int colM) {
		// Nombre de pions qui sont alignés les uns à la suite des autres
		int nbAlign = 0;
		int colMin = colM - 3;
		if (colMin <= 0)
			colMin = 0;
		
		int colMax = colM + 3;
		if (colMax >= matJeu[0].length)
			colMax = matJeu[0].length - 1;
		
		for (int i = colMin; i <= colMax; i++) {
			if (matJeu[ligneM][i] == jVal)
				nbAlign++;
			else
				nbAlign = 0;

			if (nbAlign == 4)
				return true;
		}
		return false;
	}
	
	/**
	 * Evalue la condition de victoire verticale
	 * @param jVal Valeur du joueur
	 * @param ligne Ligne correspondant à la position dans la grille
	 * @param col Colonne correspondant à la position dans la grille
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean vertGagne(byte jVal, int ligne, int col) {
		// Nombre de pions qui sont alignés les uns à la suite des autres
		int nbAlign = 0;
		int ligneMin = ligne - 3;
		if (ligneMin <= 0)
			ligneMin = 0;
		
		int ligneMax = ligne + 3;
		if (ligneMax >= matJeu.length)
			ligneMax = matJeu.length - 1;
		
		for (int i = ligneMin; i <= ligneMax; i++) {
			if (matJeu[i][col] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
		
			if (nbAlign == 4)
				return true;
		}
		return false;
	}
	
	/**
	 * Evaluation de la condition de victoire de la diagonale en forme de back-slash
	 * @param jVal Valeur du joueur
	 * @param ligne Ligne correspondant à la position dans la grille
	 * @param col Colonne correspondant à la position dans la grille
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean diag1Gagne(byte jVal, int ligne, int col) {
		int nbAlign = 0;
		int ligneMin = ligne;
		int ligneMax = ligne;
		int colMin = col;
		int colMax = col;
		
		//Limites de l'évaluation (3 cases en bas à droite)
		int compteur = 0;
		while (ligneMax + 1 < matJeu.length && colMax + 1 < matJeu[0].length && compteur <= 2) {
			ligneMax++;
			colMax++;
			compteur++;
		}
		
		//Limites de l'évaluation (3 cases en haut à gauche)
		compteur = 0;
		while (ligneMin >= 1 && colMin >= 1 && compteur <= 2) {
			ligneMin--;
			colMin--;
			compteur++;
		}
		
		ligne = ligneMin;
		col = colMin;
		do {
			if (matJeu[ligne][col] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
				
			ligne++;
			col++;
			
		} while (ligne <= ligneMax && col <= colMax);
		
		return false;
	}
	
	/**
	 * Evaluation de la condition de victoire de la diagonale en forme de slash
	 * @param jVal Valeur du joueur
	 * @param ligne Ligne correspondant à la position dans la grille
	 * @param col Colonne correspondant à la position dans la grille
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean diag2Gagne(byte jVal, int ligne, int col) {
		int nbAlign = 0;
		int ligneMin = ligne;
		int ligneMax = ligne;
		int colMin = col;
		int colMax = col;
		
		//Limites de l'évaluation (3 cases en bas à gauche)
		int compteur = 0;
		while (ligneMax + 1 < matJeu.length && colMin >= 1 && compteur <= 2) {
			ligneMax++;
			colMin--;
			compteur++;
		}
		
		//Limites de l'évaluation (3 cases en haut à droite)
		compteur = 0;
		while (ligneMin >= 1 && colMax + 1 < matJeu[0].length && compteur <= 2) {
			ligneMin--;
			colMax++;
			compteur++;
		}
		
		ligne = ligneMax;
		col = colMin;
		do {
			if (matJeu[ligne][col] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
			
			ligne--;
			col++;
					
		} while (ligne >= ligneMin && col <= colMax);
		
		return false;
	}
	
	/**
	 * Recherche la ligne correspondant à la colonne sélectionnée
	 * @param col Colonne à évaluer 
	 * @return La ligne recherchée et -1 si la colonne est remplie et qu'aucune ligne n'a été trouvée 
	 */
	public int searchLine(int col) {
		for (int i = matJeu.length - 1; i >= 0; i--) {
			if (matJeu[i][col] == 0)
				return i;
		}
		return -1;
	}

}
