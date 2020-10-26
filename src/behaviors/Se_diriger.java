package behaviors;

import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
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
		for (int i=20; i<1000; i+=20){
			nav.goTo(i,i);
		}
		
		LCD.drawString("Arrive !",0,3);
		//nav.stop();
	}

	@Override
	public void suppress() {
		Button.RIGHT.isDown();
		nav.stop();
		
	}

}
