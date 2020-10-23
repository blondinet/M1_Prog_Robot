package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.Navigator;
import ressources_twister.Robot;

public class goTo implements Behavior {

	private Navigator nav;
	
	public goTo(Navigator n) {
		this.nav = n;
	}
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action() {
		nav.goTo(200,200);
	}

	@Override
	public void suppress() {
		nav.stop();
		
	}

}
