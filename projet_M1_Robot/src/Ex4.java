import lejos.hardware.Button;

import lejos.hardware.motor.UnregulatedMotor;
import objetPhysic.*;

public class Ex4 {

	Components comp;

	
	public Ex4() {
		comp = new Components();
	}
	
	public void danceAwayYourProblems() {
		Button.waitForAnyPress();
		System.out.println("DANCE AWAY YOUR PROBLEMS!\n");
		System.out.println("Press any key to start");
		
	}
	
	public void doStep(Components motors, int directions) { //dir = avant ou arrière
		int dir= (int)(Math.random()*100);
		while (Button.getButtons()!=Button.ID_ENTER) {
			
		}
		
	}
}
