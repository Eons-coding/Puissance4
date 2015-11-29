package be.ephec.interfaceGraphique.serveur;
import javax.swing.JFrame;
import javax.swing.JPanel;

import be.ephec.application.ServerApplication;
import be.ephec.application.Settings;
import be.ephec.network.ServerSideClient;

public class ServerGraphicInterface extends JFrame {
	private JPanel jpanelServer;
	private ServerApplication sa;
	public ServerGraphicInterface(ServerApplication sa) {
		this.sa = sa;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Server");
		setBounds(
				Settings.COIN_SUP_GAUCHE_SERVEUR_X, 
				Settings.COIN_SUP_GAUCHE_SERVEUR_Y, 
				Settings.LARGEUR_JFRAME_ACCUEIL_SERVEUR, 
				Settings.HAUTEUR_JFRAME_ACCUEIL_SERVEUR
		);
		jpanelServer = new ServerChatJPanel(sa);
		setContentPane(jpanelServer);
		this.setVisible(true);
	}
	
	public void ajouteDansLaConsole(String s){
		ServerMainJPanel jps = (ServerMainJPanel) jpanelServer;
		jps.writeOnCommandConsole(s);
	}
	public void ajouteClientJComboBox(ServerSideClient client){
		ServerMainJPanel jps = (ServerMainJPanel) jpanelServer;
		jps.addClientJComboBox(client);
	}
	public void supprimeClientJComboBox(ServerSideClient client){
		ServerMainJPanel jps = (ServerMainJPanel) jpanelServer;
		jps.addClientJComboBox(client);
	}
	public JPanel getJpanelServer() {
		return jpanelServer;
	}
	public void setJpanelServer(JPanel jpanelServer) {
		this.jpanelServer = jpanelServer;
	}
}
