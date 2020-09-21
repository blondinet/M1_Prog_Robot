import java.util.Random;

import general_methods.*;
import lejos.hardware.Button;

import lejos.hardware.motor.UnregulatedMotor;

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
	
	public void doStep(Components motor) {
		
		
			
	}
		
	
	public int generateRandomNb(int low, int high) {
		Random r = new Random();
		int result = r.nextInt(high-low) + low;
		return result;
	}
}
