package ressources_twister;

import java.io.Serializable;

public class Map_twister implements Serializable {
	private Case_twister[][] map;
	
	/**
	 * Constructeur de la classe
	 * Une couleur est compos� de 3 valeurs num�riques allant de 0 � 255 (rouge, vert, bleu)
	 */
	public Map_twister() {
		this.map = new Case_twister[5][7];
		for(Case_twister[] l:this.map) {
			for(Case_twister c:l) {
				c = new Case_twister();
			}
		}
		// Cr�ation d'un tableau de couleur de 5x7 cases
	}

	public Case_twister[][] getMap() {
		return map;
	}

	public void setMap(Case_twister[][] map) {
		this.map = map;
	}
	
}
