package ressources_twister;

import java.io.Serializable;

/**
 * Classe qui permet d'instancier une carte de jeu de Twister.
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Map_twister implements Serializable {
	// Variables
	private Case_twister[][] map;
	final static private int X = 5; // Largeur du tableau
	final static private int Y = 7; // Longueur du tableau
	// Constructeur

	/**
	 * Constructeur de la classe carte. Créé un tableau à double entrée de 5 cases
	 * sur 7
	 * 
	 */
	public Map_twister() {
		this.map = new Case_twister[X][Y];
		// Création d'un tableau de couleur de 5x7 cases
		for (int y = 0; y < Y; y++) {
			for (int x = 0; x < X; x++) {
				Case_twister c = new Case_twister(x, y);
				this.map[x][y] = c;
			}
		}
	}

	// Getters & Setters
	public Case_twister[][] getMap() {
		return map;
	}

	public void setMap(Case_twister[][] map) {
		this.map = map;
	}

	public Case_twister getCase(int x, int y) {
		return map[x][y];
	}

	public int lengthX() {
		return X;
	}

	public int lengthY() {
		return Y;
	}

	// Méthodes
	/**
	 * Permet d'afficher la map sous forme de chaîne de caractères. Une map
	 * s'affichera sous la forme suivante :
	 * "|x|x|x|x|x|x|x|
	 *  |x|x|x|x|x|x|x|
	 *  |x|x|x|x|x|x|x|
	 *  |x|x|x|x|x|x|x|
	 *  |x|x|x|x|x|x|x|"
	 */
	public String toString() {
		String result = "";

		for (int x = 0; x < X; x++) {
			result += "|";
			for (int y = 0; y < Y; y++) {
				if (this.map[x][y].getCouleur() != null) {
					result += this.map[x][y].getCouleur().getName().charAt(0);
				} else {
					result += "/";
				}
				result += "|";

			}
			result += "\n";
		}

		return result;
	}
}
