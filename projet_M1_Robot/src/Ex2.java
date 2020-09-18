import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import objetPhysic.Components;

public class Ex2 {
	Components comp;

	public Ex2() {
		comp = new Components();

	}
	/**
	*@param speed int : paramètre de la vitesse
	*/
	public void driveForward(int speed, int delay) {
		
		System.out.println("I'm ready to drop fast");
		Button.waitForAnyPress();
		
        // set motors to 50% power.
        comp.motorB.setPower(speed); //en pourcentage de vitesse maximale du robot
        comp.motorC.setPower(speed);	
        
        // wait 2 seconds.
        Delay.msDelay(delay);
        
        // stop motors with brakes on. 
        comp.motorB.stop();
        comp.motorC.stop();
        
        // free up motor resources. 
        comp.motorB.close();
        comp.motorC.close();
        
        Sound.beepSequence(); // we are done.
    }
}
