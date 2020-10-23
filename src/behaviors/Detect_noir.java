package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Color_twister;
import ressources_twister.Robot;

public class Detect_noir implements Behavior {
	private Robot robot;
	
	public Detect_noir(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return robot.comparerCouleur() == robot.getNoir();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Delay.msDelay(1000);
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
