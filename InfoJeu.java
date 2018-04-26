/**
 * La classe <code>InfoJeu</code> est utilisé pour donner les informations de
 * la partie en cours au joueur
 *
 * @version 0.1
 * @author Remy G. et Jordan D.
 */

import javax.swing.*;
import java.awt.*;

public class InfoJeu extends JPanel{

	/**
	 * Permet de communiquer avec la partie en cours
	 */
	private Gameplay partie;

	/**
	 * JLabel qui affiche le nombre de bombe(s) restante(s)
	 * Ou affiche "victoire" ou "defaite" en fin de partie
	 */
	private JLabel counter;

	/**
	 * Attribut qui permet de savoir combien de bombe(s)
	 * il y a dans la grille
	 */
	private int nbBombe;

	/**
	 * Bouton qui sauvegarde et quitte la partie
	 * Action dans la classe BoutonInfoJeu
	 */
	private JButton quit;

	/**
	 * Bouton qui sauvegarde et quitte la partie
	 * Action dans la classe BoutonInfoJeu
	 */
	private BoutonInfoJeu observateur;

	/**
	 * Permet de declencher la sauvegarde en cas de fermeture
	 * de fenetre
	 */
	private ActionFenetre observateurFen;

	/**
	 * Constructeur destine a preparer les attributs
	 * @param partie (partie en cours)
	 * @param nbBombe (nombre de bombe(s) choisie(s) dans le menu)
	 */
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

	/**
     * Permet de changer le texte dans le JLabel counter
     * Change le nombre de bombe(s) restante(s)
	 * Ou affiche "victoire" ou "defaite"
	 * @param nbEtoiles nombre de marqueur(s') etoile(s)
     */
	public void updateCounter(int nbEtoiles){
		this.counter.setText("Bombes: "+(this.nbBombe-nbEtoiles) );
		if(nbEtoiles==-1){
			this.counter.setText("  Victoire!  ");
		}else if(nbEtoiles==-2){
			this.counter.setText("  Defaite...  ");
		}
	}

	/**
     * Change le contenu du JButton "quit"
	 * en "Quitter" en cas de vitoire ou défaite
     */
	public void setButtonToQuit(){
		this.quit.setText("Quitter");
	}

	/**
     * Permet de desactiver a sauvegarde dans le cas
	 * ou la partie est deja termine
     */
	public void removeActionFenetre(){
		this.partie.removeWindowListener(this.observateurFen);
	}

	/*public void saveInfoJeu(DataOutputStream flux){

	}*/
}
