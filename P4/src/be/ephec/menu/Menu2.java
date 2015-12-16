package be.ephec.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu2 extends JFrame  {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu2 frame = new Menu2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu2() {

		//setBackground(Color.red);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(10000, 8000);
		getContentPane().setLayout(new BorderLayout());
		//setContentPane(new JLabel(new ImageIcon("file:///Users/quentindehemptinne/Puissance4/P4/img/puisf_01.jpg")));
		getContentPane().setLayout(new FlowLayout());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//contentPane.setBackground(Color.red);


		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mnRestart = new JMenuItem("Nouveau jeu");
		mnMenu.add(mnRestart);

		JMenuItem mnReseau = new JMenuItem("Jeu en reseau");
		mnMenu.add(mnReseau);

		JMenuItem mnProfil = new JMenuItem("Profil");
		mnMenu.add(mnProfil);

		/*JMenuItem mnRetour = new JMenuItem("Retour");
		mnMenu.add(mnRetour);*/

		JMenuItem quitProp = new JMenuItem("Quitter");
		mnMenu.add(quitProp);

		JMenu mnApropos = new JMenu("A propos");
		menuBar.add(mnApropos);

		// menuBar.setBackground(Color.gray); mettre en gris clair pas en blanc

		JPanel panel = new JPanel();
		panel.setBackground(Color.yellow); // yellow = zone de grille P4
		contentPane.add(panel, BorderLayout.CENTER);

		JPanel tchat = new JPanel();
		tchat.setBackground(Color.GREEN); // red pour fenetre des message envoye et recu
		JTextArea jTextArea = new JTextArea();
		jTextArea.setSize(1001, 1000);
		contentPane.add(tchat, BorderLayout.EAST);
		
		tchat.add(jTextArea);


		JPanel ecriture = new JPanel();
		ecriture.setBackground(Color.BLUE); // blue zone de message
		contentPane.add(ecriture, BorderLayout.AFTER_LAST_LINE);
		JTextField message = new JTextField();
		message.setSize(500, 20); // 
		message.setText("Message à envoyer");
		JButton btnEnvoyerAuClient = new JButton("Envoyer à l'adversaire");
		btnEnvoyerAuClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ecriture.add(message);
		ecriture.add(btnEnvoyerAuClient);




		mnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {/*this*/
		Fenetre fen = new Fenetre();
			}
		/*  mnRetour.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    	// ouvrir la classe Profil
		    	
		    	} */
		    });
		//Action when start a new game
		mnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			NouveauJeuFenetre essaye = new NouveauJeuFenetre();
			}
		});
		mnApropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		quitProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);

			}



		});
		mnReseau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

			}
		});
	
		
		//networkTab.add(load);
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(10000, 10000));
		content.setBackground(Color.GRAY);
		content.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();
		
		//---grille-------YELLOW
		grid.gridx = 0;
		grid.gridy = 0;
		grid.gridheight = 1;
		grid.gridwidth = 1;
		grid.weightx = 10000;
		grid.weighty= 700;
		grid.fill = GridBagConstraints.BOTH;
		content.add(panel,grid);
		//----------------------------------
		
		//---fenetre de tchat----------
		grid.gridx = 1;
		grid.gridy = 0;
		grid.gridheight = 2;
		grid.gridwidth = 2;
		grid.weightx = 400;
		grid.weighty = 600;
		grid.fill = GridBagConstraints.BOTH;
		content.add(tchat,grid);
		
		// ONGLET
		
		grid.gridwidth = GridBagConstraints.REMAINDER;
		
		//---Endroit d'écriture--------
		
		grid.gridx = 1;
		grid.gridy = -1;
		grid.gridheight = 1;
		grid.gridwidth = 1;
		grid.weightx = 400;
		grid.weighty = 100;
		grid.fill = GridBagConstraints.BOTH;
		content.add(ecriture, grid);
		
		grid.gridwidth = GridBagConstraints.REMAINDER;
		
		this.setContentPane(content);
	} 

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Fenetre fen = new Fenetre();
		this.setVisible(true);
		
	}*/
	
	
}







/*JPanel panel = new JPanel();
		panel.setBackground(Color.red); //background pour un PANEL
		contentPane.add(panel, BorderLayout.NORTH);

		JButton jButtonProfil = new JButton("Profil");
		panel.add(jButtonProfil);

		JButton jButtonConnexion = new JButton("Connexion");
		panel.add(jButtonConnexion);

		JButton jButtonJouer = new JButton("JOUER");
		panel.add(jButtonJouer);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);

		JButton btnRgles = new JButton("Règles");
		panel_1.add(btnRgles);
 */

