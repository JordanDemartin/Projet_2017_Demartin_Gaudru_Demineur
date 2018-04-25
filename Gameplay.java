import 

public class Gameplay{

	public Gameplay(int nbBombe, int lignes,int colonnes){
		JFrame fenetre = new JFrame();
		panel[][] cases;
		for (int i = 0; i<lignes ; i++) {
			for (int j = 0; j<colonnes ; j++) {
				panel[i][j] = new JPanel();
				panel[i][j].addMouseListener();
			}
		}
	}


}