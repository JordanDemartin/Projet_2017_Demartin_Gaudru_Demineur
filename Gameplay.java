import java.awt.*;
import javax.swing.*;

public class Gameplay{
	private Grille jeu;

	public Gameplay(int nbBombe, int lignes,int colonnes){
		JFrame fenetre = new JFrame();
		JPanel info = new JPanel();
		int sup=0;
		info.setBackground(new Color(100,100,100));
		this.jeu = new Grille(nbBombe,lignes,colonnes);
		if(lignes>colonnes){
			sup=lignes;
		}else{
			if(lignes<(colonnes/2)){
				sup=(colonnes/2);
			}else{
				sup=colonnes;
			}
		}
		int tailleXjeu = colonnes*25+(colonnes+2)*5;
		int tailleY = (sup+1)*25+(sup+8)*6; //minimum = 4*26+50 = 154
		int tailleXinfo = 150;
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setLocationRelativeTo(null);
		fenetre.setResizable(true);
		fenetre.setTitle("Demineur par J.Demartin et R.Gaudru");
		

		this.jeu.setPreferredSize(new Dimension(tailleXjeu,tailleY));
		info.setPreferredSize(new Dimension(tailleXinfo,tailleY));

		fenetre.setSize(tailleXjeu+tailleXinfo,tailleY);
		fenetre.add(this.jeu,BorderLayout.WEST);
		fenetre.add(info,BorderLayout.EAST);
		fenetre.setVisible(true);

	}
}