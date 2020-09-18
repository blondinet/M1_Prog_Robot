package objetPhysic;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;

public class Components { //should be private
	public UnregulatedMotor motorB;
	public UnregulatedMotor motorC;
	
	public Components() {
		motorB = new UnregulatedMotor(MotorPort.B); //moteur B = roue de gauche
        motorC = new UnregulatedMotor(MotorPort.C);
	}
}
