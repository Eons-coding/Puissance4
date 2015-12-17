package be.ephec.p4;

public class Options {
	private int nbLig;			
	private int nbCol;			
	boolean ia = false;	
	boolean ordiCommence = false;
	boolean resOn = false;
	boolean serveur = false;
	ServeurClient sc;
	Jeu jeu;
	
	/**
	 * Construction de l'objet Options sans options particuliares
	 * @param j Objet Jeu en cours
	 */
	public Options(Jeu j) {
		
		this.jeu = j;
		OptionsGUI optionsGUI = new OptionsGUI(this);
	}
	
	/**
	 * Construction de l'objet Options avec options
	 * @param nbLig Nombre de lignes a creer
	 * @param nbCol Nombre de colonnes a creer
	 * @param jeu Objet Jeu en cours
	 */
	public Options(int nbLig, int nbCol, Jeu jeu) {
		this.jeu = jeu;
		setSize(nbLig, nbCol, true);
	}
	
	/**
	 * Mise en place de la taille du plateau de jeu
	 * @param nbLig Nombre de lignes a creer
	 * @param nbCol Nombre de colonnes a creer
	 * @param initSize Booleen permettant la creation du plateau de jeu
	 */
	public void setSize(int nbLig, int nbCol, boolean initSize) {
		this.nbLig = nbLig;
		this.nbCol = nbCol;
		if (initSize)
			initSize(nbLig, nbCol);
	}
	
	/**
	 * Creation du plateau de jeu
	 * @param nbLig Nombre de ligne a creer
	 * @param nbCol Nombre de colonnes a creer
	 */
	public void initSize(int nbLig, int nbCol) {
		jeu.plateau = new Grille(nbLig, nbCol, jeu);
		jeu.plateau.setVisible(true);
		jeu.matJeu = new byte[nbLig][nbCol];
		jeu.historique = new int[nbLig * nbCol];
	}
	
	/**
	 * Initialisation de l'IA adverse
	 * @param ia Booleen avaluant la presence d'un ordinateur en tant qu'adversaire 
	 * @param ordiCommence Booleen avaluant si l'ordinateur commence en premier
	 * @param diff 
	 */
	public void initOrdi(boolean ia, boolean ordiCommence, int diff) {
		this.ia = ia;
		this.ordiCommence = ordiCommence;
		jeu.ia = new Computer(diff); 
		if (ordiCommence)
			jeu.ordiJoue();
	}
	
	/**
	 * Initialisation du raseau
	 * @param serveur Booleen avaluant la presence d'un serveur
	 * @param ip Adresse IP liee au serveur que l'on souhaite atteindre
	 */
	public void initReseau(boolean serveur, String ip) {
		try {
			resOn = true;
			this.serveur = serveur;
			if (!serveur) {
				sc = new Client(ip, jeu);
				
				String entree1 = null;
				while(entree1 == null)
					entree1 = sc.in.readLine();
				
				String entree2 = null;
				while(entree2 == null)
					entree2 = sc.in.readLine();
				
				int nbLig = Integer.parseInt(entree1);
				int nbCol = Integer.parseInt(entree2);
				
				//Partage des options de construction de la grille
				setSize(nbLig, nbCol, true);
				
				jeu.lock = true;
				NetworkThread nt = new NetworkThread(sc, jeu);
				nt.start();
			}
			else {
				sc = new Serveur(jeu);
			}
		}
		catch(Exception e) {
			System.out.println("Erreur lors de creation du client ou du serveur");
		}
	}
	
	//GETTERS & SETTERS
	public int getNbCol() {
		return nbCol;
	}
		
	public int getNbLig() {
		return nbLig;
	}
}