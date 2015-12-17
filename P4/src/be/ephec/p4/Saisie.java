package be.ephec.p4;

import javax.swing.*;

public class Saisie {
	/**
	 * Lecture d'un entier 
	 * @param message Texte affich� dans le contenu de la fen�tre
	 * @param titre Texte affich� dans l'en-t�te de la fen�tre
	 * @return L'entier lu par le fen�tre
	 */
	public static int lire_entier(String message, String titre) {
		String valeur;
	
		while (true) {	
			try {
				valeur = JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE);
				return Integer.parseInt(valeur);
			} catch (NumberFormatException e) {
				erreurMsgOk("Entier non-valide, reesayez", "Valeur incorrecte");
			}
		}
	}
	
	/**
	 * Affichage d'une question
	 * @param message Texte affich� dans le contenu de la fen�tre
	 * @param titre Texte affich� dans l'en-t�te de la fen�tre
	 * @return L'entier lu par le fen�tre
	 */
	public static int question_ouinon(String message, String titre) {
		int val = JOptionPane.showConfirmDialog(null, message, titre, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return val;
	}
	
	/**
	 * Affichage d'un message d'information
	 * @param message Texte affich� dans le contenu de la fen�tre
	 * @param titre Texte affich� dans l'en-t�te de la fen�tre
	 */
	public static void infoMsgOk(String message, String titre) {
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Affichage d'un message d'erreur 
	 * @param message Texte affich� dans le contenu de la fen�tre
	 * @param titre Texte affich� dans l'en-t�te de la fen�tre
	 */
	public static void erreurMsgOk(String message, String titre) {
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.ERROR_MESSAGE);
	}
	
}