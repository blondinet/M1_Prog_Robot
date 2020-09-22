package general_methods;

import java.util.Random;

public class Methode_Utiles {

	public static int giveRandomLimite(int low, int high) {
		Random r = new Random();
	
		int result = r.nextInt(high-low) + low;
	
		return result;
	}
	
	/**
	 * Methode qui donnera une vitesse random entre -140 et 140
	 */
	public static int giveRandomSpeedMax() {
		Random rnd = new Random();
		int speed = rnd.nextInt() % 140;
		return speed;
	}

	public static int giveRandomSpeed() {
		Random rnd = new Random();
		int speed = rnd.nextInt();
		return speed;
	}
}
