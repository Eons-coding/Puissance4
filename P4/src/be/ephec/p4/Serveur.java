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
			System.out.println("J'attends qu'un client se connecte");
			jeu.plateau.setVisible(false); // cache le jeu
			clientSocket = ss.accept();
			System.out.println("Un client s'est connecté");
			jeu.plateau.setVisible(true); // montre la fenetre jeu ? TODO
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			// Envoie des paramètre de taille au client
			out.println(jeu.opts.getNbLig());
			out.println(jeu.opts.getNbCol());
			
		}
		catch(IOException e) {
			System.out.println("Oops, la création du serveur plante");
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
