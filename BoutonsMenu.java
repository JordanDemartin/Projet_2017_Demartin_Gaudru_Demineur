import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * La classe <code>BoutonInfoJeu</code> sert a realiser l'action du bouton dans la classe InfoJeu.
 *  
 * @version 0.1
 * @author Remy G. et Jordan D.
 */

public class BoutonsMenu implements ActionListener{
	private Menu menu;

	public BoutonsMenu(Menu menu){
		super();
		this.menu=menu;
	}

	public void actionPerformed(ActionEvent e){
		String contenu = e.getActionCommand();
		if ( contenu.equals("Nouvelle partie") ){
			//this.menu.dispose();
			menu.menu2();
			//Gameplay partie = new Gameplay(30,30,30);
			//partie.setVisible(true);
		}
		if ( contenu.equals("Charger partie") ){

			this.menu.dispose();

			Gameplay partie;

			try {
				FileInputStream fichier = new FileInputStream("save.dat");
				DataInputStream flux = new DataInputStream(fichier);

				partie = new Gameplay(flux.readInt(),flux.readInt(),flux.readInt());

				partie.setGameplay(flux);

				flux.close();

				partie.setVisible(true);

			} catch (FileNotFoundException er) {
				System.err.println("Erreur de sauvegarde");
			} catch (IOException er) {
				System.err.println("Erreur de sauvegarde");
			}

		}
		if ( contenu.equals("Quitter") ){
			System.exit(0);
		}
		if ( contenu.equals("Jouer!") ){
			boolean error=false;
			int in[] = new int[3];
			for(int i=0;i<3;i++){
				String entree = menu.getTextField(i);
				try{
					in[i] = Integer.parseInt(entree);
				}catch(IllegalArgumentException er){
					menu.setTextField(i,"4");
					error=true;
				}
			}

			if(!error){
				if(in[1]<4 || in[1]>30){
					error=true;
					menu.setTextField(1,"4");
				}if(in[2]<4 || in[2]>30){
					error=true;
					menu.setTextField(2,"4");
				}
				if( in[0]<0 || in[0]>(in[1] * in[2]) ){
					error=true;
					menu.setTextField(0,"4");
				}
			}

			if(!error){
				this.menu.dispose();
				Gameplay partie = new Gameplay(in[0],in[1],in[2]);
				partie.setVisible(true);
			}

		}

	}

}