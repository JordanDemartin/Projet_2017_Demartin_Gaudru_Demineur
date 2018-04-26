import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoutonInfoJeu implements ActionListener{
	private Gameplay partie;

	public BoutonInfoJeu(Gameplay partie){
		super();
		this.partie=partie;
	}

	public void actionPerformed(ActionEvent e){
		String contenu = e.getActionCommand();
		if ( contenu.equals("Sauvgarder et Quitter") ){
			System.out.println("Bouton Save");
			//this.partie.saveGameplay();
		}
		System.exit(0);
	}


}