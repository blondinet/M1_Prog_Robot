package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;
import ressources_twister.Color_twister;
import ressources_twister.Robot;

public class Detecter_noir implements Behavior {
	private Robot robot;
	
	public Detecter_noir(Robot r) {
		this.robot = r;
	}

	@Override
	public boolean takeControl() {
		// Conditions de lancement du comportement
		return robot.comparerCouleur() == robot.getNoir();
	}

	@Override
	public void action() {
		// Le robot doit �tre pos� sur la case rouge au bord de la map
		this.robot.setPowerAllMotor(200);
		this.robot.getLeftW().forward();
		this.robot.getRightW().forward();
		Delay.msDelay(500);
		/*for(int x=0;x<this.robot.getMapMemoire().lengthX();x++) {
			this.robot.getMapMemoire().getCase(x, 0).setCouleur(this.robot.comparerCouleur());
			this.robot.setPowerAllMotor(200);
			this.robot.getLeftW().forward();
			this.robot.getRightW().forward();
		}*/
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
		Delay.msDelay(500);
	}

	@Override
	public void suppress() {
		this.robot.getLeftW().stop(true);
		this.robot.getRightW().stop(true);
	}

}
