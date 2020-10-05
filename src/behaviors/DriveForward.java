package behaviors;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class DriveForward implements Behavior{
	
	public boolean takeControl() { return true; }
	public void suppress() {
	Motor.B.stop(true);
	Motor.C.stop(true);
	}
	public void action() {
	Motor.B.forward();
	Motor.C.forward();
	}
}
