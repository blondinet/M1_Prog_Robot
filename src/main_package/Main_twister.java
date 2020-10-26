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
		//Début
		LCD.clear();
		LCD.setAutoRefresh(true);
		LCD.drawString("Bonjour !",0,0);
		LCD.drawString("Chargement...",0,1);
		//Chargement des constructeurs
		Robot robot = new Robot();
		Map_twister map = new Map_twister();
		//Attente pour la construction
		LCD.drawString("Pret ! Touche Moi.",0,2);
		Button.waitForAnyPress();
		LCD.clear();
		
		// Apprentissage des couleurs
		LCD.clear();
		boolean choix_couleurs = true;
		while(choix_couleurs) {
			LCD.drawString("Apprendre Couleurs",0,0);
			LCD.drawString("Memoire ?",0,1);
			LCD.drawString("Non(G) / Oui(D)",0,2);
			Button.waitForAnyPress();
			if (Button.LEFT.isDown()){
				// Apprentissage manuel
				robot.learnColors();
				choix_couleurs = false;
			} else if (Button.RIGHT.isDown()) {
				// Apprentissage grâce à un fichier en mémoire
				robot.setCouleurMemoire(Enregistreur.deserialiserCouleurs());
				choix_couleurs = false;
			} else {
				LCD.clear();
				LCD.drawString("PAS BONNE TOUCHE><",0,0);
				Delay.msDelay(2000);
				LCD.clear();
			}
		}
		
		// Apprentissage de la carte
		//robot.cartography();
		// Chargement de la map en mémoire
		//robot.setMapMemoire(Enregistreur.deserialiserMap());
		
		//test comparer couleur
		/*
		while(!Button.DOWN.isDown()) {
			LCD.clear();
			LCD.drawString("test :",0,0);
			LCD.drawString("comparer couleur",0,1);
			LCD.drawString("appuyez 1 touche",0,2);
			LCD.drawString("(tout sauf bas)",0,3);
			//LCD.refresh();
			Button.waitForAnyPress();
			LCD.clear();
			robot.comparerCouleur();
		}
		*/
			
		/*
		// Comportements
		try {
		LCD.clear();
		LCD.drawString("Chargement",0,1);
		LCD.drawString("comportements...",2,2);
		LCD.refresh();
		Behavior b_forward = new Drive_forward(robot);
		Behavior b2 = new Hit_wall(robot);
		Behavior b3 = new Stop_if_critical_battery(robot);
		Behavior b_stop = new Bouton_stop(robot);
		Behavior b_goTo = new Se_diriger(robot.getNav());
		//Behavior[] bArray = {b1,b2,b3,b4}; //du moins prioritaire au plus
		//Arbitrator arby = new Arbitrator(bArray);
		Behavior[] bArrayTest = {b_forward,b_goTo,b_stop};
		Arbitrator arbyTest = new Arbitrator(bArrayTest);
		//LCD.clear();
		LCD.clear(0);
		LCD.clear(1);
		LCD.drawString("Pret ! ...",0,3);
		Button.waitForAnyPress();
		//arby.go();
		arbyTest.go();
		} catch(Exception e){
			LCD.drawString("Erreurs comportements",0,4);
		}
		*/
		
		LCD.drawString("Quitter ...",3,7);
		Button.waitForAnyPress();
		// On arrête tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
