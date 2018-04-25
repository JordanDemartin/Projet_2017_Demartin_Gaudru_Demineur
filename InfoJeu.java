import javax.swing.*;
import java.awt.*;

public class InfoJeu extends JPanel{
	private Gameplay partie;
	private JLabel counter;
	private int nbBombe;

	public InfoJeu(Gameplay partie,int nbBombe){
		super();
		this.partie = partie;
		this.nbBombe = nbBombe;
		this.counter = new JLabel("Bombes: "+nbBombe);
		this.counter.setFont(new Font(null, Font.BOLD, 15));
		this.add(this.counter);
	}

	public void updateCounter(int nbEtoiles){
		this.counter.setText("Bombes: "+(this.nbBombe-nbEtoiles) );
	}
}