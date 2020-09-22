package general_methods;

import java.util.Random;

public class Methode_Utiles {

	public static int giveRandom(int low, int high) {
		
		Random r = new Random();
		int result = r.nextInt(high-low) + low;
		return result;
	}
	
}
