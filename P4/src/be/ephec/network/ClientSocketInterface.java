package be.ephec.network;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import be.ephec.application.ClientApplication;
import be.ephec.application.Settings;

public class ClientSocketInterface extends Socket implements Runnable {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ClientApplication appliClient;
	
	
	public ClientSocketInterface(ClientApplication appliClient) throws UnknownHostException, IOException{
		super(Settings.SERVER_IP,Settings.DEFAULT_PORT_NUMBER);
		this.appliClient = appliClient;
		constructorsCommonMethod();
	}
	public ClientSocketInterface(String ip, int port,ClientApplication appliClient) throws UnknownHostException, IOException{
		super(ip,port);
		this.appliClient = appliClient;
		constructorsCommonMethod();
	}
	public void constructorsCommonMethod(){
		try {
			oos = new ObjectOutputStream(this.getOutputStream());
			ois = new ObjectInputStream(this.getInputStream());
			appliClient.setNum((int)ois.readObject());
			appliClient.setTitle("Client #"+appliClient.getNum());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	public void continuousReading(){
		new Thread(this).start();
	}
	public void finalize(){
		try {
			this.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(Object o){
		try {
			oos.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object read(){
		try {
			Object o = ois.readObject();
			appliClient.handelReceivedObject(o);
			return o;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			try {
				this.close(); 
				// si on ne sait pas lire c'est que le serveur est fermé
				//TODO Trouver une solution moins radicale
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void run() {
		while (!this.isClosed()){
			Object o = this.read();
		}
		
	}
}



