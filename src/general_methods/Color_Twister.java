package general_methods;

public class Color_Twister {
	private short red;
	private short green;
	private short blue;
	
	private final short MIN = 0;
	private final short MAX = 255;
	/**
	 * Constructeur de la classe
	 * Une couleur est composé de 3 valeurs numériques allant de 0 à 255 (rouge, vert, bleu)
	 */
	public Color_Twister(short r, short g, short b) {
		this.red = Limiteur(r);
		this.green = Limiteur(g);
		this.blue = Limiteur(b);
	}
	
	// Getters & Setters
	public short getRed() {
		return red;
	}
	public void setRed(short red) {
		this.red = Limiteur(red);
	}
	public short getGreen() {
		return green;
	}
	public void setGreen(short green) {
		this.green = Limiteur(green);
	}
	public short getBlue() {
		return blue;
	}
	public void setBlue(short blue) {
		this.blue = Limiteur(blue);
	}
	
	// Méthodes
	/**
	 * Permet de limiter la valeur d'une couleur (toujours supérieure à 0 et inférieur à 255) 
	 * @param a valeur d'une des 3 couleurs RGB
	 * @return valeur comprise entre 0 et 255
	 */
	private short Limiteur(short a) {
		if(a < MIN) {return 0;}
		else if (a > MAX) {return 255;}
		else return a;
	}
}
