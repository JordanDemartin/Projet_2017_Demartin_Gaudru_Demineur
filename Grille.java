import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Grille extends JPanel{
	private Case[][] cases;
	private int nbBombe;
	private int lignes;
	private int colonnes;

	public Grille(int nbBombe, int lignes,int colonnes){
		super();
		this.lignes=lignes;
		this.colonnes=colonnes;
		this.setBackground(new Color(0,0,0));
		this.nbBombe=nbBombe;
		cases = new Case[lignes][colonnes];
		for (int i = 0; i<lignes ; i++) {
			for (int j = 0; j<colonnes ; j++) {
				cases[i][j] = new Case(i,j,this);
				this.add(cases[i][j]);
				cases[i][j].setBackground(new Color(0,100,0));
				cases[i][j].setPreferredSize(new Dimension(25,25));
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
		int i=x-1;
		int j=y-1;
		int maxi=x+2;
		int maxj=y+2;
		if(x==0){
			i=x;
		}
		if((x+2)>this.lignes){
			maxi=x+1;
		}
		
		for(; i<maxi;i++){
			if(y==0){
				j=y;
			}else{
				j=y-1;
			}
			if((y+2)>this.colonnes){
				maxj=y+1;
			}
			for(; j<maxj;j++){
				if(cases[i][j].getBombe()){
					nbVoisin++;
				}
			}
		}
		return nbVoisin;
	}
}