package ressource_exercice;


import general_methods.*;
import lejos.hardware.*;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;


/**
 * Cette classe permet de répondre aux question du td 1 
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
	 * Fonction qui permet de répondre à la question 1
	 */
	public void sayHello() { 
	    System.out.println("bonjour Madame, bonjour Monsieur !"); 
	    Button.waitForAnyPress(); 
	}

	/**
	* Methode qui permet de répondre à la question 2
	* 
	* On attend que l'user clique sur un boutton
	* Le robot ira a la vitesse definit par le parametre speed pendant un temps définit par delay
	* Puis on arrete et ferme les moteurs avant de signaler la fin par un bip
	* 
	* @param speed INT C'est le paramètre de la vitesse
	* @param delay INT C'est le paramètre d'attente avant l'arret du vehicule
	*/
	public void driveForward(int speed, int delay) {
		
		System.out.println("I'm ready to drop fast"); // on demande à l'utilisateur de s'executer
		Button.waitForAnyPress();
		
        robot.setPowerAllMotor(speed); // On donne la puissance aux moteurs 
        Delay.msDelay(delay);
        
        robot.stopAllMotor(); // Séquence de fin
        robot.closeAllMotor();
        
        Sound.beepSequence(); // Signal de fin
    }
	
	/**
	 * Methode qui permet de repondre à la question 3
	 * 
	 * On va effectuer un carré avec le robot
	 * pour 4 iteration, le robot obtiendra la vitesse définit par speed puis roulera le temps défini par delay
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
	        
	        robot.getMotorOne().forward(); // on prépare les moteurs pour repartir en ligne droite
	        robot.getMotorTwo().forward();
        }
		
		robot.stopAllMotor(); // Séquence de fin
		robot.closeAllMotor();
        
        System.out.println("BYE BYE <3");
        Sound.beepSequence(); // Signal de fin
	}

	/**
	 * Methode qui permet de répondre à la question 4
	 */
	public void danceAwayYourProblems() {
		System.out.println("DANCE AWAY YOUR PROBLEMS!\n");
		System.out.println("Press any key to start");
		
		Button.waitForAnyPress();

		System.out.println("Ca marche, je danse");
		
		
		
		Thread motorDoStep1 = new Thread() {
			public void run() {
				
				System.out.println("test Thread 1");
				int b_speed = Methode_Utiles.giveRandomSpeedMax();
				
				//b_speed = 50;
				robot.doStep(robot.getMotorOne(), b_speed);
		    }
		};
		
		
		Thread motorDoStep2 = new Thread() {
			public void run() {

				System.out.println("Thread 2");
				int c_speed = Methode_Utiles.giveRandomSpeedMax();
				
				//c_speed = 50;
				robot.doStep(robot.getMotorTwo(), c_speed);
		    }
		};
		
		
		
		while(!Button.LEFT.isDown()) {
			motorDoStep1.start();
			motorDoStep2.start();
			
			try{
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		Delay.msDelay((Methode_Utiles.giveRandomSpeed() % 400) + 100);
		}
	
	
	/**
	 * 
	 * @param rob
	 */
	public void letsHug() {
		Button.waitForAnyPress();
		System.out.println(robot.getMotorOne().getPosition()); //permet de connaître la position par rapport à position initiale
		robot.getMotorOne().rotateTo(-90);
		System.out.println("Dans mes bras!");
		Button.waitForAnyPress();
		robot.getMotorOne().rotateTo(90);
	}
	
	
	
	/*
	 * Texte écrit au tableau par le prof
	public class Arreter implements Behavior{
		private EV3TouchSensor touch;
		private Arbitrator arby;
		
		public Arreter(EV3TouchSensir e) {
			this.touch = e;
		}
		
		public boolean takeControl() {
			return Button.LEFT.isDown();
		}
		
		public void suppress(){
		}
		
		public void setArbi(Arbitrator a){
			this.arby=a;
		}
		
		public void action(){
			this.touch.close();
			robot.getMotorOne.stop(true);
			robot.getMotorTwo.stop(true);
			if(a != null){
				arby.stop();
			}
			System.exit(0);
		}
		
		// inserez dans main \\
		Behavior b3 = new Arbitrator(to);
		Behavior[] bArray = new Behavior[]{b3}
		Arbitrator aby = .......
		b3.setArbi(arby);
		arby.go();		
	}
	*/
	
	
	
	
	//do step(motor) ?
	/* 56 *26 ZR */
}
