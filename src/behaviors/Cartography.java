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

	public Cartography(Robot r) {
		this.robot = r;
	}

	public boolean takeControl() {
		return true; // activé en premier par défaut
	}

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
		for (int j=0; j<this.robot.getMapMemoire().lengthY(); j++) {
			if ((j%2)==0) { // si on est sur une colonne paire = robot va dans un sens (aller)
				for (int i=0; i<this.robot.getMapMemoire().lengthX(); i++) {
					// attribution de la couleur de la case
					///// COULEURS
					//this.robot.getMapMemoire().getCase(i, j).setCouleur(this.robot.comparerCouleur());
					// comportements pour passer à la case ou ligne suivante
					if(i < this.robot.getMapMemoire().lengthX()-1) {
						// comportement classique
						this.robot.getPilot().travel(dist_case);
					} else {
						// comportement pour la dernière case de la ligne
						///// ROTATION
						this.robot.getPilot().travel(dist_case);
						this.robot.getPilot().rotate(340);
						
					}
					
				}
			} else { // si on est sur une colonne impaire = robot va dans l'autre sens (retour)
				for (int i=this.robot.getMapMemoire().lengthX()-1; i>=0; i--) {
					// attribution de la couleur de la case
					///// COULEURS
					//this.robot.getMapMemoire().getCase(i, j).setCouleur(this.robot.comparerCouleur());
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
					
					}
				}
			}
		}
		
		//arbitrator_ligne_suivante.go();
		
			
		
		// Le placer sur la case rouge en bas à gauche
		/*
		this.getMapMemoire().getCase(0, 0).setCouleur(comparerCouleur());
		arbitrator_cartographie.go();
		for (int x = 0; x < getMapMemoire().lengthX(); x++) {
			this.getMapMemoire().getCase(x, 0).setCouleur(comparerCouleur());
			arbitrator_cartographie.go();
		}

		// Sauvegarde de la map en mémoire
		// Enregistreur.serialiserMap(this.getMapMemoire());
		// Chargement de la map en mémoire
		// this.getMapMemoire() = Enregistreur.deserialiserMap();
		// robot.setMapMemoire(Enregistreur.deserialiserMap());

		// Fin de la cartographie
		LCD.clear();
		LCD.drawString("Fin cartographie.", 0, 0);
		Delay.msDelay(3000);
		LCD.clear();*/

	}

	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}
}
