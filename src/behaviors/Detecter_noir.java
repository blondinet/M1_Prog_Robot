package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Robot;

public class Detecter_noir implements Behavior {
	private Robot robot;
<<<<<<< HEAD
	private int compteur;
=======
>>>>>>> 54fc46921a5a2e02c45d01d15b92e826cfd75e0f
	
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
			System.out.println(robot.comparerCouleur().getName());
			this.robot.setPowerAllMotor(100);
			this.robot.getLeftW().forward();
			this.robot.getRightW().forward();
	}


	@Override
	public void suppress() {
		Delay.msDelay(600);
		robot.stopAllMotor();
		robot.closeAllSensors();
		System.exit(1);
	}

}
