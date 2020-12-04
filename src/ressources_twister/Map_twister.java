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
		/*
		for(Case_twister[] l:this.map) {
			for(Case_twister c:l) {
				c = new Case_twister();
			}
		}*/
		// Création d'un tableau de couleur de 5x7 cases
		for (int y=0; y<Y; y++) {
			for (int x=0; x<X; x++) {
				Case_twister c = new Case_twister(x,y);
				this.map[x][y] = c;
				
			}
		}
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
	
	public String toString() {
		String result="";
		
		for (int x=0; x<X; x++) {
			result+="|";
			for (int y=0; y<Y; y++) {
				if(this.map[x][y].getCouleur()!= null) {
					result+=this.map[x][y].getCouleur().getName().charAt(0);
				}
				else {
					result+="/";
				}
				result+="|";
				
			}
			result+="\n";
		}
		
		return result;
	}
}
