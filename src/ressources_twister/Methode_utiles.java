package ressources_twister;

import java.util.Random;

import lejos.hardware.Button;

/**
 * Classe qui contient des fonctions utilitaires Cette classe n'a pas �t�
 * utilis� Elle contient des m�thodes de g�n�ration de vitesses al�atoires
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Methode_utiles {

	/**
	 * Methode qui g�n�re un nombre al�atoire entre -140 et 140 qui prend en
	 * param�tre une valeur minimale et une valeur maximale des nombres a g�n�rer
	 * 
	 * @param low  valeur min
	 * @param high valeur max
	 * @return la vitesse al�atoire g�n�r�e
	 */
	public static int giveRandomLimite(int low, int high) {
		Random r = new Random();

		int result = r.nextInt(high - low) + low;

		return result;
	}

	/**
	 * Methode qui g�n�re un nombre al�atoire entre -140 et 140
	 * 
	 * @return la vitesse al�atoire g�n�r�e
	 */
	public static int giveRandomSpeedMax() {
		Random rnd = new Random();
		int speed = rnd.nextInt() % 140;
		return speed;
	}

	/**
	 * Methode qui g�n�re un nombre al�atoire
	 * 
	 * @return la vitesse al�atoire g�n�r�e
	 */
	public static int giveRandomSpeed() {
		Random rnd = new Random();
		int speed = rnd.nextInt();
		return speed;
	}

}
