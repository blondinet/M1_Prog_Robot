package behaviors;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;
import lejos.hardware.Battery;

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
	 * Instancie un objet Battery et r�cup�re le voltage en millivolte de la batterie
	 * Si le voltage est inf�rieur � une certaine valeur, arr�te tous les moteurs du robot
	 */
	@Override
	public void action() {
		Battery b = new Battery();
		if (b.getVoltageMilliVolt() < 90) {
			this.robot.getArms().stop(true);
			this.robot.getLeftW().stop(true);
			this.robot.getRightW().stop(true);
		}
	}

}
