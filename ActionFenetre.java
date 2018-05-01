import java.awt.event.*;

/**
 * La classe <code>ActionFenetre</code> sert a déclencher la sauvgarde en cas de fermeture de la fenetre
 * alors que la partie n'est pas finis.
 * 
 * @version 1.0
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
   * @param partie partie en cours
   */
	public ActionFenetre(Gameplay partie) {
		super();
		this.partie=partie;
	}

/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void windowActivated(WindowEvent evenement) {}      // premier plan
/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void windowClosed(WindowEvent evenement) {}         // après fermeture



/**
   * L'action qui s'effectue quand la fenetre est en train de se fermer, ici, declencher la sauvgarde.
   * 
   * @param evenement la variable contenant les circonstances de l'appel de la methode
   */
	public void windowClosing(WindowEvent evenement) {
		this.partie.saveGameplay();
	} // avant fermeture



/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void windowDeactivated(WindowEvent evenement){}   // arrière-plan
/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void windowDeiconified(WindowEvent evenement){}    // restauration
/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void windowIconified(WindowEvent evenement){}      // minimisation
/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void windowOpened(WindowEvent evenement){}         // après ouverture
}