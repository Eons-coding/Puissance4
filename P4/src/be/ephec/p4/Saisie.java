package be.ephec.p4;

import javax.swing.*;

public class Saisie {
	public static int lire_entier(String message, String titre) {
	
		String valeur;
	
		while (true) {	
			try {
				valeur = JOptionPane.showInputDialog(null, message, titre, JOptionPane.QUESTION_MESSAGE);
				return Integer.parseInt(valeur);
			} catch (NumberFormatException e) {
				erreurMsgOk("Erreur : entrez un entier", "Erreur");
			}
		}

	}
	
	public static int question_ouinon(String message, String titre) {
		
		int val = JOptionPane.showConfirmDialog(null, message, titre, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return val;
		
	}
	
	public static void infoMsgOk(String message, String titre) {
		
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
	public static void erreurMsgOk(String message, String titre) {
		
		JOptionPane.showMessageDialog(null, message, titre, JOptionPane.ERROR_MESSAGE);
		
	}
	
}