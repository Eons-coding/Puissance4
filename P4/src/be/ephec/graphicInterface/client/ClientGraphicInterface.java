package be.ephec.graphicInterface.client;
import javax.swing.JFrame;
import javax.swing.JPanel;

import be.ephec.application.ClientApplication;
import be.ephec.application.Settings;

public class ClientGraphicInterface extends JFrame {
	private ClientApplication clientApp;
	private JPanel jPanelClient;

	/**
	 * Crée l'interface graphique correspondant au client
	 * @param applicationClient référence vers l'application cliente qui permet
	 *  d'accéder à cette JFrame et au socket
	 */
	public ClientGraphicInterface(ClientApplication clientApp) {
		this.clientApp = clientApp;
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new ClientChatJPanel(clientApp));
		// astuce pour que toutes les JFrame ne se supperposent pas
		int x = Settings.COIN_SUP_GAUCHE_CLIENT_X+ ((clientApp.getNbClients())%Settings.NB_COLONNES_AFFICHAGE)*Settings.LARGEUR_JFRAME_ACCUEIL_CLIENT;
		int y = Settings.COIN_SUP_GAUCHE_CLIENT_Y+((clientApp.getNbClients())/Settings.NB_COLONNES_AFFICHAGE)*Settings.HAUTEUR_JFRAME_PINC_CLIENT;
		setBounds(
				x, y,  
				Settings.LARGEUR_JFRAME_ACCUEIL_CLIENT, 
				Settings.HAUTEUR_JFRAME_ACCUEIL_CLIENT
		);	
	}
	
	/**
	 * ajoute dans le jTextArea correspondant à la console du client
	 * @param s la chaine de caractères
	 */
	public void writeOnCommandConsole(String s){
		((ClientMainJPanel)jPanelClient).writeOnCommandConsole(s);;
	}

	public JPanel getjPanelClient() {
		return jPanelClient;
	}

	/**
	 * Permet de charger un jPanel dans la JFrame
	 * @param jPanelClient le nouveau jPanel
	 */
	public void setjPanelClient(JPanel jPanelClient) {
		this.jPanelClient = jPanelClient;
		this.setContentPane(jPanelClient);
	}
}
