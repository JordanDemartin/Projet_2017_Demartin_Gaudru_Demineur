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
	    	if(e.getButton() == MouseEvent.BUTTON1){
	    		if(!this.bloc.tryDecouvre() && this.bloc.getFlag()==0){
	    			this.bloc.setBackground(new Color(255,255,255));
	    			System.out.println( this.jeu.bombeVoisin(this.x,this.y) );
	    			this.texte.setText( ""+this.jeu.bombeVoisin(this.x,this.y) );
	    		}	
	    	}
	    	if(e.getButton() == MouseEvent.BUTTON3){
	    		bloc.setBackground(new Color(100,100,100));
	    	}
	    }
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
}