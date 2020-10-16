package main_package;
//import ressource_exercice.*;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import ressources_twister.Robot;

//import general_methods.*;

/**
 * Classe qui permet d'executer les différentes réponses aux exercices
 * @author blondin
 *
 */
public class Main {
	
	/**
	 * On instancie un robot avec la classe Robot_Component
	 * Ce robot sera utilisé dans les ExerciceTd afin de programmer le robot physique
	 * Dans les classe ExerciceTD (qui hérite de Ex_Model) on trouvera de multiples 
	 * méthodes pour répondre aux différentes question du Td
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("Bonjour !\nVeuillez patienter...");
		LCD.clear();
		//LCD.drawString("message",largeur,hauteur);
		//largeur : 0 à 18
		// hauteur : 0 à 7
		//LCD.drawString("123456789012345678",0,0);
		//LCD.drawString("Bonjour !\nVeuillez patienter...",3,0);
		LCD.drawString("test:",0,0);
		LCD.drawString("Bonjour !",0,2);
		LCD.drawString("Veuillez patienter...",0,3);
		LCD.refresh();
		//Robot r = new Robot();
		//System.out.println("Appuyez pour continuer.");
		//LCD.clear();
		LCD.drawString("Appuyez pour",0,4);
		LCD.drawString("continuer.",0,5);
		//LCD.drawString("123456789012345678",0,0);
		LCD.refresh();
		Button.waitForAnyPress();
		//ExerciceTd1 td1 = new ExerciceTd1(new Robot_Component());
		
		//r.learnColors();
		
		//LCD.clear();
		//LCD.drawString("Bonjour",3,4);
		//LCD.refresh();
		//Button.waitForAnyPress();
		
		//LCD.clear();
		//LCD.drawString("Je suis Jon",3,4);
		//LCD.refresh();
		//Button.waitForAnyPress();
		//td1.danceAwayYourProblems();
		
		//td1.letsHug();
		
		//td1.capteurCouleur();
		//td1.capteurCouleurRGB();
		
		//td1.sayHello(); //Exercice 1
	
		//td1.driveForward(50, 5000); //Exercice 2 (speed percentage, delay in milliseconds)
		
		//td1.turnAround(50, 2000); //Exercice 3

		//r.closeAllMotor();
		//r.closeAllSensors();
	}
	
}
