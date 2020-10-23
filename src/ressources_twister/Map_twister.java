package ressources_twister;

import java.io.Serializable;

public class Map_twister implements Serializable {
	private Case_twister[][] map;
	final static private int X = 5;
	final static private int Y = 7;
	/**
	 * Constructeur de la classe
	 * Une couleur est composé de 3 valeurs numériques allant de 0 à 255 (rouge, vert, bleu)
	 */
	public Map_twister() {
		this.map = new Case_twister[X][Y];
		for(Case_twister[] l:this.map) {
			for(Case_twister c:l) {
				c = new Case_twister();
			}
		}
		// Création d'un tableau de couleur de 5x7 cases
	}

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
}
