package main_package;

import java.io.Serializable;

import behaviors.*;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.*;

public class Main_twister implements Serializable {

	public static void main(String[] args) {
		// Début
		LCD.clear();
		LCD.setAutoRefresh(true);
		LCD.drawString("Bonjour !", 3, 0);
		LCD.drawString("Chargement...", 0, 1);
		// Chargement des constructeurs
		Robot robot = new Robot();
		// Attente pour la construction
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		Button.waitForAnyPress();

		// Apprentissage des couleurs
		LCD.clear();
		boolean choix_couleurs = true;
		while (choix_couleurs) {
			LCD.drawString("Apprendre Couleurs", 0, 0);
			LCD.drawString("Manuel ou Fichier?", 0, 1);
			LCD.drawString("Manu(G) / Fich(D)", 0, 2);
			Button.waitForAnyPress();
			if (Button.LEFT.isDown()) {
				// Apprentissage manuel
				robot.learnColors();
				choix_couleurs = false;
			} else if (Button.RIGHT.isDown()) {
				// Apprentissage grâce à un fichier en mémoire
				robot.setCouleurMemoire(Enregistreur.deserialiserCouleurs());
				choix_couleurs = false;
			} else {
				LCD.clear();
				LCD.drawString("PAS BONNE TOUCHE><", 0, 0);
				Delay.msDelay(2000);
				LCD.clear();
			}
		}
		
		// Cartographie
		LCD.clear();
		boolean choix_carto = true;
		while (choix_carto) {
			LCD.drawString("Test Cartographie", 0, 0);
			LCD.drawString("Manuel ou Fichier?", 0, 1);
			LCD.drawString("Manu(G) / Fich(D)", 0, 2);
			Button.waitForAnyPress();
			if (Button.LEFT.isDown()) {
				try {
					Behavior b_stop = new Bouton_stop(robot);
					Behavior b_cartography = new Cartography(robot);
					Behavior[] comportements_cartography = {b_cartography,b_stop}; // du moins prioritaire au plus prioritaire
					Arbitrator arbitrator_cartography = new Arbitrator(comportements_cartography);
					robot.clearPrint();  // Permet d'effacer le message du constructeur de l'Arbitrator
					arbitrator_cartography.go();
				} catch (Exception exception) {
					LCD.clear(5);
					LCD.drawString("ERREUR CARTOGRAPHY", 0, 5);
					// exception.printStackTrace();
				}
				choix_carto = false;
			} else if (Button.RIGHT.isDown()) {
				// Chargement de la map en mémoire
				robot.setMapMemoire(Enregistreur.deserialiserMap());
				robot.printMap();
				Button.waitForAnyPress();
				robot.clearPrint();
				choix_carto = false;
				break;
			} else {
				LCD.clear();
				LCD.drawString("PAS BONNE TOUCHE><", 0, 0);
				Delay.msDelay(2000);
				LCD.clear();
			}
		}

		// Fin du programme
		LCD.drawString("Au revoir !", 3, 7);
		Button.waitForAnyPress();
		LCD.clear();
		// On arrête tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
