package be.ephec.p4;

import java.io.*;
import java.net.*;

public class Client extends ServeurClient {
    
	private InetAddress adr;
	private Socket socket;
   
	/**
	 * Cr�ation d'une nouvelle instance Client
	 * @param adrServeur Adresse IP utilis�e lors de la partie en r�seau  
	 * @param jeu Objet Jeu de la partie en cours
	 */
	public Client(String adrServeur, Jeu jeu) {
	
		super(jeu);
		
		try {
			adr = InetAddress.getByName(adrServeur);
			socket = new Socket(adr, PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch(Exception e) {
			System.out.println("Serveur introuvable.");
			System.out.println("V�rifiez que celui-ci existe.");
			System.exit(-1);
		}
	}
	
	/** Fermeture de la connexion */
	void closeSocket() {
	
		try {
			socket.close();
		}
		catch (IOException e) {
			System.out.println("Erreur lors de la fermeture des sockets");
		}
	}
}
