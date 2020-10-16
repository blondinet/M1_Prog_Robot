package ressources_twister;
import ressources_twister.*;

public class Map_twister {
	private Case_twister[][] map;
	
	/**
	 * Constructeur de la classe
	 * Une couleur est composé de 3 valeurs numériques allant de 0 à 255 (rouge, vert, bleu)
	 */
	public Map_twister() {
		this.map = new Case_twister[5][7];
		for(int i=0;i<5;i++) {
			for(int j=0;j<7;j++) {
				map[i][j] = new Case_twister(i,j);
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
	

}
