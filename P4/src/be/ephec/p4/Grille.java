package be.ephec.p4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Grille extends JFrame implements MouseListener, ActionListener, WindowListener {
	static int nbGrilles; // Contient le nombre de fenetres actuellement ouvertes
	
	JPanel global = new JPanel();
	
	JPanel pane = new JPanel();
	GridLayout gridLay;
	
	JToolBar bar = new JToolBar();
	
	ImageIcon pionR = new ImageIcon("pionR.png");
	ImageIcon pionV = new ImageIcon("pionV.png");
	
	JButton comput = new JButton("Jouer");
	
	JLabel statusBar;
    
	/**
	 * Construction de la grille de jeu
	 * @param nbRow Nombre de lignes à créer
	 * @param nbCol Nombre de colonnes à créer
	 * @param jeu Objet Jeu en cours
	 */
	public Grille(int nbRow, int nbCol, Jeu jeu) {
		super("Puissance 4");
		setSize(400, 300);
		setLocation(50, 50);
		
		global.setLayout(new BorderLayout());
		
		gridLay = new GridLayout(nbRow, nbCol, 0, 0);
		pane.setLayout(gridLay);
		
		makeToolBar();
		global.add(bar, "North");
		
		makeCells(nbRow, nbCol, jeu);
		global.add(pane, "Center");
		
		statusBar = new JLabel("Le joueur 1 joue", pionR, JLabel.LEFT);
		global.add(statusBar, "South");
		
		setContentPane(global);
		setVisible(false);
		
		addWindowListener(this);
		
		nbGrilles++;
		
	}
	
	/** Ajoute les boutons au menu et y associe leurs ActionListeners*/	
	public void makeToolBar() {
		comput.addActionListener(this);
		bar.add(comput);
	}
	
	/**
	 * Ajout des lignes et des colonnes à la grille de jeu	
	 * @param nbRow Nombre de lignes à créer
	 * @param nbCol Nombre de colonnes à créer
	 * @param jeu Objet Jeu en cours
	 */
	public void makeCells(int nbRow, int nbCol, Jeu jeu) {
		Case c;
		for (int i = 0; i < nbRow; i++) {
			for (int j = 0; j < nbCol; j++) {
				c = new Case(i + 1, j + 1, 0, jeu);
				c.addMouseListener(this);
				pane.add(c);
			}
		}	
	}
	
	public void mouseClicked(MouseEvent evt) {

	}
	
	/** Modification de la couleur de la case où le jeton sera joué*/
	public void mouseEntered(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		int col = src.getCol();
		int ligne = src.getJeu().searchLine(col);
		if (ligne != -1) {
			Case cc = (Case)src.getJeu().plateau.pane.getComponent((src.getJeu().opts.getNbCol()) * (ligne - 1) + (col - 1));
			cc.modifierBg(new Color(198, 198, 242));
			repaint();
		}
	}

	/** Modification de la couleur de la case où le jeton sera joué*/
	public void mouseExited(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		int col = src.getCol();
		int ligne = src.getJeu().searchLine(col);
		if (ligne != -1) {
			Case cc = (Case)src.getJeu().plateau.pane.getComponent((src.getJeu().opts.getNbCol()) * (ligne - 1) + (col - 1));
			cc.modifierBg(Color.white);
			repaint();
		}
	}
	
	/** Traitement de la colonne sélectionnée par le joueur*/
	public void mousePressed(MouseEvent evt) {
		Case src = (Case)evt.getSource();
		src.getJeu().jouer(src.getCol());
	}
	
	public void mouseReleased(MouseEvent evt) {
		
	}
	
	/** Traitement de l'action sélectionnée par le joueur*/
	public void actionPerformed(ActionEvent actionEvent) {
		JButton src = (JButton)actionEvent.getSource();
		if (src == comput) {
			Case c = (Case)this.pane.getComponent(0);
			c.getJeu().ordiJoue();
		}
		// Amélioration possible: UNDO
	}
	
	public void windowActivated(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowClosed(java.awt.event.WindowEvent windowEvent) {
	}
	
	/** Fermeture automatique si aucune partie en cours*/
	public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		nbGrilles--;
		if (nbGrilles == 0)
			System.exit(-1);
	}
	
	public void windowDeactivated(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowDeiconified(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowIconified(java.awt.event.WindowEvent windowEvent) {
	}
	
	public void windowOpened(java.awt.event.WindowEvent windowEvent) {
	}
	
}
