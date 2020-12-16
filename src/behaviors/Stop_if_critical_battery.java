package behaviors;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;
import lejos.hardware.Battery;

/**
 * Comportement qui s'éxecute en parallèle
 * Le robot va s'arrêter et quitter le programme en cours si le niveau de batterie atteint un seuil trop bas
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Stop_if_critical_battery implements Behavior{
	private Robot robot;
	
	public Stop_if_critical_battery(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() { return true; }
	
	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}
	
	/**
	 * Instancie un objet Battery et récupère le voltage en millivolte de la batterie
	 * Si le voltage est inférieur à une certaine valeur, arrête tous les moteurs du robot
	 */
	@Override
	public void action() {
		Battery b = new Battery();
		if (b.getVoltageMilliVolt() < 90) {
			this.robot.getArms().stop(true);
			this.robot.getLeftW().stop(true);
			this.robot.getRightW().stop(true);
			System.exit(1);
		}
	}

}
