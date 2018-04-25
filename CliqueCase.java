import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CliqueCase implements MouseListener {
	private Case bloc;
	private Grille jeu;
	private JLabel texte;
	private int x;
	private int y;

	public CliqueCase(Case bloc, Grille jeu, JLabel texte, int x, int y) {
		this.bloc=bloc;
		this.jeu=jeu;
		this.texte=texte;
		this.x=x;
		this.y=y;
	}



	public void mouseClicked(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
    }
    
    public void mouseReleased(MouseEvent e) {
    	if(!bloc.getState()){
    		int nbVoisin;
	    	if(e.getButton() == MouseEvent.BUTTON1){
	    		boolean sortieTryDecouvre = this.bloc.tryDecouvre();
	    		if(!sortieTryDecouvre && this.bloc.getFlag()==0){
	    			this.bloc.setBackground(new Color(255,255,255));
	    			nbVoisin=this.jeu.bombeVoisin(this.x,this.y);
	    			if(nbVoisin!=0){
	    				this.texte.setText(""+nbVoisin);
	    			}else{
	    				this.texte.setText("");
	    				this.bloc.decouvreVoisin(this.x,this.y);
	    			}
	    			jeu.testVictoire();
	    		}else if(sortieTryDecouvre){
	    			jeu.defaite(this.x,this.y);
	    		}
	    	}
	    	if(e.getButton() == MouseEvent.BUTTON3){
	    		this.bloc.nextFlag();
	    		if(this.bloc.getFlag() == 0){
	    			this.bloc.setBackground(new Color(0,100,0));
	    			this.texte.setText("");
	    		}
	    		if(this.bloc.getFlag() == 1){
	    			this.bloc.setBackground(new Color(253,106,0));
	    			this.texte.setText("\u2605"); //affiche étoile
	    		}
	    		if(this.bloc.getFlag() == 2){
	    			this.bloc.setBackground(new Color(255,255,50));
	    			this.texte.setText("?");
	    		}
	    		jeu.getEtoiles();
	    	}
	    }
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
}