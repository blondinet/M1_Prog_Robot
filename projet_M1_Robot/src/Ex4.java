import lejos.hardware.Button;

import lejos.hardware.motor.UnregulatedMotor;
import objetPhysic.*;

public class Ex4 {

	Components comp;
	UnregulatedMotor[] motors = {comp.motorB, comp.motorC};
	int[] directions = {0,1}; //0=avant, 1=arrière
	
	public Ex4() {
		comp = new Components();
	}
	
	public void danceAwayYourProblems() {
		Button.waitForAnyPress();
		System.out.println("Drive in a Square\nand Stop\n");
		System.out.println("Press any key to start");
		
	}
	
	public void doStep(Components motors, int directions) { //dir = avant ou arrière
		
		while (Button.getButtons()!=Button.ID_ENTER) {
			int dir= (int)(Math.random()*100);
		}
		
	}
}
