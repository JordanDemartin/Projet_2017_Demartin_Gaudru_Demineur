import javax.swing.*;

public class Case extends JPanel{
	private boolean bombe;
	private boolean ouverte;
	private int flag;
	private Grille jeu;

	public Case(int i,int j,Grille jeu){
		super();
		this.bombe=false;
		this.ouverte=false;
		this.flag=0;
		this.jeu=jeu;
		JLabel texte = new JLabel("");
		this.add(texte);
		CliqueCase observateur = new CliqueCase(this,this.jeu,texte,i,j);
		this.addMouseListener(observateur);
	}

	public void setBombe(){
		this.bombe=true;
	}

	public boolean getBombe(){
		return this.bombe;
	}

	public boolean tryDecouvre(){
		if(this.ouverte==false){
			if(this.bombe && this.flag==0){
				return true;
			}
			if(this.flag==0){
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
}