package behaviors;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Enregistreur;
import ressources_twister.Robot;

/**
 * Comportement qui s'�xecute d�s qu'on lance le mode cartographie
 * Le robot va commencer sa navigation � partir de la case rouge dans le coin et finir dans l'autre coin en diagonale de la carte
 * Il va attribuer une couleur � chaque case de la map au fur et-�-mesure de son parcours
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Cartography implements Behavior {
	private Robot robot;
	private boolean non_fini;

	public Cartography(Robot r) {
		this.robot = r;
		non_fini = true;
	}

	/**
	 * Prend le contr�le de l'Arbitrator et �xecute la m�thode action() si  non_fini est pass� � true, ce qui veut dire que le processus n'est pas termin�
	 * non_fini est pass� � true par d�faut
	 * @return boolean
	 */
	public boolean takeControl() {
		return non_fini; // activ� en premier par d�faut
	}

	/**
	 * Indique que le mode cartographie est activ� et commence la navigation � travers la map
	 * Le point de d�part est la case rouge dans le coin dans la carte et le point d'arriv�e correspond � la derni�re case non visit�e
	 * Lors de la navigation, le robot va mettre � jour sa carte en m�moire de la carte du jeu en attribuant les couleurs aux cases respectives
	 * Lorsque le robot arrive au bout d'une ligne, il tourne afin de se positionner � la premi�re case de la deuxi�me ligne et avance jusqu'� la derni�re case de cette ligne.
	 * R�it�re l'op�ration jusqu'� la derni�re case de la carte x: 5 et y: 7
	 * Enregistre la carte avec les couleurs dans un fichier .ser
	 */
	public void action() {
		LCD.clear();
		LCD.drawString("Cartographie", 0, 0);
		LCD.drawString("Chargement...", 0, 1);
		int dist_case = 133;
		Enregistreur.resetMemoireMap();
		LCD.clear(1);
		LCD.clear(2);
		LCD.drawString("Place moi sur case", 0, 1); // case rouge dans le coin en bas � gauche
		LCD.drawString("rouge & touche moi", 0, 2);
		Button.waitForAnyPress();
		
		// d�but de la cartographie
		this.robot.getPilot().setAngularSpeed(100);
		this.robot.getPilot().setLinearSpeed(100);
		first:
		for (int j=0; j<this.robot.getMapMemoire().lengthY(); j++) {
			if ((j%2)==0) { // si on est sur une colonne paire = robot va dans un sens (aller)
				for (int i=0; i<this.robot.getMapMemoire().lengthX(); i++) {
					// attribution de la couleur de la case
					this.robot.getMapMemoire().getCase(i, j).setCouleur(this.robot.comparerCouleur());
					this.robot.clearPrint();
					this.robot.printMap();
					// comportements pour passer � la case ou ligne suivante
					if(i < this.robot.getMapMemoire().lengthX()-1) {
						// comportement classique
						this.robot.getPilot().travel(dist_case);
					} else {
						// comportement pour la derni�re case de la ligne
						///// ROTATION
						this.robot.getPilot().travel(dist_case);
						this.robot.getPilot().rotate(100);
						//Button.waitForAnyPress();
						
					}
					
				}
			} else { // si on est sur une colonne impaire = robot va dans l'autre sens (retour)
				for (int i=this.robot.getMapMemoire().lengthX()-1; i>=0; i--) {
					// attribution de la couleur de la case
					this.robot.getMapMemoire().getCase(i, j).setCouleur(this.robot.comparerCouleur());
					this.robot.clearPrint(); 	// Permet d'effacer la carte pr�c�dente
					this.robot.printMap();		 // Permet d'afficher la carte actuelle
					// comportements pour passer � la case ou ligne suivante
					if(i > 0) {
						// comportement classique
						this.robot.getPilot().travel(dist_case);
						//arbitrator_case_suivante.go();
					} else {
						// comportement pour la derni�re case de la ligne
						///// ROTATION
						this.robot.getPilot().travel(dist_case);
						this.robot.getPilot().rotate(-340);
						//Button.waitForAnyPress();
					
					}
				}
			}
		}
		
		// Sauvegarde de la map en m�moire
		Enregistreur.serialiserMap(this.robot.getMapMemoire());
		// Chargement de la map en m�moire
		// this.getMapMemoire() = Enregistreur.deserialiserMap();
		// robot.setMapMemoire(Enregistreur.deserialiserMap());

		// Fin de la cartographie
		non_fini=false;
	}

	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}
}
