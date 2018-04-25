public class Main{
	public static void main(String[] args){
		Gameplay partie = new Gameplay(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
		partie.setVisible(true);
	}
}