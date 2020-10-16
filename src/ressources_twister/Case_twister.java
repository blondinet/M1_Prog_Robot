package ressources_twister;

public class Case_twister {
	private Color_twister couleur;
	private boolean libre;
	private int posX;
	private int posY;

	public Case_twister(int i, int j) {
		// TODO Auto-generated constructor stub
		this.couleur = new Color_twister();
		this.libre = true;
		this.posX = i;
		this.posY = j;
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
	

}
