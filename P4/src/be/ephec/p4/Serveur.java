package be.ephec.p4;

import java.io.*;
import java.net.*;


public class Serveur extends ServeurClient {
	
	ServerSocket ss;
	Socket clientSocket;

	
	public Serveur(Jeu jeu) {
		
		super(jeu);
		
		try {
			ss = new ServerSocket(PORT);
			System.out.println("En attente du client...");
			jeu.plateau.setVisible(false);
			clientSocket = ss.accept();
			System.out.println("Un client s'est connecte");
			jeu.plateau.setVisible(true);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// Envoie des parametres de taille au client
			out.println(jeu.opts.getNbLig());
			out.println(jeu.opts.getNbCol());
			
		}
		catch(IOException e) {
			System.out.println("Erreur lors de la creation du serveur");
		}
	}
		
	void closeSocket() {
	
		try {
			this.clientSocket.close();
			ss.close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la fermeture des sockets");
		}
		
	}
	
}
