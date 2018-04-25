import java.awt.*;
import javax.swing.*;

public class Gameplay{
	private Grille jeu;

	public Gameplay(int nbBombe, int lignes,int colonnes){
		JFrame fenetre = new JFrame();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(colonnes*100,lignes*100);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(true);
		fenetre.setTitle("Demineur par J.Demartin et R.Gaudru");
		this.jeu=new Grille(nbBombe,lignes,colonnes);
		fenetre.add(jeu);
		fenetre.setVisible(true);

	}
}