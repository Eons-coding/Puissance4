package be.ephec.graphicInterface.client;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;

import be.ephec.application.ClientApplication;
import be.ephec.application.Console;
import be.ephec.application.Settings;
import be.ephec.network.ClientSocketInterface;

public class ClientChatJPanel extends JPanel implements ActionListener {
	private ClientApplication clientApp;
	private JTextField jTextFieldIP;
	private JTextField jTextFieldPort;
	private JLabel jLabelInfo;
	/**
	 * Create the panel.
	 */
	public ClientChatJPanel(ClientApplication clientApp) {
		this.clientApp = clientApp;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel jLabelIP = new JLabel("Server IP :");
		GridBagConstraints gbc_jLabelIP = new GridBagConstraints();
		gbc_jLabelIP.anchor = GridBagConstraints.EAST;
		gbc_jLabelIP.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelIP.gridx = 0;
		gbc_jLabelIP.gridy = 0;
		add(jLabelIP, gbc_jLabelIP);
		
		jTextFieldIP = new JTextField();
		jTextFieldIP.setText("127.0.0.1");
		GridBagConstraints gbc_jTextFieldIP = new GridBagConstraints();
		gbc_jTextFieldIP.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldIP.gridx = 1;
		gbc_jTextFieldIP.gridy = 0;
		add(jTextFieldIP, gbc_jTextFieldIP);
		jTextFieldIP.setColumns(10);
		
		JLabel jLabelPortTCP = new JLabel("TCP port :");
		GridBagConstraints gbc_jLabelPortTCP = new GridBagConstraints();
		gbc_jLabelPortTCP.anchor = GridBagConstraints.EAST;
		gbc_jLabelPortTCP.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelPortTCP.gridx = 0;
		gbc_jLabelPortTCP.gridy = 1;
		add(jLabelPortTCP, gbc_jLabelPortTCP);
		
		jTextFieldPort = new JTextField();
		jTextFieldPort.setText(Settings.DEFAULT_PORT_NUMBER+"");
		GridBagConstraints gbc_jTextFieldPort = new GridBagConstraints();
		gbc_jTextFieldPort.insets = new Insets(0, 0, 5, 0);
		gbc_jTextFieldPort.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldPort.gridx = 1;
		gbc_jTextFieldPort.gridy = 1;
		add(jTextFieldPort, gbc_jTextFieldPort);
		jTextFieldPort.setColumns(10);
		
		JButton jButtonConnecter = new JButton("Connexion");
		jButtonConnecter.addActionListener(this);
		GridBagConstraints gbc_jButtonConnecter = new GridBagConstraints();
		gbc_jButtonConnecter.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonConnecter.gridx = 1;
		gbc_jButtonConnecter.gridy = 2;
		add(jButtonConnecter, gbc_jButtonConnecter);
		
		jLabelInfo = new JLabel("");
		GridBagConstraints gbc_jLabelInfo = new GridBagConstraints();
		gbc_jLabelInfo.gridwidth = 2;
		gbc_jLabelInfo.gridx = 0;
		gbc_jLabelInfo.gridy = 3;
		add(jLabelInfo, gbc_jLabelInfo);
	}
	public void actionPerformed(ActionEvent e) {
		ClientSocketInterface client;
		try {
			client = new ClientSocketInterface(jTextFieldIP.getText(),
					Integer.parseInt(jTextFieldPort.getText()),clientApp);
			client.continuousReading();
			//TODO à améliorer
			clientApp.getInterfaceGraphiqueClient().setjPanelClient
			(new ClientMainJPanel(clientApp));
			clientApp.getInterfaceGraphiqueClient().setSize(Settings.LARGEUR_JFRAME_PINC_CLIENT,Settings.HAUTEUR_JFRAME_PINC_CLIENT);
			clientApp.setSocket(client);
			clientApp.getInterfaceGraphiqueClient().writeOnCommandConsole(
					Console.getCommandPrompt()+
					"Client connected to the server\n");
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			afficheInfo("Server not responding\n"
					+ "Try again later");
		}
	}
	
	public void afficheInfo(String s){
		jLabelInfo.setText(s);
	}

}
