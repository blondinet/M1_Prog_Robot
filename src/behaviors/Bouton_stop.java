package behaviors;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Robot;

public class Bouton_stop implements Behavior{
	private Robot robot;

	public Bouton_stop(Robot r) {
		this.robot = r;
	}
	/**
	 * Prend le contr�le de l'Arbitrator et �xecute la m�thode action() si le boutton �chape du robot est appuy�
	 * @return boolean
	 */
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		//return Button.LEFT.isDown();
		return Button.ESCAPE.isDown();
	}

	@Override
	/**
	 * Ferme tous les moteurs et tous les sensors du robot et quitte le programme en cours
	 */
	public void action() {
		// TODO Auto-generated method stub
		this.robot.stopAllMotor();
		this.robot.closeAllSensors();
		System.exit(1);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.robot.stopAllMotor();
		this.robot.closeAllSensors();
		System.exit(1);
	}

}
