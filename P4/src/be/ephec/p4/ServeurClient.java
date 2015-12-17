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
		System.out.println("j'attends que l'autre joue");
		while(true) {
			try {
				entree = in.readLine();
				if (entree != null) {
					System.out.println("J'ai reçu un coup de la part de l'autre");
					jeu.lock = false; // on retire le lock pour pouvoir valider le coup reçu
					return Integer.parseInt(entree);
				}
			}
			catch(Exception e) {
				System.out.println("Pb dans l'attente de coup. Je quitte");
				System.exit(-1);
			}
		}
			
	}
	
	public void envoyerCoup(int col) {
		System.out.println("J'envoie le coup");
		out.println(col);
		System.out.println("C'est fait");
	}
		
	abstract void closeSocket();
	
}
