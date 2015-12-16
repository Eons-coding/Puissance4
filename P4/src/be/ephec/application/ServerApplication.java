package be.ephec.application;

import java.util.Calendar;

import be.ephec.interfaceGraphique.serveur.ServerGraphicInterface;
import be.ephec.network.ServerSideClient;
import be.ephec.network.ServerSocketInterface;

public class ServerApplication {
	private ServerGraphicInterface serverGraphicInterface;
	private ServerSocketInterface serverSocketInterface;
	public ServerApplication(){
		serverGraphicInterface = new ServerGraphicInterface(this);
	}
	public static void main(String[] args) {
		ServerApplication sa = new ServerApplication();
	}

	public void handelConnectedClient(ServerSideClient ccs){
		getInterfaceGraphiqueServeur().ajouteDansLaConsole(
				Console.getCommandPrompt()+"Client #"+
				ccs.getNum()+" connected\n");
		getInterfaceGraphiqueServeur().ajouteClientJComboBox(ccs);
	}

	public void handelReceivedObject(ServerSideClient ccs,Object object){
		Calendar cal = Calendar.getInstance();
		getInterfaceGraphiqueServeur().ajouteDansLaConsole(
				Console.getCommandPrompt()+"> From client #"+ 
				ccs.getNum()+" : "+object.toString()+"\n");
	}

	public void handelObjectToSend(ServerSideClient ccs,Object o){
		getServeurSocket().writeOnClient(ccs, o);
	}

	public void handelBroadcastObject(Object o){
		getServeurSocket().broadcastToClients(o);
	}

	public void handelDisconnectedClient(ServerSideClient ccs){
		getInterfaceGraphiqueServeur().ajouteDansLaConsole(
				Console.getCommandPrompt()+"Client #"+
				ccs.getNum()+" disconnected\n");
		getInterfaceGraphiqueServeur().supprimeClientJComboBox(ccs);
	}

	//Getters & Setters
	public ServerGraphicInterface getInterfaceGraphiqueServeur() {
		return serverGraphicInterface;
	}
	public void setInterfaceGraphiqueServeur(ServerGraphicInterface interfaceGraphiqueServeur) {
		this.serverGraphicInterface = interfaceGraphiqueServeur;
	}
	public ServerSocketInterface getServeurSocket() {
		return serverSocketInterface;
	}
	public void setServeurSocket(ServerSocketInterface serveurSocket) {
		this.serverSocketInterface = serveurSocket;
	}
}


