import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * La classe <code>Menu</code> est une fenetre dans laquelle on fait apparaitre le texte "Demineur" et 2 ou 3 boutons selon si une sauvegarde existe
 * et dans un second temps, la selection du nombre de bombes, lignes et colonnes si une nouvelle partie est desirer par l'utilisateur.
 *
 * @version 1.0
 * @author Remy G. et Jordan D.
 */

public class Menu extends JFrame{
	/**
	 * observateur qui permet d'executer ceraine action dans le cas ou un bouton est presser
	 */
	private BoutonsMenu observateur;

	/**
	 * Un JPanel qui permet d'afficher de façon organiser le contenu de la fenetre
	 */
	private JPanel contenu;

	/**
	 * Un espace ou l'utilisateur rentre le nombre de bombes qu'il souhaite
	 */
	private JTextField bombesField;

	/**
	 * Un espace ou l'utilisateur rentre le nombre de lignes qu'il souhaite
	 */
	private JTextField lignesField;

	/**
	 * Un espace ou l'utilisateur rentre le nombre de colonnes qu'il souhaite
	 */
	private JTextField colonnesField;

	/**
	 * un constructeur qui met en place le premier menu et qui decide d'afficher oui ou non le bouton "Charger Partie"
	 */
	public Menu(){
		super("Demineur par J.Demartin et R.Gaudru");
		File f = new File("./save.dat");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.contenu = new JPanel();

		contenu.setLayout(new GridLayout(4,0,40,40));


		JLabel nom = new JLabel("Demineur");
		nom.setHorizontalAlignment(JLabel.CENTER);
		nom.setVerticalAlignment(JLabel.CENTER);
		nom.setFont(new Font(null, Font.BOLD, 25));
		contenu.add(nom);


		this.observateur = new BoutonsMenu(this);
		JButton jouer = new JButton("Nouvelle partie");
		jouer.addActionListener(this.observateur);
		jouer.setPreferredSize(new Dimension(140,40));
		contenu.add(jouer);

		if(f.exists()){
			JButton charger = new JButton("Charger partie");
			charger.addActionListener(this.observateur);
			charger.setPreferredSize(new Dimension(140,40));
			contenu.add(charger);
		}

		JButton quitter = new JButton("Quitter");
		quitter.addActionListener(this.observateur);
		quitter.setPreferredSize(new Dimension(140,40));
		contenu.add(quitter);

		this.add(contenu);

		this.setVisible(true);
	}

	/**
	 * une methode  qui met en place le second menu appeler par BoutonsMenu si l'utilisateur presse le bouton Nouvelle Partie dans le premier menu
	 */
	public void menu2(){
		this.remove(contenu);
		this.repaint();

		this.setLayout(new GridLayout(2,0));

		JPanel contenu2 = new JPanel();

		contenu2.setLayout(new GridLayout(3,2,40,40));

		JLabel bombesLabel = new JLabel("Bombes: ");
		bombesLabel.setFont(new Font(null, Font.BOLD, 15));
		contenu2.add(bombesLabel);

		this.bombesField = new JTextField("100");
		contenu2.add(this.bombesField);

		JLabel lignesLabel = new JLabel("Lignes: ");
		lignesLabel.setFont(new Font(null, Font.BOLD, 15));
		contenu2.add(lignesLabel);

		this.lignesField = new JTextField("30");
		contenu2.add(this.lignesField);

		JLabel colonnesLabel = new JLabel("Colonnes: ");
		colonnesLabel.setFont(new Font(null, Font.BOLD, 15));
		contenu2.add(colonnesLabel);

		this.colonnesField = new JTextField("30");
		contenu2.add(this.colonnesField);

		this.add(contenu2);

		JButton jouer = new JButton("Jouer!");
		jouer.addActionListener(this.observateur);
		this.add(jouer);

		this.setVisible(true);
	}

	/**
	 * une methode qui permet de recuperer le contenu d'un champ du menu 2
	 * @param field le numero du champ dont on souhaite obtenir le contenu
	 * @return le contenu du champ numero 'field'
	 */
	public String getTextField(int field){
		if(field == 0){
			return bombesField.getText();
		}
		if(field == 1){
			return lignesField.getText();
		}
		return colonnesField.getText();
	}

	/**
	 * une methode qui permet de modifier le contenu d'un champ du menu 2
	 *
	 * @param field le numero du champ dont on souhaite modifier le contenu
	 * @param s le contenu que l'on souhaite inserer dans le champ numero 'field'
	 */
	public void setTextField(int field,String s){
		if(field == 0){
			bombesField.setText(s);
		}
		if(field == 1){
			lignesField.setText(s);
		}
		if(field == 2){
			colonnesField.setText(s);
		}
	}

}
