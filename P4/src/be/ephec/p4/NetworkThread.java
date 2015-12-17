package be.ephec.p4;


public class NetworkThread extends Thread {
	
	Jeu jeu;
	ServeurClient sc;
	
	/**
	 * Cr�ation du Thread pour le jeu en r�seau
	 * @param sc Objet ServeurClient pour la communication sur le r�seau
	 * @param j Objet Jeu en cours
	 */
	public NetworkThread(ServeurClient sc, Jeu j) {
		this.sc = sc;
		this.jeu = j;
	}
	
	/** Lancement du Thread*/
	public void run() {
		jeu.jouer(sc.attenteCoup());
	}
}
