package be.ephec.p4;

public class Options {
	private int nbLig;			
	private int nbCol;			
	boolean jouerContreOrdi = false;	
	boolean ordiCommence = false;
	boolean resOn = false;
	boolean serveur = false;
	ServeurClient sc;
	Jeu jeu;
	
	public Options(Jeu j) {
		
		this.jeu = j;
		OptionsGUI optionsGUI = new OptionsGUI(this);
		
	}
	
	public Options(int nbLig, int nbCol, Jeu jeu) {
		this.jeu = jeu;
		setSize(nbLig, nbCol, true);
	}
	
	
	public void setSize(int nbLig, int nbCol, boolean initSize) {
		this.nbLig = nbLig;
		this.nbCol = nbCol;
		if (initSize)
			initSize(nbLig, nbCol);
	}
	
	
	public void initSize(int nbLig, int nbCol) {
		jeu.plateau = new Grille(nbLig, nbCol, jeu);
		jeu.plateau.setVisible(true);
		jeu.matJeu = new byte[nbLig][nbCol];
		jeu.historique = new int[nbLig * nbCol];
	}
	
	
	public int getNbCol() {
		return nbCol;
	}
	
	
	public int getNbLig() {
		return nbLig;
	}
	
	public void initOrdi(boolean jouerContreOrdi, boolean ordiCommence, int diff) {
		this.jouerContreOrdi = jouerContreOrdi;
		this.ordiCommence = ordiCommence;
		jeu.deep = new Computer(diff); 
		if (ordiCommence)
			jeu.ordiJoue();
	}
	
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
			System.out.println("Erreur lors de création du client ou du serveur");
		}
	}
	
}