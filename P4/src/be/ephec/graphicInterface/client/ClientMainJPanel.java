package be.ephec.graphicInterface.client;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import be.ephec.application.ClientApplication;

public class ClientMainJPanel extends JPanel implements ActionListener {

	private JTextField jTextFieldMessage;
	private JTextArea jTextAreaConsole;
	private ClientApplication clientApp;

	/**
	 * Create the panel.
	 */
	public ClientMainJPanel(ClientApplication clientApp) {
		this.clientApp = clientApp;

		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_contentPane);

		JLabel jLabelConsole = new JLabel("Client console :");
		GridBagConstraints gbc_jLabelConsole = new GridBagConstraints();
		gbc_jLabelConsole.anchor = GridBagConstraints.WEST;
		gbc_jLabelConsole.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelConsole.gridx = 0;
		gbc_jLabelConsole.gridy = 0;
		this.add(jLabelConsole, gbc_jLabelConsole);

		jTextFieldMessage = new JTextField();
		jTextFieldMessage.setText("Message");
		GridBagConstraints gbc_jTextFieldMessage = new GridBagConstraints();
		gbc_jTextFieldMessage.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldMessage.gridx = 1;
		gbc_jTextFieldMessage.gridy = 0;
		this.add(jTextFieldMessage, gbc_jTextFieldMessage);
		jTextFieldMessage.setColumns(10);

		JButton jButtonEnvoyer = new JButton("Send");
		jButtonEnvoyer.addActionListener(this);
		GridBagConstraints gbc_jButtonEnvoyer = new GridBagConstraints();
		gbc_jButtonEnvoyer.insets = new Insets(0, 0, 5, 0);
		gbc_jButtonEnvoyer.gridx = 2;
		gbc_jButtonEnvoyer.gridy = 0;
		this.add(jButtonEnvoyer, gbc_jButtonEnvoyer);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		this.add(scrollPane, gbc_scrollPane);

		jTextAreaConsole = new JTextArea();
		scrollPane.setViewportView(jTextAreaConsole);
	}
	
	public void actionPerformed(ActionEvent e) {
		clientApp.handelObjectToSend(jTextFieldMessage.getText());
	}

	public void writeOnCommandConsole(String s){
		jTextAreaConsole.append(s);
	}
}
