import javax.swing.*;
import java.awt.*;

public class InfoJeu extends JPanel{
	private Gameplay partie;
	private JLabel counter;
	private int nbBombe;
	private JButton quit;
	private BoutonInfoJeu observateur;
	private ActionFenetre observateurFen;

	public InfoJeu(Gameplay partie,int nbBombe){
		super();
		this.partie = partie;
		this.nbBombe = nbBombe;

		this.counter = new JLabel("Bombes: "+nbBombe);
		this.counter.setFont(new Font(null, Font.BOLD, 15));
		this.add(this.counter);

		this.quit = new JButton("Sauvgarder et Quitter");
		this.add(this.quit);
		this.observateur = new BoutonInfoJeu(this.partie);
		this.quit.addActionListener(this.observateur);

		this.observateurFen = new ActionFenetre(this.partie);
		this.partie.addWindowListener(this.observateurFen);
	}

	public void updateCounter(int nbEtoiles){
		this.counter.setText("Bombes: "+(this.nbBombe-nbEtoiles) );
		if(nbEtoiles==-1){
			this.counter.setText("  Victoire!  ");
		}else if(nbEtoiles==-2){
			this.counter.setText("  Defaite...  ");
		}
	}

	public void setButtonToQuit(){
		this.quit.setText("Quitter");
	}

	public void removeActionFenetre(){
		this.partie.removeWindowListener(this.observateurFen);
	}

	/*public void saveInfoJeu(DataOutputStream flux){
		
	}*/
}