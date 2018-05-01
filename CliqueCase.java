import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe <code>CliqueCase</code> detecte un clique sur la case qu'elle surveille et agit en consequence des informations sur ce clique
 *  
 * @version 1.0
 * @author Remy G. et Jordan D.
 */

public class CliqueCase implements MouseListener {
	private Case bloc;
	private Grille jeu;
	private JLabel texte;
	private int x;
	private int y;

/**
   * Constructeur permetant de transmettre divers informations necessaires au bon fonctionnement de la classe
   * 
   * @param bloc case observer par cette classe
   * @param jeu grille a laquel appartient la <code>Case</code> bloc
   * @param texte contenu de la <code>Case</code> bloc
   * @param x indice x de la <code>Case</code> bloc
   * @param y indice y de la <code>Case</code> bloc
   */
	public CliqueCase(Case bloc, Grille jeu, JLabel texte, int x, int y) {
		this.bloc=bloc;
		this.jeu=jeu;
		this.texte=texte;
		this.x=x;
		this.y=y;
	}


/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
	public void mouseClicked(MouseEvent e) {
    }
    
/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
    public void mousePressed(MouseEvent e) {
    }
    
/**
   * L'action qui s'effectue quand on clique sur la case, si clique gauche et que la case est fermee, decouvre la case (et met en place l'affichage qui correspond)
   * avec un rique de defaite/victoire et si clique droit, passe au flag suivant (et met en place l'affichage qui correspond)
   * 
   * @param evenement la variable contenant les circonstances de l'appel de la methode
   */
    public void mouseReleased(MouseEvent evenement) {
    	if(!bloc.getState()){
    		int nbVoisin;
	    	if(evenement.getButton() == MouseEvent.BUTTON1){
	    		boolean sortieTryDecouvre = this.bloc.tryDecouvre();
	    		if(!sortieTryDecouvre && this.bloc.getFlag()!=1){
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
	    	if(evenement.getButton() == MouseEvent.BUTTON3){
	    		this.bloc.nextFlag();
	    		if(this.bloc.getFlag() == 0){
	    			this.bloc.setBackground(new Color(0,100,0));
	    			this.texte.setText("");
	    		}
	    		if(this.bloc.getFlag() == 1){
	    			this.bloc.setBackground(new Color(253,106,0));
	    			this.texte.setText("\u2605"); //affiche etoile
	    		}
	    		if(this.bloc.getFlag() == 2){
	    			this.bloc.setBackground(new Color(255,255,50));
	    			this.texte.setText("?");
	    		}
	    		jeu.getEtoiles();
	    	}
	    }
    }
    
/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
    public void mouseEntered(MouseEvent e) {
    }

/**
   * Methode servant simplement a 'implements' <code>WindowListener</code>
   */
    public void mouseExited(MouseEvent e) {
    }
}