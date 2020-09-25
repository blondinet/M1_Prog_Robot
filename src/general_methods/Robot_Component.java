package general_methods;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;

/**
 * Classe qui permet de d'instancier un robot avec ses composant
 * Nous l'utiliserons afin de programmer le robot physique et de repondre aux question du td
 * 
 * @author blondin
 *
 */
public class Robot_Component {
	private NXTRegulatedMotor motorB;
	private NXTRegulatedMotor motorC;
	
	/**
	 * Constructeur de la classe
	 * Ce constructeur r�cup�re les motor physique et donne leurs valeurs a des variables d'instanciation
	 */
	public Robot_Component() {
		try {
			this.motorB = Motor.B; //moteur B = roue de gauche
			this.motorC = Motor.C;
		}catch(NullPointerException e) {
			System.out.println("Erreur Constructeur");
			System.out.println(e);
		}
	}

	/**
	 * Methode qui permet de donner la m�me valeur de vitesse � tout les moteur
	 * @param speed la vitesse qu'on souhaite donner � tout les moteurs
	 */
	public void setPowerAllMotor(int speed) {
		motorB.setSpeed(speed);
		motorC.setSpeed(speed);
		
	}
	
	/**
	 * Methode qui permet de stopper tous les moteur du robot
	 */
	public void stopAllMotor() {
		motorB.stop();
		motorC.stop();
		
	}
	
	/**
	 * Methode qui permet de fermer tous les moteurs du robot
	 */
	public void closeAllMotor() {
		motorB.close();
		motorC.close();
	}
	
	/**
	 * getteur du moteur B
	 * @return le premier Moteur
	 */
	public NXTRegulatedMotor getMotorOne() {
		return this.motorB;
	}
	
	/**
	 * getteur du moteur C
	 * @return le deuxieme Moteur
	 */
	public NXTRegulatedMotor getMotorTwo() {
		return this.motorC;
	}

	public void doStep(NXTRegulatedMotor motor, int direction) {
		this.closeAllMotor();
		
		if(direction < 0) {
			direction = Math.abs(direction);
			motor.setSpeed(direction);
			motor.backward(); 
		}else {
			motor.setSpeed(direction);
			motor.forward();
		}
		
	}

}
