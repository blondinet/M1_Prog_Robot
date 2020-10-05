package behaviors;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Battery;
import general_methods.Robot_Component;

public class stopIfCriticalBattery implements Behavior{

	public boolean takeControl() { return true; }
	
	
	public void suppress() {
		Motor.B.stop(true);
		Motor.C.stop(true);
	}
	
	public void action() {
		
	}
	
	public void action(Robot_Component r) {
		Battery b = new Battery();
		if (b.getVoltageMilliVolt() < 90) {
			r.getArms().stop(true);
			r.getLeftW().stop(true);
			r.getRightW().stop(true);
		}
	}

}