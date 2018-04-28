import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * La classe <code>Gameplay</code> est la fenetre dans laquelle va se jouer la partie.
 *
 * @version 0.1
 * @author Remy G. et Jordan D.
 */

public class Gameplay extends JFrame{

	/**
    * Le JPanel contenant la grille de jeu
    */
	private Grille jeu;

	/**
    * Le JPanel contenant les informations sur la partie
    */
	private InfoJeu info;

	/**
	 * Constructeur destine a preparer les proprietes de la fenetre <code>Gameplay</code> ainsi que les proprietes des JPanel <code>Grille</code> et <code>InfoJeu</code>
	 *
	 * @param nbBombe (nombre de bombe(s) choisie(s) dans le menu)
	 * @param lignes (nombre de ligne(s) choisie(s) dans le menu)
	 * @param colonnes (nombre de colonnes(s) choisie(s) dans le menu)
	 */
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

	/**
	 * La methode qui permet de demander la sauvgarde de la partie
	 *
	 */
	public void saveGameplay(){
		this.jeu.saveGrille();
	}

	/**
	 * La methode qui permet de demander le chargement de la sauvgarde de la partie
	 *
	 */
	public void setGameplay(DataInputStream flux){
		this.jeu.setGrille(flux);
	}
}