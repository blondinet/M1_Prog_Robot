package behaviors;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;

/**
 * Comportement qui s'�xecute en premier via sa priorit� dans le tableau de l'Arbitrator
 * Le robot va avancer jusqu'� ce que l'on appuie sur la touche �chappe 
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Drive_forward implements Behavior {
	private Robot robot;

	public Drive_forward(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		return true; // activ� en premier par d�faut
	}

	/**
	 * D�finie la puissance des moteurs du robot � 200 et avance
	 * 200 tours de roue par secondes
	 */
	@Override
	public void action() {
		this.robot.setPowerAllMotor(200);
		this.robot.getLeftW().forward();
		this.robot.getRightW().forward();

	}
	
	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}
}
