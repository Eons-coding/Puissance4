package be.ephec.p4;


import java.io.*;
import java.net.*;

abstract class ServeurClient {

	Jeu jeu;
	
	PrintWriter out;
	BufferedReader in;
	String entree;
	
	static final int PORT = 30000;
	
	public ServeurClient(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public int attenteCoup() {
		System.out.println("En attente du joueur adverse...");
		while(true) {
			try {
				entree = in.readLine();
				if (entree != null) {
					System.out.println("Coup adverse invalide");
					jeu.lock = false;
					return Integer.parseInt(entree);
				}
			}
			catch(Exception e) {
				System.out.println("Erreur lors de l'attente du coup adverse. Exit");
				System.exit(-1);
			}
		}
	}
	
	public void envoyerCoup(int col) {
		System.out.println("Le coup va etre transmit");
		out.println(col);
		System.out.println("Le coup a ete transmit");
	}
		
	abstract void closeSocket();
}
