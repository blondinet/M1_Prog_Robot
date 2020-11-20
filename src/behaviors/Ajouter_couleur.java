package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;

 class Ajouter_couleur implements Behavior{
	
	private Robot robot;
	 
	public Ajouter_couleur(Robot r) {
		this.robot=r;
	}

	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		for (int i=0; i<this.robot.getMapMemoire().lengthX(); i++) {
			LCD.drawString(this.robot.comparerCouleur().getName(), 0, 0);
			this.robot.getMapMemoire().getCase(i, 0).setCouleur(this.robot.comparerCouleur());
		}
	}
	
	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}
	
}
