package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Color_twister;
import ressources_twister.Map_twister;
import ressources_twister.Robot;

public class Detecter_noir implements Behavior {
	private Robot robot;
	
	public Detecter_noir(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		// Conditions de lancement du comportement
		LCD.clear();
		LCD.drawString(robot.comparerCouleur().getName(), 0, 0);
		return robot.comparerCouleur()==robot.getNoir();
	}

	@Override
	public void action() {


		/*LCD.drawString(this.robot.comparerCouleur().getName(), 0, 0);
		for (int i=0; i<this.robot.getMapMemoire().lengthX(); i++) {
			for (j=0; j<this.robot.getMapMemoire().lengthY();j++) {
				
			}
			
			this.robot.getMapMemoire().getCase(i, 0).setCouleur(this.robot.comparerCouleur());*/
			//Delay.msDelay(500);
			this.robot.getLeftW().stop(true);
			this.robot.getRightW().stop(true);
		}
	

	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}

}
