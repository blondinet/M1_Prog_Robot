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
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.LEFT.isDown();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		robot.stopAllMotor();
		robot.closeAllSensors();
		System.exit(1);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		robot.stopAllMotor();
		robot.closeAllSensors();
		System.exit(1);
	}

}
