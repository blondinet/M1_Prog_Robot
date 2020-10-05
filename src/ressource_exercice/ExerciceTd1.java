package ressource_exercice;


import general_methods.*;
import lejos.hardware.*;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
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
	    System.out.println("Bonjour Madame, Bonjour Monsieur !\nAppuyez sur une touche svp.");
	    System.out.println("Hello !");
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
	        
	        robot.getLeftW().backward(); // on effectue une rotation de 90%
	        robot.getRightW().forward();
	        robot.setPowerAllMotor(50);	
	        Delay.msDelay(500);
	        
	        robot.stopAllMotor(); // on arrete les moteurs
	        
	        robot.getLeftW().forward(); // on prépare les moteurs pour repartir en ligne droite
	        robot.getRightW().forward();
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
		
		
		
		Thread motorDoStepB = new Thread() {
			public void run() {
				
				System.out.println("test Thread 1");
				int b_speed = Methode_Utiles.giveRandomSpeedMax();
				
				//b_speed = 50;
				robot.doStep(robot.getLeftW(), b_speed);
		    }
		};
		
		Thread motorDoStepC = new Thread() {
			public void run() {

				System.out.println("Thread 2");
				int c_speed = Methode_Utiles.giveRandomSpeedMax();
				
				//c_speed = 50;
				robot.doStep(robot.getRightW(), c_speed);
		    }
		};
		
		
		
		while(!Button.LEFT.isDown()) {
			//motorDoStepB.start();
			//motorDoStepC.start();
			
			int b_speed = Methode_Utiles.giveRandomSpeedMax();
			robot.doStep(robot.getLeftW(), b_speed);
			
			int c_speed = Methode_Utiles.giveRandomSpeedMax();
			robot.doStep(robot.getRightW(), c_speed);
		}		
		Delay.msDelay((Methode_Utiles.giveRandomSpeed() % 400) + 100);
		this.robot.closeAllMotor();
	}
	
	
	/**
	 * 
	 * @param rob
	 */
	public void letsHug() {
		System.out.println("Press any key to start");
		
		try {
			Button.waitForAnyPress();
			System.out.println(robot.getArms().getPosition()); //permet de connaître la position par rapport à position initiale
			
			robot.getArms().rotateTo(-90);
			System.out.println("Dans mes bras!");
			Button.waitForAnyPress();
			robot.getArms().rotateTo(90);
		}catch(NullPointerException e){
			System.out.print("JE MARCHE PAS");
		}
		
	}
	
	/**
	 * Methode qui capte la couleur perçu par le senseur
	 */
	public void capteurCouleur() {
		//System.out.println("Je vais instancier les couleurs dans ma memoire !");
		System.out.println("Je vais apprendre les couleurs\nAppuie pour continuer");
		Button.waitForAnyPress();
		robot.learnColors();
		
		int valeur;String color;
		
		valeur=this.robot.getColorID();
		switch (valeur) {
			case Color.BLACK : color = "noire";
			case Color.BLUE : color = "bleue";
			case Color.BROWN : color = "marron";
			case Color.CYAN : color = "cyan";
			case Color.DARK_GRAY : color = "gris fonce";
			case Color.GRAY : color = "grise";
			case Color.GREEN : color = "verte";
			case Color.LIGHT_GRAY : color = "gris clair";
			case Color.MAGENTA : color = "magenta";
			case Color.ORANGE : color = "orange";
			case Color.PINK : color = "rose";
			case Color.RED : color = "rouge";
			case Color.WHITE : color = "blanche";
			case Color.YELLOW : color = "jaune";
			default : color = "inconnue";
			}
			
		System.out.println("ColorID = "+valeur+ ".\nC'est du " +color+ " !");
		System.out.println("Press key to quit...");
		
		robot.getColorSensor().close();
		Button.waitForAnyPress();
		
	}
	

	/**
	 * Methode qui capte la couleur perçue par le senseur (version RGB)
	 */
	public void capteurCouleurRGB() {
		System.out.println("Capteur Couleur RGB\nPress any key to start...");
		Button.waitForAnyPress();
		
		this.robot.learnColors();
		
		System.out.println("Placez moi sur une couleur.\nPress key to continue...");
		Button.waitForAnyPress();
		this.robot.detectColor();
		
		System.out.println("Press key to quit...");
		Button.waitForAnyPress();
		robot.getColorSensor().close();		// fermeture des capteurs
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
}
