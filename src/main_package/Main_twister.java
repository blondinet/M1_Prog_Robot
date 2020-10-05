package main_package;

import behaviors.*;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;

public class Main_twister {

	public static void main(String[] args) {
		Robot robot = new Robot();
		System.out.println("Bonjour ! Je m'appelle Jon !\nVeuillez patienter...");
		
		Behavior b1 = new Drive_forward(robot);
		Behavior b2 = new Hit_wall(robot);
		Behavior b3 = new Stop_if_critical_battery(robot);
		Behavior b4 = new Bouton_stop(robot);
		Behavior[] bArray = {b1,b2,b3,b4};
		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
		
		// Onn arrête tous les moteurs et tous les capteurs avant de quitter le programme
		robot.stopAllMotor();
		robot.closeAllSensors();
	}

}
