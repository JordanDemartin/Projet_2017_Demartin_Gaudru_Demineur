import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Grille extends JPanel{
	private Case[][] cases;
	private int nbBombe;

	public Grille(int nbBombe, int lignes,int colonnes){
		super();
		this.setBackground(new Color(0,0,0));
		this.nbBombe=nbBombe;
		cases = new Case[lignes][colonnes];
		for (int i = 0; i<lignes ; i++) {
			for (int j = 0; j<colonnes ; j++) {
				cases[i][j] = new Case(i,j);
				this.add(cases[i][j]);
				cases[i][j].setBackground(new Color(i*20,100,j*20));
				cases[i][j].setPreferredSize(new Dimension(80,80));
			}
		}
		for (int i = 0; i<nbBombe; i++){
			Random ran=new Random();
			int nbI = ran.nextInt(lignes);
			int nbJ = ran.nextInt(colonnes);

			if(cases[nbI][nbJ].getBombe()){
				i--;
			}
			else{
				cases[nbI][nbJ].setBombe();
				cases[nbI][nbJ].setBackground(new Color(255,0,0));
			}
		}
	}

	public int bombeVoisin(int x,int y){
		int nbVoisin=0;
		for(int i=x-1; i<=x;i++){
			for(int j=y-1; j<=y;j++){
				if(cases[i][j].getBombe()){
					nbVoisin++;
				}
			}
		}
		return nbVoisin;
	}
}