import java.awt.event.*;
import java.io.*;

/**
 * La classe <code>BoutonMenu</code> sert a realiser l'action des boutons dans la classe <code>Menu</code>.
 *  
 * @version 1.0
 * @author Remy G. et Jordan D.
 */

public class BoutonsMenu implements ActionListener{

	/**
    * Permet de communiquer avec le <code>Menu</code>
    */
	private Menu menu;

	/**
    * Constructeur permetant de transmettre la classe du menu
    * 
    * @param menu Le <code>Menu</code> surveiller par cet observateur
    */
	public BoutonsMenu(Menu menu){
		super();
		this.menu=menu;
	}

	/**
    * L'action qui s'effectue quand on presse les <code>JButton</code> dans le <code>Menu</code>
    * 
    * @param evenement la variable contenant les circonstances de l'appel de la methode
    */
	public void actionPerformed(ActionEvent evenement){
		String contenu = evenement.getActionCommand();
		if ( contenu.equals("Nouvelle partie") ){
			menu.menu2();
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
					if(i==0){
						menu.setTextField(0,"0");
					}else{
						menu.setTextField(i,"4");
					}
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