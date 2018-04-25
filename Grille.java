import java.util.Random;

public class Grille{
	private Case[][] cases;
	private int nbBombe;

	public void Grille(int nbBombe, int lignes,int colonnes){
		this.nbBombe=nbBombe;
		cases = new Case[lignes][colonnes];
		for (int i = 0; i<lignes ; i++) {
			for (int j = 0; j<colonnes ; j++) {
				cases[i][j] = new Case();
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