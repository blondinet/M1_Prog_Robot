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
		LCD.drawString("Veuillez patienter...",0,1);
		LCD.refresh();
		//Chargement des constructeurs
		Robot robot = new Robot();
		Map_twister map = new Map_twister();
		//Attente pour la construction
		LCD.drawString("Press to continue",0,3);
		//LCD.drawString("Appuyez pour",0,3);
		//LCD.drawString("continuer.",3,4);
		LCD.refresh();
		Button.waitForAnyPress();
		
		//Apprentissage des couleurs
		robot.learnColors();
		
		//Cartographie
		robot.cartography();
		
		Button.waitForAnyPress();
		/*
		System.out.println("Execution Main_twister !\nVeuillez patienter...");
		//test comparer couleur
		while(!Button.DOWN.isDown()) {
			System.out.println("test comparer couleur, appuyez sur une touche (sauf celle du bas)");
			Button.waitForAnyPress();
			robot.comparerCouleur();
		}
		
		Behavior b1 = new Drive_forward(robot);
		Behavior b2 = new Hit_wall(robot);
		Behavior b3 = new Stop_if_critical_battery(robot);
		Behavior b4 = new Bouton_stop(robot);
		Behavior[] bArray = {b1,b2,b3,b4}; //du moins prioritaire au plus
		Behavior[] bArrayTest = {b1,b2,b4};
		Arbitrator arby = new Arbitrator(bArrayTest);
		Button.waitForAnyPress();
		arby.go();
		
		*/
		// On arrête tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
