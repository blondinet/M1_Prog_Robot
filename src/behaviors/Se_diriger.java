package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import ressources_twister.Robot;

/**
 * Comportement qui s'�xecute en premier via sa priorit� dans le tableau de l'Arbitrator
 * Le robot va avancer successivement jusqu'� un Waypoint d�fini dans une boucle et indiquer qu'il est arriv�
 * Ce comportement est utilis� pour tester la navigation
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
	 * Navigue seccessivement jusqu'� un objet Waypoint and notifie de son arriv� au Waypoint
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
	 * Arr�te le robot si le bouton droit est appuy�
	 */
	@Override
	public void suppress() {
		Button.RIGHT.isDown();
		this.robot.stopAllMotor();
		
	}

}
