package be.ephec.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;

public class zProfil extends JDialog {
  private ProfilInfo zInfo = new ProfilInfo();
  private boolean sendData;
  private JLabel nomLabel, sexeLabel, couleurLabel, icon;
  private JRadioButton tranche1, tranche2, tranche3, tranche4;
  private JComboBox sexe, couleur, force;
  private JTextField nom;
  

  public zProfil(JFrame parent, String title, boolean modal){
    super(parent, title, modal);
    this.setSize(775, 400);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.initComponent();
  }

  public ProfilInfo showZDialog(){
    this.sendData = false;
    this.setVisible(true);      
    return this.zInfo;      
  }

  private void initComponent(){
    //Icône
	/*ImageIcon icone = new  ImageIcon("Icone_Jetons.png");
    icon = new JLabel();
    icon.setIcon(icone);
    icon.setVisible(true); */
    icon = new JLabel(new ImageIcon("icone.jpg"));
    
    JPanel panIcon = new JPanel();
    panIcon.setPreferredSize(new Dimension(150, 150));
    
    panIcon.setBackground(Color.white);
    panIcon.setLayout(new BorderLayout());
    panIcon.add(icon); 
    

    //Le nom
    JPanel panNom = new JPanel();
    panNom.setBackground(Color.white);
    panNom.setPreferredSize(new Dimension(220, 90));
    nom = new JTextField();
    nom.setPreferredSize(new Dimension(100, 25));
    panNom.setBorder(BorderFactory.createTitledBorder("Pseudo"));
    nomLabel = new JLabel("Saisir un pseudo :");
    panNom.add(nomLabel);
    panNom.add(nom);

    //Le sexe
    JPanel panSexe = new JPanel();
    panSexe.setBackground(Color.white);
    panSexe.setPreferredSize(new Dimension(220, 60));
    panSexe.setBorder(BorderFactory.createTitledBorder("Sexe du joueur"));
    sexe = new JComboBox();
    sexe.addItem("Masculin");
    sexe.addItem("Féminin");
    sexe.addItem("Indéterminé");
    sexeLabel = new JLabel("Sexe : ");
    panSexe.add(sexeLabel);
    panSexe.add(sexe);

    //L'âge 
    JPanel panAge = new JPanel();
    panAge.setBackground(Color.white);
    panAge.setBorder(BorderFactory.createTitledBorder("Age du joueur"));
    panAge.setPreferredSize(new Dimension(440, 100));
    tranche1 = new JRadioButton("15 - 25 ans");
    tranche1.setSelected(true);
    tranche2 = new JRadioButton("26 - 35 ans");
    tranche3 = new JRadioButton("36 - 50 ans");
    tranche4 = new JRadioButton("+ de 50 ans");
    ButtonGroup bg = new ButtonGroup();
    bg.add(tranche1);
    bg.add(tranche2);
    bg.add(tranche3);
    bg.add(tranche4);
    panAge.add(tranche1);
    panAge.add(tranche2);
    panAge.add(tranche3);
    panAge.add(tranche4);

    //La taille
    JPanel panForce = new JPanel();
    panForce.setBackground(Color.white);
    panForce.setPreferredSize(new Dimension(220, 60));
    panForce.setBorder(BorderFactory.createTitledBorder("niveau du joueur"));
    /*tailleLabel = new JLabel("Taille : ");
    taille2Label = new JLabel(" cm");
    taille = new JTextField("180");
    taille.setPreferredSize(new Dimension(90, 25));*/
    force = new JComboBox();
    force.addItem("Débutant");
    force.addItem("Moyen");
    force.addItem("Expert");
    panForce.add(force);
    

    //La couleur des couleur
    JPanel panCouleurJetons = new JPanel();
    panCouleurJetons.setBackground(Color.white);
    panCouleurJetons.setPreferredSize(new Dimension(220, 60));
    panCouleurJetons.setBorder(BorderFactory.createTitledBorder("Couleur du jetons"));
    couleur = new JComboBox();
    couleur.addItem("Jaune");
    couleur.addItem("Brun");
    couleur.addItem("Rouge");
    couleur.addItem("Bleu");
    couleurLabel = new JLabel("couleur");
    panCouleurJetons.add(couleurLabel);
    panCouleurJetons.add(couleur);

    JPanel content = new JPanel();
    content.setBackground(Color.white);
    content.add(panNom);
    content.add(panSexe);
    content.add(panAge);
    content.add(panForce);
    content.add(panCouleurJetons);
    content.add(panIcon);
    JPanel control = new JPanel();
    JButton okBouton = new JButton("OK");
    
    okBouton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent arg0) {        
            zInfo = new ProfilInfo(nom.getText(), (String)sexe.getSelectedItem(), getAge(), (String)couleur.getSelectedItem() ,(String)force.getSelectedItem());
            setVisible(false);
           
          }

          public String getAge(){
            return (tranche1.isSelected()) ? tranche1.getText() : 
                   (tranche2.isSelected()) ? tranche2.getText() : 
                   (tranche3.isSelected()) ? tranche3.getText() : 
                   (tranche4.isSelected()) ? tranche4.getText() : 
                    tranche1.getText();  
          }

        });
  

    JButton cancelBouton = new JButton("Annuler");
    cancelBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
        
      }      
    });

    control.add(okBouton);
    control.add(cancelBouton);

    this.getContentPane().add(panIcon, BorderLayout.WEST);
    this.getContentPane().add(content, BorderLayout.CENTER);
    this.getContentPane().add(control, BorderLayout.SOUTH);
  }  

}
