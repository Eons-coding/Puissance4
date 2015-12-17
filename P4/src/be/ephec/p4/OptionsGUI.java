package be.ephec.p4;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * OptionsGUI.java
 *
 *
 */

/**
 *
 * @author  de Hemptinne Quentin, Dechamps Xavier, Barata Jorge
 */

public class OptionsGUI extends JFrame implements ActionListener {
	
	Options opts;
	
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints constraints = new GridBagConstraints();
	JPanel pane = new JPanel();
	
	JPanel lignesColPane = new JPanel(new GridLayout(2, 2, 10, 10));
	JLabel nbLignesLabel = new JLabel("Nombre de lignes :");
	JTextField jTexte = new JTextField("6", 2);
	JLabel nbColLabel = new JLabel("Nombre de colonnes :");
	JTextField text2 = new JTextField("7", 2);
	
	JCheckBox jouerContreOrdi = new JCheckBox("Jouer contre l'ordinateur", false);
	JCheckBox ordiCommence = new JCheckBox("L'ordinateur commence", false);
	JLabel jLabel4 = new JLabel("Difficulté");
	JSlider slider1 = new JSlider(1, 3, 1);
        
	JPanel reseauPane = new JPanel();
	JCheckBox reseauOk = new JCheckBox("Jouer en réseau", false);
	ButtonGroup serveurClient = new ButtonGroup();
	JRadioButton server = new JRadioButton("Etre serveur", false);
	JRadioButton client = new JRadioButton("Etre client", true);
	JTextField jIpTextField = new JTextField("Hostname or IP number", 15);

	JButton ok = new JButton("Ok");
	
	/**
	 * Construction de la fen�tre d'options
	 * @param opts Ojet Options contenant les options souhait�es � l'initialisation
	 */
	public OptionsGUI(Options opts) {
		
		super("Options de jeu");
		setSize(600, 400);
		setLocation(350, 100);
		
		pane.setLayout(gbl);
		
	
		buildConstraints(constraints, 0, 0, 2, 1, 80, 30);
		lignesColPane.add(nbLignesLabel);
		lignesColPane.add(jTexte);
		lignesColPane.add(nbColLabel);
		lignesColPane.add(text2);
		gbl.setConstraints(lignesColPane, constraints);
		
		pane.add(lignesColPane);
		
		
		JPanel computerPane = new JPanel(new GridLayout(4, 1));
		computerPane.add(jouerContreOrdi);
		computerPane.add(ordiCommence);
		computerPane.add(jLabel4);
		
		initSlider(); 
		
		jouerContreOrdi.addActionListener(this);
		
		buildConstraints(constraints, 0, 1, 2, 1, 0, 20);
		gbl.setConstraints(computerPane, constraints);
		
		computerPane.add(slider1);
		
		pane.add(computerPane);
		
		
		JPanel serveurClientPane = new JPanel(new BorderLayout());
		serveurClient.add(server);
		serveurClient.add(client);
		serveurClientPane.add(reseauOk, "North");
		serveurClientPane.add(server, "West");
		serveurClientPane.add(client, "East");
		serveurClientPane.add(jIpTextField, "South");
		reseauPane.add(serveurClientPane);
		
		reseauOk.addActionListener(this);
		client.addActionListener(this);
		server.addActionListener(this);
		
		buildConstraints(constraints, 0, 2, 2, 1, 0, 10);
		gbl.setConstraints(reseauPane, constraints);
		
		pane.add(reseauPane);
                
		
		ok.addActionListener(this);
		
		buildConstraints(constraints, 1, 3, 1, 1, 0, 10);
		gbl.setConstraints(ok, constraints);
		pane.add(ok);
		
		setIAOptsVisible(jouerContreOrdi.isSelected());
		setNetworkOptsVisible(reseauOk.isSelected());
		
		setContentPane(pane);
		
		setVisible(true);
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.opts = opts;
	}
	
	/** Initialisation du slider*/
	public void initSlider() {
		slider1.setMajorTickSpacing(4);
		slider1.setMinorTickSpacing(1);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setSnapToTicks(true);
	}
	
	public void buildConstraints (GridBagConstraints gbc, int gx, int gy, int gw, int gh, int wx, int wy) {
		gbc.gridx = gx;			
		gbc.gridy = gy;
		gbc.gridwidth = gw;		
		gbc.gridheight = gh; 
		gbc.weightx = wx;		
		gbc.weighty = wy;
	}
	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		if (src == ok) {
			try {
				int nbLig = Integer.parseInt(jTexte.getText());
				int nbCol = Integer.parseInt(text2.getText());
				if (nbLig <= 0 || nbCol <= 0)
					throw new NumberFormatException();
				if (!reseauOk.isSelected() || !client.isSelected())
					opts.setSize(nbLig, nbCol, true);
				
				setVisible(false);
				
				opts.initOrdi(jouerContreOrdi.isSelected(), ordiCommence.isSelected(), slider1.getValue());
				
				if (reseauOk.isSelected()) {
					if (client.isSelected())
						opts.initReseau(false, jIpTextField.getText());
					else
						opts.initReseau(true, new String());
				}
				
			} catch (NumberFormatException e) {
				Saisie.erreurMsgOk("Options invalides : le nombre de ligne et le nombre de colonnes doivent etre des entiers", "Options invalides");
			}
			
		}
		else if (src == reseauOk || src == client || src == server)
			setNetworkOptsVisible(reseauOk.isSelected()); 
		
		else if (src == jouerContreOrdi)
			setIAOptsVisible(jouerContreOrdi.isSelected()); 
	}
	
	/**
	 * Rend les options pour le jeu solo modifiables 
	 * @param active Booleen �valuant l'activation des param�tres du jeu solo
	 */
	public void setIAOptsVisible(boolean active) {
		ordiCommence.setEnabled(active);
		slider1.setEnabled(active);
		reseauOk.setEnabled(!active);
	}	
	
	/**
	 * Rend les options r�seau modifiables 
	 * @param active Booleen �valuant l'activation des param�tres r�seau
	 */
	public void setNetworkOptsVisible(boolean active) {
		server.setEnabled(active);
		client.setEnabled(active);
		if (client.isSelected())
			jIpTextField.setEnabled(active);
		else
			jIpTextField.setEnabled(false);
		
		jouerContreOrdi.setEnabled(!active);
	}	
}
