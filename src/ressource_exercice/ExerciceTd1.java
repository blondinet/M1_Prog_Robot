package ressource_exercice;

import java.util.Random;

import general_methods.*;
import lejos.hardware.*;
import lejos.utility.Delay;


/**
 * Cette classe permet de r�pondre aux question du td 1 
 * 
 * @author blondin
 *
 */
public class ExerciceTd1 extends Ex_Model{
	
	/**
	 * Le constructeur 
	 * @param r le robot qu'on va utiliser
	 */
	public ExerciceTd1(Robot_Component r) {
		super(r);
	}
	
	/**
	 * Fonction qui permet de r�pondre � la question 1
	 */
	public void sayHello() { 
	    System.out.println("bonjour Madame, bonjour Monsieur !"); 
	    Button.waitForAnyPress(); 
	}

	/**
	* Methode qui permet de r�pondre � la question 2
	* 
	* On attend que l'user clique sur un boutton
	* Le robot ira a la vitesse definit par le parametre speed pendant un temps d�finit par delay
	* Puis on arrete et ferme les moteurs avant de signaler la fin par un bip
	* 
	* @param speed INT C'est le param�tre de la vitesse
	* @param delay INT C'est le param�tre d'attente avant l'arret du vehicule
	*/
	public void driveForward(int speed, int delay) {
		
		System.out.println("I'm ready to drop fast"); // on demande � l'utilisateur de s'executer
		Button.waitForAnyPress();
		
        robot.setPowerAllMotor(speed); // On donne la puissance aux moteurs 
        Delay.msDelay(delay);
        
        robot.stopAllMotor(); // S�quence de fin
        robot.closeAllMotor();
        
        Sound.beepSequence(); // Signal de fin
    }
	
	/**
	 * Methode qui permet de repondre � la question 3
	 * 
	 * On va effectuer un carr� avec le robot
	 * pour 4 iteration, le robot obtiendra la vitesse d�finit par speed puis roulera le temps d�fini par delay
	 * 
	 * 
	 * @param speed le parametre de la vitesse
	 * @param delay le parametre du temps
	 */
	public void turnAround(int speed, int delay) {
		System.out.println("Drive in a Square\nand Stop\n");
		System.out.println("Press any key to start");
		Button.waitForAnyPress(); 
		
		for (int i=0; i<4; i++) {
     
			robot.setPowerAllMotor(speed); // on donne la puissance aux moteurs
			Delay.msDelay(delay);
	        
	        robot.stopAllMotor(); // on arrete les moteurs
	        
	        robot.getMotorOne().backward(); // on effectue une rotation de 90%
	        robot.getMotorTwo().forward();
	        robot.setPowerAllMotor(50);	
	        Delay.msDelay(500);
	        
	        robot.stopAllMotor(); // on arrete les moteurs
	        
	        robot.getMotorOne().forward(); // on pr�pare les moteurs pour repartir en ligne droite
	        robot.getMotorTwo().forward();
        }
		
		robot.stopAllMotor(); // S�quence de fin
		robot.closeAllMotor();
        
        System.out.println("BYE BYE <3");
        Sound.beepSequence(); // Signal de fin
	}

	/**
	 * Methode qui permet de r�pondre � la question 4
	 */
	public void danceAwayYourProblems() {
		System.out.println("DANCE AWAY YOUR PROBLEMS!\n");
		System.out.println("Press any key to start");
		Button.waitForAnyPress();
		Random rnd = new Random(42);
		int b_speed = rnd.nextInt() % 140;
		int c_speed = rnd.nextInt() % 140;
		doStep(motorB, b_speed);
		doStep(motorC, c_speed);
		Delay.msDelay((rnd.nextInt() % 400) + 100);
		}
		
		while(!Button.LEFT.) {
			int b_speed = Methode_Utiles.giveRandomSpeedMax();
			int c_speed = Methode_Utiles.giveRandomSpeedMax();
			robot.doStep(motor, b_speed);
			

			Methode_Utiles.giveRandomSpeedMax();
		}
		
	}
	

	
	//do step(motor) ?
	/* 56 *26 ZR */
}
