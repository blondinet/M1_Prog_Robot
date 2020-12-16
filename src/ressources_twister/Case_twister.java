package ressources_twister;

import java.io.Serializable;

/**
 * Classe qui permet d'instancier une case.
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Case_twister implements Serializable {
	// Variable
	private Color_twister couleur;
	private boolean libre;
	private int posX;
	private int posY;

	// Comportement
	/**
	 * Constructeur d'une case indépendamment d'une carte (sans position x et y).
	 * Par défaut une case est libre et sa couleur est nulle.
	 */
	public Case_twister() {
		this.couleur = null;
		this.libre = true;
	}

	/**
	 * Constructeur d'une case dans une carte (avec sa position x et y). Par défaut
	 * une case est libre et sa couleur est nulle.
	 * 
	 * @param x position x de la case
	 * @param y position y de la case
	 */
	public Case_twister(int x, int y) {
		this.posX = x;
		this.posY = y;
		this.couleur = null;
		this.libre = true;
	}

	// Getters & Setters
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

	// Méthodes
	/**
	 * Permet d'afficher une case sous forme de chaîne de caractères. Une case
	 * s'affiche sous la forme suivante :
	 * "Case(0,0), couleur : rouge"
	 */
	public String toString() {
		return "Case(" + this.posX + "," + this.posY + "), couleur : " + this.couleur;
	}

}
