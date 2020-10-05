package behaviors;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import general_methods.Robot_Component;

public class BoutonStop implements Behavior{

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.LEFT.isDown();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.exit(1);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		System.exit(1);
	}

}
