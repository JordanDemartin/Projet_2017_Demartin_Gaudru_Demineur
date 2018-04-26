import java.awt.*;
import javax.swing.*;

public class Gameplay extends JFrame{
	private Grille jeu;
	private InfoJeu info;

	public Gameplay(int nbBombe, int lignes,int colonnes){
		super("Demineur par J.Demartin et R.Gaudru");
		
		int maximum;
		if(lignes>colonnes){	//permet de rendre l'affichage plus élégant quand le nombre de colonnes est trop grand par rapport au nombre de lignes
			maximum=lignes;
		}else{
			if(lignes<(colonnes/2)){
				maximum=(colonnes/2);
			}else{
				maximum=colonnes;
			}
		}
		int tailleXjeu = colonnes*25+(colonnes+2)*5;
		int tailleY = (maximum+1)*25+(maximum+8)*6; //minimum = (4+1)*25+(4+8)*6 = 197 pixels
		int tailleXinfo = 150;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.info = new InfoJeu(this,nbBombe);	//mise en place du panel contenant la grille de jeu
		info.setPreferredSize(new Dimension(tailleXinfo,tailleY));
		this.add(info,BorderLayout.EAST);

		this.jeu = new Grille(nbBombe,lignes,colonnes,info);	//mise en place du panel contenant les informations sur la partie en cours
		this.jeu.setPreferredSize(new Dimension(tailleXjeu,tailleY));
		this.add(this.jeu,BorderLayout.WEST);
		
		this.setSize(tailleXjeu+tailleXinfo,tailleY);
	}

	/*public void saveGameplay(){
	try {
			FileOutputStream fichier = new FileOutputStream("save.dat");
			DataOutputStream flux = new DataOutputStream(fichier);
			
			this.info.saveInfoJeu(flux);
			this.jeu.saveGrille(flux);

			flux.close();
		} catch (FileNotFoundException e1) {
			System.err.println("Erreur de sauvegarde");
		} catch (IOException e2) {
			System.err.println("Erreur de sauvegarde");
		}
	}*/
}