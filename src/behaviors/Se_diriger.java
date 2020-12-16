package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import ressources_twister.Robot;

/**
 * Comportement qui s'éxecute en premier via sa priorité dans le tableau de l'Arbitrator
 * Le robot va avancer successivement jusqu'à un Waypoint défini dans une boucle et indiquer qu'il est arrivé
 * Ce comportement est utilisé pour tester la navigation
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Se_diriger implements Behavior {

	private Robot robot;
	
	public Se_diriger(Robot robot) {
		this.robot=robot;
	}
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Navigue seccessivement jusqu'à un objet Waypoint and notifie de son arrivé au Waypoint
	 */
	@Override
	public void action() {
		for (int i=20; i<1000; i+=20){
			this.robot.getNav().goTo(i,i);
		}
		
		LCD.drawString("Arrive !",0,3);
		//this.robot.getNav().stopAllMotor();
	}

	/**
	 * Arrête le robot si le bouton droit est appuyé
	 */
	@Override
	public void suppress() {
		Button.RIGHT.isDown();
		this.robot.stopAllMotor();
		
	}

}
