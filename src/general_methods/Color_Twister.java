package general_methods;

public class Color_Twister {
	private int red;
	private int green;
	private int blue;
	
	private final short MIN = 0;
	private final short MAX = 255;
	/**
	 * Constructeur de la classe
	 * Une couleur est compos� de 3 valeurs num�riques allant de 0 � 255 (rouge, vert, bleu)
	 */
	public Color_Twister(int r, int g, int b) {
		this.red = Limiteur(r);
		this.green = Limiteur(g);
		this.blue = Limiteur(b);
	}
	
	// Getters & Setters
	public int getRed() {
		return red;
	}
	public void setRed(short red) {
		this.red = Limiteur(red);
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(short green) {
		this.green = Limiteur(green);
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(short blue) {
		this.blue = Limiteur(blue);
	}
	
	// M�thodes
	/**
	 * Permet de limiter la valeur d'une couleur (toujours sup�rieure � 0 et inf�rieur � 255) 
	 * @param a valeur d'une des 3 couleurs RGB
	 * @return valeur comprise entre 0 et 255
	 */
	private int Limiteur(int a) {
		if(a < MIN) {return 0;}
		else if (a > MAX) {return 255;}
		else return a;
	}
	
	/**
	 * Calcul de la distance euclidienne entre 2 couleurs
	 * @param x 2e couleur
	 * @return la distance
	 */
	public double DistanceEuclidienneCouleur(Color_Twister x) {
		return Math.sqrt( Math.pow((this.red-x.getRed()), 2) + Math.pow((this.green-x.getGreen()), 2) + Math.pow((this.blue-x.getBlue()), 2));	
	}
	
}
