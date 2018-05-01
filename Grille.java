import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * La classe <code>Grille</code> permet de creer la grille
 * et de connaitre toutes les informations necessaire au bon deroulement d'une partie
 *
 * @version 1.0
 * @author Remy G. et Jordan D.
 */

public class Grille extends JPanel{

	/**
	 * Nombre de bombe(s) presente(s) dans la grille
	 */
	private int nbBombe;

	/**
	 * Nombre de ligne(s) presente(s) dans la grille
	 */
	private int lignes;

	/**
	 * Nombre de colonne(s) presente(s) dans la grille
	 */
	private int colonnes;

	/**
	 * Tableau de cases qui permet de representer la grille
	 */
	private Case[][] cases;

	/**
	 * Permet de communiquer avec la partie en cours
	 */
	private Gameplay partie;

	/**
	 * Permet de communiquer la classe <code>InfoJeu</code>
	 */
	private InfoJeu info;

	/**
	 * Constructeur destine a creer la grille
	 *
	 * @param nbBombe nombre de bombe(s) choisie(s) dans le menu
	 * @param lignes nombre de ligne(s) choisie(s) dans le menu
	 * @param colonnes nombre de colonnes(s) choisie(s) dans le menu
	 * @param info La classe <code>InfoJeu</code> contenue dans le meme <code>Gameplay</code> que cette classe <code>Grille</code>
	 */
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

	/**
     * Permet de compter le nombre de bombe(s) qu'il y a autour d'une case
	 * @param x indice x dans le Tableau de cases
	 * @param y indice y dans le Tableau de cases
	 * @return nombre de bombe(s) voisine(s) a la case de coordonnees x et y
     */
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

	/**
     * Methode pour recuperer le nombre de ligne(s)
	 * @return nombre de ligne(s)
     */
	public int getLignes(){
		return this.lignes;
	}

	/**
     * Methode pour recuperer le nombre de colonne(s)
	 * @return nombre de colonne(s)
     */
	public int getColonnes(){
		return this.colonnes;
	}

	/**
	 * Essaie d'ouvrir/retourner cases[x][y]
	 *  
	 * @param x indice x dans le Tableau de cases
	 * @param y indice y dans le Tableau de cases
	 * @return renvoie 'true' si cases[x][y] est decouverte et contenait une bombe sinon elle renvoie 'false'
     */
	public boolean tryDecouvreCase(int x, int y){
		return cases[x][y].tryDecouvre();
	}

	/**
     * Methode pour recuperer le flag d'une case, Etoile/?/Rien
	 * @param x indice x dans le Tableau de cases
	 * @param y(indice y dans le Tableau de cases
	 * @return flag de la cases[x][y] (Etoile/?/Rien)
     */
	public int getFlagCase(int x, int y){
		return cases[x][y].getFlag();
	}

	/**
     * Methode pour changer la couleur de fond de la cases[x][y]
	 * @param x indice x dans le Tableau de cases
	 * @param y indice y dans le Tableau de cases
	 * @param c Couleur que l'on veut pour la cases[x][y]
     */
	public void setBackgroundCase(int x, int y,Color c){
		cases[x][y].setBackground(c);
	}

	/**
     * Methode pour changer la texte/contenu de la cases[x][y]
	 * @param x indice x dans le Tableau de cases
	 * @param y indice y dans le Tableau de cases
	 * @param s chaine de caracteres que l'on veut mettre dans la cases[x][y]
     */
	public void setTextCase(int x, int y,String s){
		cases[x][y].alterTexte(s);
	}

	/**
     * Methode pour recuperer l'etat d'une case (ouverte ou non)
     * 
	 * @param x indice x dans le Tableau de cases
	 * @param y indice y dans le Tableau de cases
	 * @return Renvoie 'false' si la cases[x][y] n'est pas encore ouverte et renvoie 'true' dans le cas contraire
     */
	public boolean getStateCase(int x, int y){
		return cases[x][y].getState();
	}

	/**
     * Methode pour recuperer le nombre d'etoile(s) dans la grille
	 * Et demande la mise a jour du texte dans le compteur de <code>InfoJeu</code> en consequence
     */
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

	/**
     * Permet de tester la victoire en comptant le nombre de case(s) ouvertes
	 * Si il y a victoire, on ne peut plus cliquer sur la grille
	 * et le bouton 'sauvegarder et quitter' devient 'menu'
     */
	public void testVictoire(){
		int nbOuvertes=0;
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				if(cases[i][j].getState()){
					nbOuvertes++;
				}
			}
		}

		if(nbOuvertes+this.nbBombe == this.lignes*this.colonnes){
			this.info.setButtonToMenu();

			this.desactiveCliqueCase();

			this.info.updateCounter(-1);

			this.info.removeActionFenetre();

			File f = new File("./save.dat");
			if(f.exists()){
				f.delete();
			}
		}

	}

	/**
     * Met en place un l'etat visuel de la defaite
	 * Si il y a victoire, on ne peut plus cliquer sur la grille
	 * et le bouton 'sauvegarder et quitter' devient 'menu'
	 *
	 * @param x indice x dans le Tableau de cases de la case ou une bombe a ete cliquee
	 * @param y indice y dans le Tableau de cases de la case ou une bombe a ete cliquee
     */
	public void defaite(int x,int y){


		this.info.setButtonToMenu();

		this.desactiveCliqueCase();

		this.info.updateCounter(-2);

		this.info.removeActionFenetre();

		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				if(cases[i][j].getBombe()){
					if(cases[i][j].getFlag() !=1){
						cases[i][j].setBackground(new Color(255,0,0));
					}
					if(i==x && j==y){
						cases[i][j].setBackground(new Color(220,20,60));
						cases[i][j].alterTexte("X");
					}
				}else if(!cases[i][j].getState()){			//ajout ici
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
		File f = new File("./save.dat");
		if(f.exists()){
			f.delete();
		}

	}

	/**
     * Permet de desactiver le clique sur la grille, methode utilisee pour bloquer la partie en cas de victoire / defaite
     */
	public void desactiveCliqueCase(){	//bloque la partie une fois celle-ci terminer
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				cases[i][j].removeMouseListener( cases[i][j].getObservateur() );
			}
		}
	}

	/**
     * La methode qui realise la sauvgarde, elle ouvre le fichier de sauvgarde en ecriture et ecrit
     * 3 infos cruciales a sauvgarder dans celui-ci avant de dire a chaque case de se sauvgarder dans un certain ordre
     */
	public void saveGrille(){

		try {
			FileOutputStream fichier = new FileOutputStream("save.dat");
			DataOutputStream flux = new DataOutputStream(fichier);

			flux.writeInt(this.nbBombe);
			flux.writeInt(this.lignes);
			flux.writeInt(this.colonnes);

			for (int i = 0; i<this.lignes ; i++) {
				for (int j = 0; j<this.colonnes ; j++) {
					cases[i][j].saveCase(flux);
				}
			}

			flux.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erreur de sauvegarde");
		} catch (IOException e) {
			System.err.println("Erreur de sauvegarde");
		}

	}

	/**
     * La methode qui dit a chaque case de se remettre a l'etat qui correspond a la sauvgarde
     * avant d'appeler la methode updateAffichage.
	 *
     * @param flux flux d'ecriture vers le fichier de sauvgarde
     */
	public void setGrille(DataInputStream flux){
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {
				cases[i][j].setCase(flux);
			}
		}
		this.updateAffichage();
	}

	/**
     * cette methode fait en sorte que l'etat reel d'une case et son etat visuel correspondent,
     * elle est utiliser uniquement lors de la restauration de la sauvgarde car c'est le seul moment ou ces deux etats ne
     * seront plus en accord
     */
	public void updateAffichage(){
		int nbVoisin;
		for (int i = 0; i<this.lignes ; i++) {
			for (int j = 0; j<this.colonnes ; j++) {

				if(cases[i][j].getState()){

					cases[i][j].setBackground(new Color(255,255,255));

					nbVoisin = this.bombeVoisin(i,j);
	    			if(nbVoisin!=0){
	    				cases[i][j].alterTexte(""+nbVoisin);
	    			}else{
	    				cases[i][j].alterTexte("");
	    			}

				}
				if(cases[i][j].getFlag() == 1){
					cases[i][j].setBackground(new Color(253,106,0));
	    			cases[i][j].alterTexte("\u2605"); //affiche étoile
				}else if(cases[i][j].getFlag() == 2){
					cases[i][j].setBackground(new Color(255,255,50));
	    			cases[i][j].alterTexte("?");
				}

			}
		}
		this.getEtoiles(); //update compteur de bombes
	}

}
