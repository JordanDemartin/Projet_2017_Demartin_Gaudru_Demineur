import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>BoutonInfoJeu</code> sert a realiser l'action du bouton dans la classe InfoJeu.
 *  
 * @version 0.1
 * @author Remy G. et Jordan D.
 */

public class BoutonInfoJeu implements ActionListener{

/**
   * Permet de communiquer avec la partie en cours
   */
	private Gameplay partie;

/**
   * Constructeur permetant de transmettre la classe de la partie en cours
   * 
   * @param partie en cours
   */
	public BoutonInfoJeu(Gameplay partie){
		super();
		this.partie=partie;
	}

/**
   * L'action qui s'effectue quand on presse le JButton 'quit' dans la classe InfoJeu
   * 
   * @param evenement
   */
	public void actionPerformed(ActionEvent e){
		String contenu = e.getActionCommand();
		if ( contenu.equals("Sauvgarder et Quitter") ){
			this.partie.saveGameplay();
			this.partie.dispose();
			Menu menu = new Menu();
		}
		if ( contenu.equals("Menu") ){
			this.partie.dispose();
			Menu menu = new Menu();
		}
		
	}


}