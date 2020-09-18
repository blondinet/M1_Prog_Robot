import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import objetPhysic.*;

public class Ex3 {
	Components comp;

	public Ex3() {
		comp = new Components();
	}
	
	public void turnAround(int speed, int delay) {
		Button.waitForAnyPress();
		System.out.println("Drive in a Square\nand Stop\n");
		System.out.println("Press any key to start");
		
		for (int i=0; i<4; i++) {
     
			// set motors' power.
			comp.motorB.setPower(speed); //en pourcentage de vitesse maximale du robot
	        comp.motorC.setPower(speed);	
	        
	        // wait 2 seconds.
	        Delay.msDelay(delay);
	        
	        // stop motors with brakes on. 
	        comp.motorB.stop();
	        comp.motorC.stop();
	        
	        // turn right by reversing the right motor.
	        comp.motorB.backward();
	        comp.motorC.forward();
	        
	        // set motors' power.
	        comp.motorB.setPower(50); //il est en train de tourney
	        comp.motorC.setPower(50);	
	        
	        // adjust time to get a 90% turn.
	        Delay.msDelay(500);
	        
	        comp.motorB.stop();
	        comp.motorC.stop();
	        
	        //set right motor back to forward motion.
	        comp.motorB.forward();
	        comp.motorC.forward();
        }
		
		
		// free up motor resources. 
        comp.motorB.close();
        comp.motorC.close();
        
        System.out.println("BYE BYE <3");
        Sound.beepSequence(); // we are done.
	}
}
