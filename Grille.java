import java.util.Random;
import java.awt.*;
import javax.swing.*;

public class Grille extends JPanel{
	private Case[][] cases;
	private int nbBombe;
	private int lignes;
	private int colonnes;
	private Gameplay partie;
	private InfoJeu info;

	public Grille(int nbBombe, int lignes,int colonnes,InfoJeu info){
		super();
		this.lignes=lignes;
		this.colonnes=colonnes;
		this.setBackground(new Color(0,0,0));
		this.nbBombe=nbBombe;
		this.info=info;
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
				//cases[nbI][nbJ].setBackground(new Color(255,0,0)); //aide au debogage, bombes > rouge
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

	public int getLignes(){
		return this.lignes;
	}

	public int getColonnes(){
		return this.colonnes;
	}

	public boolean tryDecouvreCase(int x, int y){
		return cases[x][y].tryDecouvre();
	}

	public int getFlagCase(int x, int y){
		return cases[x][y].getFlag();
	}

	public void setBackgroundCase(int x, int y,Color c){
		cases[x][y].setBackground(c);
	}

	public void setTextCase(int x, int y,String s){
		cases[x][y].alterTexte(s);
	}
	public boolean getStateCase(int x, int y){
		return cases[x][y].getState();
	}

	public void getEtoiles(){
		int nbEtoiles=0;
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				if(cases[i][j].getFlag() == 1){
					nbEtoiles++;
				}
			}
		}
		this.info.updateCounter(nbEtoiles);
	}

	public void testVictoire(){
		int nbOuvertes=0;
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				if(cases[i][j].getState()){
					nbOuvertes++;
				}
			}
		}

		if(nbOuvertes+this.nbBombe == this.lignes*this.colonnes){ //bloque la partie une fois celle-ci terminer

			this.desactiveCliqueCase();

			System.out.println("victoire");

		}

	}

	public void defaite(int x,int y){

		this.info.updateCounter(this.nbBombe);

		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				if(cases[i][j].getBombe()){
					if(cases[i][j].getFlag() == 0 || cases[i][j].getFlag() == 2){
						cases[i][j].setBackground(new Color(255,0,0));
					}
					if(i==x && j==y){
						cases[i][j].setBackground(new Color(220,20,60));
						cases[i][j].alterTexte("X");
					}
				}else{
					cases[i][j].setBackground(new Color(120,120,120));

					if(cases[i][j].getFlag() == 1){
	    				cases[i][j].setBackground(new Color(210,180,140));
	    			}

					int nbVoisin=this.bombeVoisin(i,j);
	    			if(nbVoisin!=0){
	    				cases[i][j].alterTexte(""+nbVoisin);
	    			}else{
	    				cases[i][j].alterTexte("");
	    			}
				}
			}
		}
		this.desactiveCliqueCase();

		System.out.println("Defaite");

	}

	public void desactiveCliqueCase(){
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				cases[i][j].removeMouseListener( cases[i][j].getObservateur() );
			}
		}
	}


}