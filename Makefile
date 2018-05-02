# Auteurs: Jordan Demartin & Rémy Gaudru
# Variables

JC = javac

# Regles essentiels
Main.class : Main.java Menu.class
	$(JC) Main.java

Menu.class : Menu.java BoutonsMenu.class
	$(JC) Menu.java

BoutonsMenu.class : BoutonsMenu.java Gameplay.class #Menu.class
	$(JC) BoutonsMenu.java

Gameplay.class : Gameplay.java InfoJeu.class Grille.class
	$(JC) Gameplay.java

InfoJeu.class : InfoJeu.java BoutonInfoJeu.class ActionFenetre.class #Gameplay.class
	$(JC) InfoJeu.java

ActionFenetre.class : ActionFenetre.java #Gameplay.class
	$(JC) ActionFenetre.java

BoutonInfoJeu.class : BoutonInfoJeu.java #Menu.class #Gameplay.class
	$(JC) BoutonInfoJeu.java

Grille.class : Grille.java Case.class InfoJeu.class #Gameplay.class
	$(JC) Grille.java

Case.class : Case.java CliqueCase.class #Grille.class
	$(JC) Case.java

CliqueCase.class : CliqueCase.java #Case.class #Grille.class
	$(JC) CliqueCase.java

# Regles optionnelles

clean :
	rm *.class
	rm save.dat

test :
	java Main
