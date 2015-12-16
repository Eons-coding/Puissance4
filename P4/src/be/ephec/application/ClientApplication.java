package be.ephec.application;
import be.ephec.graphicInterface.client.ClientGraphicInterface;
import be.ephec.network.ClientSocketInterface;

public class ClientApplication {
	private ClientGraphicInterface cgi;
	private ClientSocketInterface socket;
	private int num;
	private static int nbClients = 0;
	public ClientApplication(){
		cgi = new ClientGraphicInterface(this);
		nbClients++;
	}

	public static void main(String[] args) {
		ClientApplication clientApp = new ClientApplication();
	}
	
	public void handelNewConnexion(){
		cgi.writeOnCommandConsole(Console.getCommandPrompt()+
				"New client connected\n");
	}
	
	public void handelReceivedObject(Object o){
		cgi.writeOnCommandConsole(Console.getCommandPrompt()+o.toString()+"\n");
	}
	
	public void setTitle(String s){
		cgi.setTitle(s);
	}
	
	public void handelObjectToSend(Object o){
		socket.write(o);
	}

	//Getters & Setters
	public ClientGraphicInterface getInterfaceGraphiqueClient() {
		return cgi;
	}
	public void setInterfaceGraphiqueClient
	 (ClientGraphicInterface interfaceGraphiqueClient) {
		this.cgi = interfaceGraphiqueClient;
	}

	public ClientSocketInterface getSocket() {
		return socket;
	}

	public void setSocket(ClientSocketInterface socket) {
		this.socket = socket;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	public static int getNbClients() {
		return nbClients;
	}
	public static void setNbClients(int nbClients) {
		ClientApplication.nbClients = nbClients;
	}	
}

