import javax.swing.*;
import java.awt.*;
import java.io.*;


/**
 * La classe <code>Case</code> est un JPanel contenant des infos necessaires pour la case qu'il est.
 *  
 * @version 1.0
 * @author Remy G. et Jordan D.
 */
public class Case extends JPanel{
	/**
	 * boolean determinant si une case contient une bombe ou non:
	 * true: bombe, false: pas de bombe
	 */
	private boolean bombe;

	/**
	 * boolean determinant si une case est ouverte:
	 * true: ouverte, false: fermee
	 */
	private boolean ouverte;

	/**
	 * JLabel permettant de contenir du texte dans la case, soit pour dire le nb de bombes voisines,
	 * soit pour afficher le flag de la case, soit en cas de defaite pour montrer quelle bombe a exploser
	 */
	private JLabel texte;

	/**
	 * int permettant de savoir le flag afficher sur la case, 0: rien, 1: etoile et 2: ?
	 */
	private int flag;

	/**
	 * Permet de communiquer avec la grille
	 */
	private Grille jeu;

	/**
	 * observateur detectant si l'utilisateur clique sur la case (clique gauche ou droit)
	 */
	private CliqueCase observateur;

	/**
    * Constructeur permetant de creer une case avec toutes ses caracteristiques par defaut
    * 
    * @param i indice i de la <code>Case</code>
    * @param j indice j de la <code>Case</code>
    * @param jeu la grille representant le jeu en cours
    */
	public Case(int i,int j,Grille jeu){
		super();
		this.bombe=false;
		this.ouverte=false;
		this.flag=0;
		this.jeu=jeu;
		this.texte = new JLabel("");
		this.texte.setFont(new Font(null, Font.BOLD, 15));
		this.add(this.texte);
		this.observateur = new CliqueCase(this,this.jeu,texte,i,j);
		this.addMouseListener(this.observateur);
	}

	/**
	 * une methode qui permet de declarer que la case contient une bombe
	 */
	public void setBombe(){
		this.bombe=true;
	}

	/**
	 * une methode qui permet de recuperer la valeur de l'attribut bombe
	 *
	 * @return la valeur de l'attribut bombe
	 */
	public boolean getBombe(){
		return this.bombe;
	}

	/**
	 * une methode qui permet de verifier si une case peut etre retourner et au passage de renvoyer, si la case est retourner, true si on decouvre une bombe
	 * @return renvoie 'true' si la case est decouverte et contenait une bombe sinon elle renvoie 'false'
	 */
	public boolean tryDecouvre(){
		if(this.ouverte==false){
			if(this.bombe && this.flag!=1){
				return true;
			}
			if(this.flag!=1){
				this.ouverte=true;
			}
		}
		return false;
	}

	/**
	 * une methode qui permet de changer le flag d'une case
	 */
	public void nextFlag(){
		this.flag++;
		if(this.flag==3)
			this.flag=0;
	}

	/**
	 * une methode qui permet de recuperer le contenu de l'attribut flag
	 *
	 * @return le flag de la case
	 */
	public int getFlag(){
		return this.flag;
	}

	/**
	 * une methode qui permet de recuperer l'etat d'une case (ouverte ou non)
	 *
	 * @return l'etat de la case, true: ouverte, false: fermee
	 */
	public boolean getState(){
		return this.ouverte;
	}

	/**
	 * une methode qui permet de recuperer l'observateur de la case
	 *
	 * @return l'observateur de la case
	 */
	public CliqueCase getObservateur(){
		return this.observateur;
	}

	/**
	 * une methode qui permet de changer le texte d'une case
	 * @param s texte a affficher
	 */
	public void alterTexte(String s){
		this.texte.setText(s);
	}

	/**
	 * une methode qui permet de decouvrir les voisin d'une case s'ils n'ont pas de bombe et ceci recursivement
	 * @param x indice x de la case dont on doit decouvrir les voisins
	 * @param y indice y de la case dont on doit decouvrir les voisins
	 */
	public void decouvreVoisin(int x,int y){
		

		int i=x-1;
		int j=y-1;
		int maxi=x+2;
		int maxj=y+2;

		if(x==0){
			i=x;
		}

		if((x+2)>jeu.getLignes()){
			maxi=x+1;
		}

		
		for(; i<maxi;i++){

			if(y==0){
				j=y;
			}else{
				j=y-1;
			}
			if((y+2)>jeu.getColonnes()){
				maxj=y+1;
			}

			for(; j<maxj;j++){

				if(!this.jeu.getStateCase(i,j)){
		    		int nbVoisin;
			    	if(!this.jeu.tryDecouvreCase(i,j) && this.jeu.getFlagCase(i,j)!=1){

			    		this.jeu.setBackgroundCase(i,j,new Color(255,255,255));
			    		nbVoisin=this.jeu.bombeVoisin(i,j);
			    		if(nbVoisin!=0){
			    			this.jeu.setTextCase(i,j,""+nbVoisin);
			    		}else{
			    			this.jeu.setTextCase(i,j,"");
			    			this.decouvreVoisin(i,j);
			    		}
			    	}
			    }
			}


		}


	}

	/**
	 * une methode qui permet d'ecrire dans un fichier les donnees de la case 
	 * @param flux du DataOutputStream
	 */
	public void saveCase(DataOutputStream flux){
		try{
			flux.writeBoolean(this.bombe);
			flux.writeBoolean(this.ouverte);
			flux.writeInt(this.flag);
		}catch(IOException e){
			System.err.println("Erreur de sauvegarde, saveCase");
		}
	}

	/**
	 * une methode qui permet de lire dans le fichier l'etat d'une case pour la restituer
	 * @param flux du DataInputStream
	 */
	public void setCase(DataInputStream flux){
		try{
			this.bombe = flux.readBoolean();
			this.ouverte = flux.readBoolean();
			this.flag = flux.readInt();
		}catch(IOException e){
			System.err.println("Erreur de recuperation des donnees, setCase");
		}
	}
}