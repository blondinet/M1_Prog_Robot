package ressources_twister;

import java.io.Serializable;

public class Case_twister implements Serializable{
	private Color_twister couleur;
	private boolean libre;
	private int posX;
	private int posY;
	
	public Case_twister() {
		this.couleur = null;
		this.libre = true;
	}
	
	public Case_twister(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.couleur = null;
		this.libre = true;
	}

	public Color_twister getCouleur() {
		return couleur;
	}

	public void setCouleur(Color_twister couleur) {
		this.couleur = couleur;
	}

	public boolean isLibre() {
		return libre;
	}

	public void setLibre(boolean libre) {
		this.libre = libre;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public String toString() {
		   return "Case(" + this.posX +
			  "," + this.posY +
			  "), couleur : " + this.couleur;
		}
	
}
