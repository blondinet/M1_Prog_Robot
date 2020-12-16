package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import ressources_twister.Robot;

/**
 * Comportement qui s'éxecute en parallèle des autres comportements
 * Le robot va attribuer une couleur à chaque case dans sa carte en mémoire, en parallèle d'un comportement qui le fait avancer
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
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
	/**
	 * Attribue une couleur à chaque case que le robot rencontre, en suivant une trajectoire définie
	 */
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
