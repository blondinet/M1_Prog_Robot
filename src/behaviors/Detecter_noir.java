package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Robot;

/**
 * Comportement qui s'éxecute lorsque le robot voit la couleur noir
 * Le robot va afficher le nom de la couleur visitée et avancer pendant 600 millisecondes afin d'arriver au milieu de la case adjacente
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Detecter_noir implements Behavior {
	private Robot robot;
	private int compteur;
	
	public Detecter_noir(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		// Conditions de lancement du comportement
		return robot.comparerCouleur()==robot.getCouleur("noir");
	}

	/**
	 * Indique le nom de la couleur de la case en train d'être visitée
	 * S'arrête and avance pendant 600 milliseconds
	 */
	@Override
	public void action() {
			System.out.println(robot.comparerCouleur().getName());
			this.robot.setPowerAllMotor(100);
			this.robot.getLeftW().forward();
			this.robot.getRightW().forward();
			Delay.msDelay(600);
	}


	@Override
	public void suppress() {
		robot.stopAllMotor();
		robot.closeAllSensors();
		System.exit(1);
	}

}
