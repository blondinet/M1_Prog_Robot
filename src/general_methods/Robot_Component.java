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
	private NXTRegulatedMotor[] tabMotor;
	
	/**
	 * Constructeur de la classe
	 * Ce constructeur récupère les motor physique et donne leurs valeurs a des variables d'instanciation
	 */
	public Robot_Component() {
		try {
			tabMotor[0] = Motor.B; //moteur B = roue de gauche
			tabMotor[1] = Motor.C;
		}catch(NullPointerException e) {
			System.out.println("Erreur Constructeur");
			System.out.println(e);
		}
	}

	/**
	 * Methode qui permet de donner la même valeur de vitesse à tout les moteur
	 * @param speed la vitesse qu'on souhaite donner à tout les moteurs
	 */
	public void setPowerAllMotor(int speed) {
		for(NXTRegulatedMotor iM : tabMotor) {
			iM.setSpeed(speed);
		}
	}
	
	/**
	 * Methode qui permet de stopper tous les moteur du robot
	 */
	public void stopAllMotor() {
		for(NXTRegulatedMotor iM : tabMotor) {
			iM.stop();
		}
	}
	
	/**
	 * Methode qui permet de fermer tous les moteurs du robot
	 */
	public void closeAllMotor() {
		for(NXTRegulatedMotor iM : tabMotor) {
			iM.close();
		}
	}
	
	/**
	 * getteur du moteur B
	 * @return le premier Moteur
	 */
	public NXTRegulatedMotor getMotorOne() {
		return this.tabMotor[0];
	}
	
	/**
	 * getteur du moteur C
	 * @return le deuxieme Moteur
	 */
	public NXTRegulatedMotor getMotorTwo() {
		return this.tabMotor[1];
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
