package ressources_twister;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Color_twister {
	private String name;
	private int red;
	private int green;
	private int blue;
	
	private final int MIN = 0;
	private final int MAX = 255;
	/**
	 * Constructeur de la classe
	 * Une couleur est composé de 3 valeurs numériques allant de 0 à 255 (rouge, vert, bleu)
	 */
	public Color_twister(String n, int r, int g, int b) {
		this.name = n;
		this.red = Limiteur(r);
		this.green = Limiteur(g);
		this.blue = Limiteur(b);
	}
	
	// Getters & Setters
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = Limiteur(red);
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = Limiteur(green);
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = Limiteur(blue);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int[] getRGBcode() {
		int[] code = new int[3];
		try {
			code[0] = this.red;
			code[1] = this.green;
			code[2] = this.blue;
		}catch(NullPointerException e) {
			LCD.clear();
			System.out.println("Erreur !\nLe robot n'a pas encore appris cette couleur !");
		}
		return code;
	}
	public void getRGB() {
		LCD.clear();
		LCD.drawString("Couleur = "+this.name,0,0);
		LCD.drawString("R : "+this.red+" ;",2,1);
		LCD.drawString("G : "+this.green+" ;",2,2);
		LCD.drawString("B : "+this.blue+".",2,3);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
	}
	@Override
	public String toString() {
		return "Couleur = "+this.name+"\n R : "+this.red+" ;\n G : "+this.green+" ;\n B : "+this.blue+".";
		
	}
	// Méthodes
	/**
	 * Permet de limiter la valeur d'une couleur (toujours supérieure à 0 et inférieur à 255) 
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
	 * @return la distance entre les couleurs
	 */
	public double DistanceEuclidienneCouleur(Color_twister x) {
		return Math.sqrt( Math.pow((this.red-x.getRed()), 2) + Math.pow((this.green-x.getGreen()), 2) + Math.pow((this.blue-x.getBlue()), 2));	
	}
	
}
