package main_package;

import behaviors.*;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.*;

public class Main_twister {

	public static void main(String[] args) {
		//Début
		LCD.clear();
		LCD.drawString("Bonjour !",0,0);
		LCD.drawString("Chargement...",0,1);
		LCD.refresh();
		//Chargement des constructeurs
		Robot robot = new Robot();
		Map_twister map = new Map_twister();
		//Attente pour la construction
		LCD.drawString("Pret ! ...",0,3);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		
		//Apprentissage des couleurs
		//robot.learnColors();
		// Chargement de la liste des couleurs en mémoire
		robot.setCouleurMemoire(Enregistreur.deserialiserCouleurs());
				
		//System.out.println("Execution Main_twister !\nVeuillez patienter...");
		
		//robot.cartography(map);
		//map[0][0].setCouleur(robot.comparerCouleur());
		
		//test comparer couleur
		while(!Button.DOWN.isDown()) {
			LCD.clear();
			LCD.drawString("test :",0,0);
			LCD.drawString("comparer couleur",0,1);
			LCD.drawString("appuyez 1 touche",0,2);
			LCD.drawString("(tout sauf bas)",0,3);
			LCD.refresh();
			Button.waitForAnyPress();
			LCD.clear();
			robot.comparerCouleur();
		}
		
		LCD.clear();
		LCD.drawString("Chargement",0,0);
		LCD.drawString("comportements...",2,1);
		LCD.refresh();
		Behavior b1 = new Drive_forward(robot);
		Behavior b2 = new Hit_wall(robot);
		Behavior b3 = new Stop_if_critical_battery(robot);
		Behavior b4 = new Bouton_stop(robot);
		Behavior[] bArray = {b1,b2,b3,b4}; //du moins prioritaire au plus
		//Arbitrator arby = new Arbitrator(bArray);
		Behavior[] bArrayTest = {b1,b2,b4};
		Arbitrator arby = new Arbitrator(bArrayTest);
		LCD.drawString("Pret ! ...",0,2);
		Button.waitForAnyPress();
		LCD.clear();
		arby.go();
		
		// Onn arrête tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
