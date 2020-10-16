package main_package;

import behaviors.*;
import lejos.hardware.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.*;

public class Main_twister {

	public static void main(String[] args) {
		Robot robot = new Robot();
		//Map_twister map = new Map_twister();
		Case_twister[][] map = new Case_twister[5][7];
		
		System.out.println("Execution Main_twister !\nVeuillez patienter...");
		
		robot.learnColors();
		robot.cartography();
		//map[0][0].setCouleur(robot.comparerCouleur());
		
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
		
		// Onn arrête tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
