package behaviors;

import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Robot;

public class Detecter_noir implements Behavior {
	private Robot robot;
	private int compteur;
	
	public Detecter_noir(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		// Conditions de lancement du comportement
		return robot.comparerCouleur()==robot.getNoir();
	}

	@Override
	public void action() {
		compteur=0;
		LCD.clear();
		this.robot.getLeftW().forward();
		this.robot.getRightW().forward();
		Delay.msDelay(1000);
		while (robot.comparerCouleur()!=robot.getNoir()){
			// tant que le robot voit autre chose que du noir, il avance
			System.out.println(robot.comparerCouleur().getName());
			this.robot.getMapMemoire().getCase(compteur, 0).setCouleur(this.robot.comparerCouleur());
			
			if(robot.comparerCouleur()==robot.getNoir()) {
				// si il voit du noir, on passe à la case suivante
				compteur = compteur + 1;
			}
			
			//if (robot.comparerCouleur()==robot.getBlanc() && compteur>longueur_ligne) {
				// si le robot a fini la ligne
				// s'arrêter au bout d'une ligne
				// tourner puis recommencer une autre ligne
			//}
		}
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);

	}


	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}

}
