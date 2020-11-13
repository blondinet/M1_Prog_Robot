package behaviors;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;

public class Drive_forward implements Behavior {
	private Robot robot;

	public Drive_forward(Robot r) {
		this.robot = r;
	}

	public boolean takeControl() {
		return true; // activé en premier par défaut
	}

	public void action() {
		this.robot.setPowerAllMotor(200);
		this.robot.getLeftW().forward();
		this.robot.getRightW().forward();

	}

	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}
}
