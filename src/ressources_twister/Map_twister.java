package ressources_twister;

import java.io.Serializable;

public class Map_twister implements Serializable {
	private Case_twister[][] map;
	
	/**
	 * Constructeur de la classe
	 * Une couleur est composé de 3 valeurs numériques allant de 0 à 255 (rouge, vert, bleu)
	 */
	public Map_twister() {
		this.map = new Case_twister[5][7];
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
	
}
