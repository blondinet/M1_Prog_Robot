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
		// D�but
		LCD.clear();
		LCD.setAutoRefresh(true);
		LCD.drawString("Bonjour !", 3, 0);
		LCD.drawString("Chargement...", 0, 1);
		// Chargement des constructeurs
		Robot robot = new Robot();
		Map_twister map = new Map_twister();
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
				// Apprentissage gr�ce � un fichier en m�moire
				robot.setCouleurMemoire(Enregistreur.deserialiserCouleurs());
				choix_couleurs = false;
			} else {
				LCD.clear();
				LCD.drawString("PAS BONNE TOUCHE><", 0, 0);
				Delay.msDelay(2000);
				LCD.clear();
			}
		}

		// Apprentissage de la carte
		// robot.cartography();
		// Chargement de la map en m�moire
		// robot.setMapMemoire(Enregistreur.deserialiserMap());

		// Test
		// Comparer couleur
		LCD.clear();
		boolean choix_test = true;
		while (choix_test) {
			LCD.clear();
			LCD.drawString("Test couleurs ?", 0, 0);
			LCD.drawString("Non(G) / Oui(D)", 0, 1);
			Button.waitForAnyPress();
			if (Button.LEFT.isDown()) {
				choix_test = false;
			} else if (Button.RIGHT.isDown()) {
				LCD.clear(1);
				LCD.drawString("Placez moi...", 0, 1);
				LCD.drawString("Pret ! Touche moi.", 0, 2);
				Button.waitForAnyPress();
				LCD.clear();
				robot.comparerCouleur();
			}
		}

		// Comportements
		LCD.clear();
		boolean choix_comp = true;
		while (choix_comp) {
			LCD.drawString("Test Comportements", 0, 0);
			LCD.drawString("Lancer ?", 0, 1);
			LCD.drawString("Non(G) / Oui(D)", 0, 2);
			Button.waitForAnyPress();
			if (Button.RIGHT.isDown()) {
				// Lancement des comportements
				LCD.clear(1);
				LCD.drawString("Chargement...", 0, 1);
				try {
					// Cr�ation des comportements
					Behavior comp_drive_forward = new Drive_forward(robot);
					Behavior comp_hit_wall = new Hit_wall(robot);
					Behavior comp_stop = new Bouton_stop(robot);
					Behavior comp_se_diriger = new Se_diriger(robot.getNav());
					Behavior comp_detecter_noir = new Detecter_noir(robot);
					// Cr�ation de l'arbitrator pour g�rer les comportements
					// Du moins prioritaire au plus prioritaire
					//Behavior[] bArray= {comp_drive_forward, comp_detecter_noir, comp_stop };
					//Arbitrator aby = new Arbitrator(bArray);
					
					// {comp_detecter_noir,comp_drive_forward,comp_stop}; // il avance sans d�tecter la couleur
					// {comp_drive_forward,comp_detecter_noir,comp_stop}; // il d�tecte la couleur
					// sans avancer
					for (int i = 0; i < 7; i++) {
						System.out.println(" "); // Permet d'effacer le message du constructeur de l'Arbitrator
					}
					LCD.drawString("Pret ! Touche moi.", 0, 2);
					Button.waitForAnyPress();
					// Lancement de l'arbitrator
					robot.cartography();
				} catch (Exception exception) {
					LCD.clear(2);
					LCD.drawString("ERREUR BEHAVIORS", 0, 2);
					// exception.printStackTrace();
				}
				choix_comp = false;
			} else if (Button.LEFT.isDown()) {
				// Sinon ne rien faire
				choix_comp = false;
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
		// On arr�te tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
