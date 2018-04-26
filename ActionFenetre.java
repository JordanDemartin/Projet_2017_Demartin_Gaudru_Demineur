import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * La classe <code>ActionFenetre</code> sert a déclencher la sauvgarde en cas de fermeture de la fenetre
 * alors que la partie n'est pas finis.
 * 
 * @version 0.1
 * @author Remy G. et Jordan D.
 */

public class ActionFenetre implements WindowListener {

/**
   * Permet de communiquer avec la partie en cours
   */
	private Gameplay partie;

/**
   * Constructeur permetant de transmettre la classe de la partie en cours
   * 
   * @param partie en cours
   */
	public ActionFenetre(Gameplay partie) {
		super();
		this.partie=partie;
	}

	public void windowActivated(WindowEvent evenement) {}      // premier plan
	public void windowClosed(WindowEvent evenement) {}         // après fermeture

/**
   * L'action qui s'effectue quand la fenetre est en train de se fermer, ici, declencher la sauvgarde.
   * 
   * @param evenement
   */
	public void windowClosing(WindowEvent evenement) {
		System.out.println("Fenetre Save");
		//this.partie.saveGameplay();
	} // avant fermeture

	public void windowDeactivated(WindowEvent evenement){}   // arrière-plan
	public void windowDeiconified(WindowEvent evenement){}    // restauration
	public void windowIconified(WindowEvent evenement){}      // minimisation
	public void windowOpened(WindowEvent evenement){}         // après ouverture
}