package general_methods;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Classe qui permet de d'instancier un robot avec ses composant
 * Nous l'utiliserons afin de programmer le robot physique et de repondre aux question du td
 * 
 * @author blondin
 *
 */
public class Robot_Component {
	private UnregulatedMotor[] tabMotor;
	
	/**
	 * Constructeur de la classe
	 * Ce constructeur récupère les motor physique et donne leurs valeurs a des variables d'instanciation
	 */
	public Robot_Component() {
		tabMotor[0] = new UnregulatedMotor(MotorPort.B); //moteur B = roue de gauche
		tabMotor[1] = new UnregulatedMotor(MotorPort.C);
	}

	/**
	 * Methode qui permet de donner la même valeur de vitesse à tout les moteur
	 * @param speed la vitesse qu'on souhaite donner à tout les moteurs
	 */
	public void setPowerAllMotor(int speed) {
		for(UnregulatedMotor iM : tabMotor) {
			iM.setPower(speed);
		}
	}
	
	/**
	 * Methode qui permet de stopper tous les moteur du robot
	 */
	public void stopAllMotor() {
		for(UnregulatedMotor iM : tabMotor) {
			iM.stop();
		}
	}
	
	/**
	 * Methode qui permet de fermer tous les moteurs du robot
	 */
	public void closeAllMotor() {
		for(UnregulatedMotor iM : tabMotor) {
			iM.close();
		}
	}
	
	/**
	 * getteur du moteur B
	 * @return le premier Moteur
	 */
	public UnregulatedMotor getMotorOne() {
		return this.tabMotor[0];
	}
	
	/**
	 * getteur du moteur C
	 * @return le deuxieme Moteur
	 */
	public UnregulatedMotor getMotorTwo() {
		return this.tabMotor[1];
	}
}
