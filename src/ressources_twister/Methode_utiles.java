package ressources_twister;

import java.util.Random;

import lejos.hardware.Button;

/**
 * Classe qui contient des fonctions utilitaires Cette classe n'a pas été
 * utilisé Elle contient des méthodes de génération de vitesses aléatoires
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Methode_utiles {

	/**
	 * Methode qui génère un nombre aléatoire entre -140 et 140 qui prend en
	 * paramètre une valeur minimale et une valeur maximale des nombres a générer
	 * 
	 * @param low  valeur min
	 * @param high valeur max
	 * @return la vitesse aléatoire générée
	 */
	public static int giveRandomLimite(int low, int high) {
		Random r = new Random();

		int result = r.nextInt(high - low) + low;

		return result;
	}

	/**
	 * Methode qui génère un nombre aléatoire entre -140 et 140
	 * 
	 * @return la vitesse aléatoire générée
	 */
	public static int giveRandomSpeedMax() {
		Random rnd = new Random();
		int speed = rnd.nextInt() % 140;
		return speed;
	}

	/**
	 * Methode qui génère un nombre aléatoire
	 * 
	 * @return la vitesse aléatoire générée
	 */
	public static int giveRandomSpeed() {
		Random rnd = new Random();
		int speed = rnd.nextInt();
		return speed;
	}

}
