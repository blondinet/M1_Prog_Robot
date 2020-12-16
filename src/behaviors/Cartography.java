package behaviors;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Enregistreur;
import ressources_twister.Robot;

public class Cartography implements Behavior {
	private Robot robot;
	private boolean non_fini;

	public Cartography(Robot r) {
		this.robot = r;
		non_fini = true;
	}

	/**
	 * Prend le contrôle de l'Arbitrator et éxecute la méthode action() si  non_fini est passé à true, ce qui veut dire que le processus n'est pas terminé
	 * non_fini est passé à true par défaut
	 * @return boolean
	 */
	public boolean takeControl() {
		return non_fini; // activé en premier par défaut
	}

	/**
	 * Indique que le mode cartographie est activé et commence la navigation à travers la map
	 * Le point de départ est la case rouge dans le coin dans la carte et le point d'arrivée correspond à la dernière case non visitée
	 * Lors de la navigation, le robot va mettre à jour sa carte en mémoire de la carte du jeu en attribuant les couleurs aux cases respectives
	 * Lorsque le robot arrive au bout d'une ligne, il tourne afin de se positionner à la première case de la deuxième ligne et avance jusqu'à la dernière case de cette ligne.
	 * Réitère l'opération jusqu'à la dernière case de la carte x: 5 et y: 7
	 */
	public void action() {
		LCD.clear();
		LCD.drawString("Cartographie", 0, 0);
		LCD.drawString("Chargement...", 0, 1);
		int dist_case = 133;
		Enregistreur.resetMemoireMap();
		LCD.clear(1);
		LCD.clear(2);
		LCD.drawString("Place moi sur case", 0, 1); // case rouge dans le coin en bas à gauche
		LCD.drawString("rouge & touche moi", 0, 2);
		Button.waitForAnyPress();
		
		// début de la cartographie
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
					// comportements pour passer à la case ou ligne suivante
					if(i < this.robot.getMapMemoire().lengthX()-1) {
						// comportement classique
						this.robot.getPilot().travel(dist_case);
					} else {
						// comportement pour la dernière case de la ligne
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
					this.robot.clearPrint(); 	// Permet d'effacer la carte précédente
					this.robot.printMap();		 // Permet d'afficher la carte actuelle
					// comportements pour passer à la case ou ligne suivante
					if(i > 0) {
						// comportement classique
						this.robot.getPilot().travel(dist_case);
						//arbitrator_case_suivante.go();
					} else {
						// comportement pour la dernière case de la ligne
						///// ROTATION
						this.robot.getPilot().travel(dist_case);
						this.robot.getPilot().rotate(-340);
						//Button.waitForAnyPress();
					
					}
				}
			}
		}
		
		// Sauvegarde de la map en mémoire
		Enregistreur.serialiserMap(this.robot.getMapMemoire());
		// Chargement de la map en mémoire
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
