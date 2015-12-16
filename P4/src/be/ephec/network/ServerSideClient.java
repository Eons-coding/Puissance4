package be.ephec.network;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import be.ephec.application.ServerApplication;

public class ServerSideClient implements Runnable{
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int num;
	private ServerApplication appliServeur;
	
	public String toString(){
		return "Client #"+num;
	}
	
	public int getNum() {
		return num;
	}

	public ServerSideClient(Socket socket, int num, ServerApplication appliServeur) throws IOException{
		this.appliServeur = appliServeur;
		this.socket = socket;
		this.num = num;
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(num); // on envoie son numéro au client
		new Thread(this).start();
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
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void run() {
		while (!socket.isClosed()){
			try {
				Object object = ois.readObject();
				appliServeur.handelReceivedObject(this, object);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				try {
					//TODO à améliorer
					socket.close();
					//TODO
					appliServeur.handelConnectedClient(this);
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		
	}
	
}