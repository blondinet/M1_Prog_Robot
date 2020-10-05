package behaviors;

import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import general_methods.Robot_Component;

public class HitWall implements Behavior{
	private EV3TouchSensor touch;
	//private float[] sample;
	private float[] sample = new float[] {0};
	private Robot_Component robot;
	
	public HitWall(EV3TouchSensor ts, float[] s, Robot_Component r) {
		this.touch = ts;
		this.sample = s;
		this.robot = r;
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
		Delay.msDelay(300);					// Tourne pendant 300 millisecondes
		this.robot.getRightW().stop(true);	// Arrête l'autre roue
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.robot.getLeftW().stop(true);	// Stop les 2 moteurs
		this.robot.getRightW().stop(true);
	}

}
