import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ActionFenetre implements WindowListener {
	private Gameplay partie;

	public ActionFenetre(Gameplay partie) {
		super();
		this.partie=partie;
	}

	public void windowActivated(WindowEvent evenement) {}      // premier plan
	public void windowClosed(WindowEvent evenement) {}         // après fermeture

	public void windowClosing(WindowEvent evenement) {
		System.out.println("Fenetre Save");
		//this.partie.saveGameplay();
	} // avant fermeture

	public void windowDeactivated(WindowEvent evenement){}   // arrière-plan
	public void windowDeiconified(WindowEvent evenement){}    // restauration
	public void windowIconified(WindowEvent evenement){}      // minimisation
	public void windowOpened(WindowEvent evenement){}         // après ouverture
}