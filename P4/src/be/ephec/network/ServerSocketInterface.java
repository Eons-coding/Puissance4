package be.ephec.network;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import be.ephec.application.ServerApplication;
import be.ephec.application.Settings;

public class ServerSocketInterface extends ServerSocket implements Runnable {
	private ServerApplication sa;
	private ArrayList<ServerSideClient> clientList = new ArrayList<ServerSideClient>();
	private int nbClients = 0;
	/**
	 * Constructeur qui permet de démarrer un serveur TCP sur un numéro de port donné
	 * @param numPort le numéro de port sur lequel le serveur va écouter
	 * @throws IOException
	 */
	public ServerSocketInterface(int numPort,ServerApplication sa) throws IOException {
		super(numPort);
		this.sa = sa;
		//System.out.println("le serveur écoute sur "+this.getLocalPort());
		acceptMultipleClients();
	}
	/**
	 * Constructeur qui permet de démarrer un serveur TCP sur le numéro de port se trouvant 
	 * dans la classe Param
	 * @throws IOException
	 */
	public ServerSocketInterface(ServerApplication sa) throws IOException {
		super(Settings.DEFAULT_PORT_NUMBER);
		this.sa = sa;
		//System.out.println("Server listening on port "+this.getLocalPort());
		acceptMultipleClients();
	}
	/**
	 * Méthode qui permet de créer un serveur TCP sur un numéro de port libre à partir du 
	 * numéro de port indiqué dans la classe Param
	 * Tous les numéros de port sont testés à partir de Param.NUMPORTDEBASE jusqu'à 65535
	 * @return le serveur TCP
	 */
	public static ServerSocketInterface getAvailablePort(ServerApplication sa){
		ServerSocketInterface ms = null;
		for (int numPort = Settings.DEFAULT_PORT_NUMBER ; numPort<=65535 ; numPort++){
			try{
				ms = new ServerSocketInterface(numPort,sa);
				ms.acceptMultipleClients();
				break;
			}catch (IOException ioe){
			}
		}
		return ms;
	}
	/**
	 * Méthode qui permet de créer un serveur TCP sur un numéro de port libre à partir du 
	 * numéro de port reçu en paramètre
	 * Tous les numéros de port sont testés à partir de Param.NUMPORTDEBASE jusqu'à 65535
	 * @param numPortDepart un entier compris entre 1 et 65536. Ce sera le numéro de port 
	 * à partir duquel on cherche un port libre pour notre application
	 * @return le serveur TCP
	 * 
	 */
	public static ServerSocketInterface getAvailablePortFromNb(ServerApplication sa,int startPortNumber){
		ServerSocketInterface ms = null;
		for (int numPort = startPortNumber ; numPort<=65535 ; numPort++){
			try{
				ms = new ServerSocketInterface(numPort,sa);
				ms.acceptMultipleClients();
				break;
			}catch (IOException ioe){
				System.out.println("");
			}
		}
		return ms;
	}
	/**
	 * Méthode qui attend des clients pour les ajouter à la liste des clients connectés
	 */
	public void acceptMultipleClients(){
		Thread t = new Thread(this);
		t.start();
	}
	/**
	 * Juste pour tester en console que ça fonctionne
	 * Normalement on exécute la méthode main de la classe MonApplication
	 * @param args pas utilisé
	 */
	public static void main(String[] args) {
		ServerSocketInterface ssi = ServerSocketInterface.getAvailablePort(null);
		
	}
	public void writeOnClient(ServerSideClient ccs, Object o){
		ccs.write(o);
	}
	public void broadcastToClients(Object o){
		for (ServerSideClient serverSideClient : clientList) {
			serverSideClient.write(o);
		}
	}
	public Object readClient(int num){
		return clientList.get(num).read();
	}
	@Override
	public void run() {
		while (!this.isClosed()){
			try {
				ServerSideClient ccs = new ServerSideClient(this.accept(),++nbClients,sa);
				this.clientList.add(ccs);
				sa.handelConnectedClient(ccs);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

