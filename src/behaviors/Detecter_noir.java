package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Robot;

public class Detecter_noir implements Behavior {
	private Robot robot;
	
	public Detecter_noir(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		// Conditions de lancement du comportement
		return robot.comparerCouleur()==robot.getNoir();
	}

	@Override
	public void action() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
			

	}


	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}

}
