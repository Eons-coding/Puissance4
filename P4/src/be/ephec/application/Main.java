package be.ephec.application;
public class Main {
	public static void main(String[] args) {
		ServerApplication serverApp = new ServerApplication();
		ClientApplication clientApp[] = new ClientApplication[Settings.NB_CLIENTS];
		for (int i = 0 ; i < clientApp.length ; i++){
			clientApp[i] = new ClientApplication();
		}
	}
}
