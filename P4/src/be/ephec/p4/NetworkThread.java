package be.ephec.p4;


public class NetworkThread extends Thread {
	
	Jeu jeu;
	ServeurClient sc;
	
	public NetworkThread(ServeurClient sc, Jeu j) {
		this.sc = sc;
		this.jeu = j;
	}
	
	public void run() {
		jeu.jouer(sc.attenteCoup());
	}
}
