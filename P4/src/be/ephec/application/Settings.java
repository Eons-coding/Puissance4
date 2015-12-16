package be.ephec.application;

public class Settings {
	/**
	 * C'est le numéro de port par défaut que l'on essaie d'ouvrir
	 */
	public static final int DEFAULT_PORT_NUMBER = 32900;
	public static final String SERVER_IP = "127.0.0.1";
	
	public static final int HAUTEUR_JFRAME_ACCUEIL_CLIENT = 201;
	public static final int HAUTEUR_JFRAME_ACCUEIL_SERVEUR = 400;
	public static final int LARGEUR_JFRAME_ACCUEIL_CLIENT = 300;
	public static final int LARGEUR_JFRAME_ACCUEIL_SERVEUR = 400;
	
	public static final int HAUTEUR_JFRAME_PINC_CLIENT = 200;
	public static final int HAUTEUR_JFRAME_PRINC_SERVEUR = 800;
	public static final int LARGEUR_JFRAME_PINC_CLIENT = LARGEUR_JFRAME_ACCUEIL_CLIENT-1;
	public static final int LARGEUR_JFRAME_PRINC_SERVEUR = LARGEUR_JFRAME_ACCUEIL_SERVEUR-1;
	
	public static final int COIN_SUP_GAUCHE_CLIENT_X = LARGEUR_JFRAME_ACCUEIL_SERVEUR;
	public static final int COIN_SUP_GAUCHE_CLIENT_Y = 0;
	public static final int COIN_SUP_GAUCHE_SERVEUR_X = 0;
	public static final int COIN_SUP_GAUCHE_SERVEUR_Y = 0;
	
	public static final int NB_CLIENTS = 1;
	
	public static final int NB_COLONNES_AFFICHAGE = 4;
}



