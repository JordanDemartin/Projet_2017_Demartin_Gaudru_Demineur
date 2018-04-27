import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Case extends JPanel{
	private boolean bombe;
	private boolean ouverte;
	private JLabel texte;
	private int flag;
	private Grille jeu;
	private CliqueCase observateur;

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

	public void setBombe(){
		this.bombe=true;
	}

	public boolean getBombe(){
		return this.bombe;
	}

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

	public void nextFlag(){
		this.flag++;
		if(this.flag==3)
			this.flag=0;
	}

	public int getFlag(){
		return this.flag;
	}

	public boolean getState(){
		return this.ouverte;
	}

	public CliqueCase getObservateur(){
		return this.observateur;
	}

	public void alterTexte(String s){
		this.texte.setText(s);
	}

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


	public void saveCase(DataOutputStream flux){
		try{
			flux.writeBoolean(this.bombe);
			flux.writeBoolean(this.ouverte);
			flux.writeInt(this.flag);
		}catch(IOException e){
			System.err.println("Erreur de sauvegarde, saveCase");
		}
	}

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