package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.robotics.navigation.Navigator;
import ressources_twister.Robot;

public class Se_diriger implements Behavior {

	private Navigator nav;
	
	public Se_diriger(Navigator n) {
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
