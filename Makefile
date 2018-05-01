# Auteurs: Jordan Demartin & Rémy Gaudru
# Variables

JC = javac


CLASSES = \
        ActionFenetre.java \
        BoutonInfoJeu.java \
        BoutonsMenu.java \
        Case.java \
        CliqueCase.java \
        Gameplay.java \
        Grille.java \
        InfoJeu.java \
        Menu.java \
        Main.java
 
# Regles essentiels 
 
ActionFenetre.class : ActionFenetre.java Gameplay.class#
	$(JC) ActionFenetre.java

BoutonInfoJeu.class : BoutonInfoJeu.java Menu.class Gameplay.class#
	$(JC) BoutonInfoJeu.java

BoutonsMenu.class : BoutonsMenu.java Menu.class Gameplay.class#
	$(JC) BoutonsMenu.java

Case.class : Case.java CliqueCase.class Grille.class#
	$(JC) Case.java

CliqueCase.class : CliqueCase.java Case.class Grille.class#
	$(JC) CliqueCase.java 

Gameplay.class : Gameplay.java InfoJeu.class Grille.class#
	$(JC) Gameplay.java

Grille.class : Grille.java Case.class Gameplay.class InfoJeu.class#
	$(JC) Grille.java

InfoJeu.class : InfoJeu.java Gameplay.class BoutonInfoJeu.class ActionFenetre.class#
	$(JC) InfoJeu.java

Menu.class : Menu.java BoutonsMenu.class#
	$(JC) Menu.java 

Main.class : Main.java Menu.class#
	$(JC) Main.java

# Regles optionnelles
 
clean :
	rm *.class

test :
	java Main
