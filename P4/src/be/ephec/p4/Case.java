package be.ephec.p4;
import javax.swing.*;
import java.awt.*;

public class Case extends JPanel {
	private int val;
	private int ligne;
	private int col;
	private Jeu jeu;
	private Color bgCol;
	
	/** 
	 * Construit/Instencie un nouvel objet Case
	 * @param ligne Nombre de lignes du plateau de jeu
	 * @param col 	Nombre de colonnes du plateau de jeu
	 * @param a		Valeur représentant le joueur en cours
	 * @param j		Objet Jeu de la partie en cours
	 */
	public Case(int ligne, int col, int a, Jeu j) {
		super();
		this.val = a;
		this.ligne = ligne;
		this.setCol(col);
		this.setJeu(j);
		this.bgCol = Color.white;
	}
	
	public void modifierVal(int a) {
		this.val = a;
		this.bgCol = Color.white;
		repaint();
	}
	
	/** Modifie la couleur de l'arrière-plan du plateau de jeu*/
	public void modifierBg(Color c) {
		this.bgCol = c;
		repaint();
	}
	
	/** Création des images qui représenteront les jetons joués*/
	public void paintComponent(Graphics comp) {
		Graphics2D comp2D = (Graphics2D)comp;
		comp2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		
		comp2D.setColor(bgCol);
		comp2D.fillRect(0, 0, getWidth(), getHeight());
		comp2D.setColor(Color.black);
		comp2D.drawRect(0, 0, getWidth(), getHeight());
                
		if (val != 0) {
			if (val == 1)
				comp2D.setColor(Color.red);
			else
				comp2D.setColor(Color.green);
			comp2D.fillOval(3, 3, getWidth() - 4, getHeight() - 4);
		}
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
