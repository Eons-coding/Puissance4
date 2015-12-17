package be.ephec.p4;

import java.io.*;
import java.util.StringTokenizer;
/**
*
* @author  de Hemptinne Quentin, Dechamps Xavier, Barata Jorge
*/
public class Jeu {
	Grille plateau;		
	byte[][] matJeu;	
	int nbCoups;		
	boolean enCours;	
	boolean joueur;		
	int[] historique;	
	Options opts;		
	Computer ia;	
	boolean lock;  
	
	/**
	 * Cronstruction d'un nouvel objet Jeu avec ou sans options
	 * @param optTrue Booleen évaluant la présence d'options
	 */
	public Jeu(boolean optTrue) {
		if (optTrue)
			opts = new Options(this);
		nbCoups = 0;
		enCours = true;
	}

	/**
	 * Evalue la condition de victoire pour le joueur en cours
	 * @param joueur Booleen évaluant la présence d'un joueur
	 * @param ligneM Ligne correspondant à la position dans la matrice
	 * @param colM Colonne correspondant à la position dans la matrice
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean joueurGagne(boolean joueur, int ligneM, int colM) {  
		byte jVal = 1; 
		if (joueur)
			jVal = 2;
		
		if (horiGagne(jVal, ligneM, colM) || vertGagne(jVal, ligneM, colM) || diag1Gagne(jVal, ligneM, colM) || diag2Gagne(jVal, ligneM, colM))
			return true;
		
		return false;
	}
	
	/**
	 * Evalue la condition de victoire horizontale
	 * @param jVal Joueur en cours
	 * @param ligne Ligne correspondant à la position dans la grille
	 * @param col Colonne correspondant à la position dans la grille
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean horiGagne(byte jVal, int ligne, int col) {
		// Nombre de pions qui sont alignés les uns à la suite des autres
		int nbAlign = 0;  
		int colMin = col - 3;
		if (colMin <= 0)
			colMin = 0;
		
		int colMax = col + 3;
		if (colMax >= opts.getNbCol())
			colMax = opts.getNbCol() - 1;
		
		for (int i = colMin; i <= colMax; i++) {
			if (this.matJeu[ligne][i] == jVal)
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
	 * @param jVal Joueur en cours
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
		if (ligneMax >= opts.getNbLig())
			ligneMax = opts.getNbLig() - 1;
		
		for (int i = ligneMin; i <= ligneMax; i++) {
			if (this.matJeu[i][col] == jVal)
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
	 * @param jVal Joueur en cours
	 * @param ligne Ligne correspondant à la position dans la grille
	 * @param col Colonne correspondant à la position dans la grille
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean diag1Gagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		//Limites de l'évaluation (3 cases en bas à droite)
		int compteur = 0;
		while (ligneMax + 1 < opts.getNbLig() && colMax + 1 < opts.getNbCol() && compteur <= 2) {  
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
		
		ligneM = ligneMin;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
				
			ligneM++;
			colM++;
			
		} while (ligneM <= ligneMax && colM <= colMax);
		
		return false;
	}
	
	/**
	 * Evaluation de la condition de victoire de la diagonale en forme de slash
	 * @param jVal Joueur en cours
	 * @param ligne Ligne correspondant à la position dans la grille
	 * @param col Colonne correspondant à la position dans la grille
	 * @return True si la condition de victoire est remplie et False sinon
	 */
	public boolean diag2Gagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		//Limites de l'évaluation (3 cases en bas à gauche)
		int compteur = 0;
		while (ligneMax + 1 < opts.getNbLig() && colMin >= 1 && compteur <= 2) {  
			ligneMax++;
			colMin--;
			compteur++;   
		}
		
		//Limites de l'évaluation (3 cases en haut à droite)
		compteur = 0;
		while (ligneMin >= 1 && colMax + 1 < opts.getNbCol() && compteur <= 2) {  
			ligneMin--;
			colMax++;
			compteur++;   
		}
		
		ligneM = ligneMax;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
			
			ligneM--;
			colM++;
					
		} while (ligneM >= ligneMin && colM <= colMax);
		
		return false;
	}
	
	/**
	 * Traitement de l'action Jouer sélectionnée par le joueur 
	 * @param col Colonne sélectionnée pour être jouée
	 */
	public void jouer(int col) {
		boolean coupValable;	
		int ligne = 0;		
		
		coupValable = false;
			
		ligne = this.searchLine(col);
		coupValable = this.coupValable(ligne, col);
		
		if (coupValable)
			validerCoup(ligne, col);
		
	}

	/**
	 * Evaluation de la condition de jouabilité
	 * @param ligne Ligne à vérifier
	 * @param col Colonne à vérifier
	 */
	public void validerCoup(int ligne, int col) {
		
		if (!joueur) {
			this.matJeu[ligne - 1][col - 1] = 1;  
			Case c = (Case)plateau.pane.getComponent((opts.getNbCol()) * (ligne - 1) + (col - 1));
			c.modifierVal(1);
		} else {
			this.matJeu[ligne - 1][col - 1] = 2;  
			Case c = (Case)plateau.pane.getComponent((opts.getNbCol()) * (ligne - 1) + (col - 1));
			c.modifierVal(2);
		}

		boolean gagne = this.joueurGagne(joueur, ligne - 1, col - 1);
		if (gagne) {
			if (opts.resOn)
				networkPlay(col, false);
			enCours = false; 
			if (!joueur)
				Saisie.infoMsgOk("Le joueur 1 a gagné", "Partie terminée");
			else
				Saisie.infoMsgOk("Le joueur 2 a gagné", "Partie terminée");
		}
		
		nbCoups++;  
		if (nbCoups >= opts.getNbLig() * opts.getNbCol() && !gagne) {
			if (opts.resOn)
				networkPlay(col, false);
			Saisie.infoMsgOk("Aucun des 2 joueurs n'a su gagner. Partie nulle", "Partie nulle");
			enCours = false;  
		}
		
		historique[nbCoups - 1] = col;
		joueur = !joueur;
		if (joueur) {
			plateau.statusBar.setText("Le joueur 2 joue");
			plateau.statusBar.setIcon(plateau.pionV);
		}
		else {
			plateau.statusBar.setText("Le joueur 1 joue");
			plateau.statusBar.setIcon(plateau.pionR);
		}
		if (!enCours) {		
			int ok = Saisie.question_ouinon("La partie est termin�e, voulez-vous en faire une nouvelle ?", "Nouvelle partie");
			if (ok == 0)
				nouveauJeu();
		}
		else {
			if (opts.ia && joueur != opts.ordiCommence)
				this.ordiJoue();
			
			if (opts.resOn)
				networkPlay(col, true);
		}
			
				
	}
	/** Lancement d'une nouvelle partie grâce à l'objet Jeu*/
	public static void nouveauJeu() {
		Jeu j = new Jeu(true);
	}
	
	/**
	 * Evaluation de la validité du coup joué
	 * @param ligne Ligne à évaluer
	 * @param col Colonne à évaluer
	 * @return True si le coup est valide et False sinon
	 */
	public boolean coupValable(int ligne, int col) {
		
		if (ligne == -1) {
			Saisie.erreurMsgOk("Coup invalide : la colonne est remplie", "Coup invalide");
			return false;
		}
		
		if (!enCours) {
			Saisie.erreurMsgOk("La partie est terminée, vous ne pouvez plus jouer", "Erreur : partie terminée");
			return false;
		}
		
		if (lock) {
			Saisie.erreurMsgOk("Ce n'est pas à vous de jouer", "Tour adverse");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Recherche la ligne correspondant à la colonne sélectionnée
	 * @param col Colonne à évaluer 
	 * @return La ligne recherchée et -1 si la colonne est remplie et qu'aucune ligne n'a été trouvée 
	 */
	public int searchLine(int col) {
		for (int i = opts.getNbLig(); i >= 1; i--) {
			if (matJeu[i - 1][col - 1] == 0)
				return i;
		}
		return -1; 
	}
	
	/** M�thode lancée au tour de l'ordinateur*/
	public void ordiJoue() {
		plateau.statusBar.setText("L'ordinateur réfléchit : patientez...");
		plateau.repaint();
		ia.nbCoups = nbCoups;
		ia.joueurBase = joueur; 
		ia.matJeu = new byte[opts.getNbLig()][opts.getNbCol()];
		ia.matJeu2 = new byte[opts.getNbLig()][opts.getNbCol()];
		for (int i = 0; i < opts.getNbLig(); i++) {
			for (int j = 0; j < opts.getNbCol(); j++) {
				ia.matJeu[i][j] = matJeu[i][j];
				ia.matJeu2[i][j] = matJeu[i][j];
			}
		}	
		jouer(ia.ordiJoue(joueur));
	}

	/**
	 * Méthode qui synchronise les tours dans le réseau 
	 * @param col Colonne sélectionnée
	 * @param wait Token permettant de synchroniser les tours des joueurs
	 */
	public void networkPlay(int col, boolean wait) {
		
		if (!wait) {
			opts.sc.envoyerCoup(col); 
			opts.sc.closeSocket();
		}
		else if (opts.serveur && nbCoups % 2 == 1 || !opts.serveur && nbCoups % 2 == 0) {
			opts.sc.envoyerCoup(col); 
			lock = true;  
			NetworkThread nt = new NetworkThread(opts.sc, this);
			nt.start();
		}
	}
	
	/** Lancement du Puissance 4*/
	public static void main(String args[]) {
		Jeu partie = new Jeu(true);
	}
}
