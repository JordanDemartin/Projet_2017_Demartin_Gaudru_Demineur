import javax.swing.*;
import java.awt.*;
import java.io.*;


/**
 * La classe <code>Case</code> sert recuperer toutes les informations necessaires sur une case.
 *  
 * @version 0.1
 * @author Remy G. et Jordan D.
 */
public class Case extends JPanel{
	private boolean bombe;
	private boolean ouverte;
	private JLabel texte;
	private int flag;
	private Grille jeu;
	private CliqueCase observateur;

	/**
    * Constructeur permetant de creer une case avec toutes ses caracteristiques par defaut
    * 
    * @param int i (indice i tu tableau)
    * @param int j (indice j tu tableau)
    * @param grille
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
	 * une methode qui permet de changer le contenu de l'attribut bombe
	 */
	public void setBombe(){
		this.bombe=true;
	}

	/**
	 * une methode qui permet de recuperer le de l'attribut bombe
	 */
	public boolean getBombe(){
		return this.bombe;
	}

	/**
	 * une methode qui permet de verifier si une case peut etre retourner
	 * @return boolean
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
	 * une methode qui permet de changer le flag d'une bombe
	 */
	public void nextFlag(){
		this.flag++;
		if(this.flag==3)
			this.flag=0;
	}

	/**
	 * une methode qui permet de recuperer le contenu de l'attribut flag
	 */
	public int getFlag(){
		return this.flag;
	}

	/**
	 * une methode qui permet de recuperer l'etat d'une bombe (ouverte ou non)
	 */
	public boolean getState(){
		return this.ouverte;
	}

	/**
	 * une methode qui permet de recuperer l'observateur de la bombe
	 */
	public CliqueCase getObservateur(){
		return this.observateur;
	}

	/**
	 * une methode qui permet de changer le texte d'une bombe (nb voisin)
	 * @param String (texte a affficher)
	 */
	public void alterTexte(String s){
		this.texte.setText(s);
	}

	/**
	 * une methode qui permet de decouvrir les voisin d'une case s'ils n'ont pas de bombe et ceci recursivement
	 * @param int x (indice i de la case)
	 * @param int y (indice j de la case)
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