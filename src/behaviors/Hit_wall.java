package behaviors;

import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Robot;

public class Hit_wall implements Behavior{
	private EV3TouchSensor touch;
	private float[] sample;
	private Robot robot;
	
	public Hit_wall(Robot r) {
		this.robot = r;
		this.touch = r.getTouchSensor();
		this.sample = new float[] {0};
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		touch.fetchSample(sample, 0);
		return sample[0]==1;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		this.robot.getLeftW().backward();	// Change le sens des moteurs pour reculer
		this.robot.getRightW().backward();
		Delay.msDelay(1000);	// Recule pendant une seconde
		this.robot.getLeftW().stop(true);	// Arrête la rouge gauche pour tourner
		Delay.msDelay(1000);					// Tourne pendant 1000 millisecondes
		this.robot.getRightW().stop(true);	// Arrête l'autre roue
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.robot.getLeftW().stop(true);	// Stop les 2 moteurs
		this.robot.getRightW().stop(true);
	}

}
