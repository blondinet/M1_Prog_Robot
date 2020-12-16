package ressources_twister;

import java.io.Serializable;
import java.util.ArrayList;

import behaviors.*;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

/**
 * Classe qui permet d'instancier un robot avec ses composants. Nous
 * l'utiliserons afin de programmer le robot physique.
 * 
 * @author Lucille Dumont & William Tardot
 *
 */
public class Robot implements Serializable {
	// Variables
	// moteurs
	private NXTRegulatedMotor arms; // bras
	private NXTRegulatedMotor leftW; // roue gauche
	private NXTRegulatedMotor rightW; // roue droite
	// capteurs
	private EV3TouchSensor touchS; // capteur tactile
	private EV3GyroSensor gyroS; // capteur gyroscope
	private EV3ColorSensor colorS; // capteur de couleur
	private EV3UltrasonicSensor sonarS; // capteur utrasonique
	// mémoire
	private Color_twister rouge, bleu, vert, orange, blanc, noir; // couleurs de la map
	private ArrayList<Color_twister> memoire_couleurs = new ArrayList<Color_twister>(); // liste des couleurs
																						// (enregistrable)
	private Map_twister memoire_map = new Map_twister(); // environnement sur lequel le robot se déplace (enregistrable)
	// navigation
	private Case_twister position; // case dans laquelle se situe le robot
	private Chassis chassis; // chassis du robot
	private MovePilot pilot; // pilote du robot
	private Navigator nav; // navigateur du robot

	// Constructeur
	/**
	 * Constructeur de la classe. Ce constructeur instancie les différentes
	 * variables du robot.
	 */
	public Robot() {
		// instanciation des moteurs
		try {
			this.arms = Motor.A; // moteur A = bras
			this.leftW = Motor.B; // moteur B = roue de gauche
			this.rightW = Motor.C; // moteur C = roue de droite
		} catch (NullPointerException e) {
			LCD.clear();
			System.out.println("Erreur Constructeurs : " + e);
		}
		// instanciation des capteurs
		try {
			this.touchS = new EV3TouchSensor(SensorPort.S1);
			this.gyroS = new EV3GyroSensor(SensorPort.S2);
			this.colorS = new EV3ColorSensor(SensorPort.S3);
			this.sonarS = new EV3UltrasonicSensor(SensorPort.S4);

		} catch (NullPointerException e) {
			LCD.clear();
			System.out.print("Erreur capteurs : " + e);
		}
		// instanciation du chassis & du navigator
		try {
			Wheel roueG = WheeledChassis.modelWheel(this.leftW, 56.).offset(-60);
			Wheel roueD = WheeledChassis.modelWheel(this.rightW, 56.).offset(60);
			this.chassis = new WheeledChassis(new Wheel[] { roueG, roueD }, 2);
			this.pilot = new MovePilot(chassis);
			this.nav = new Navigator(this.pilot);
		} catch (Exception e) {
			LCD.clear();
			System.out.print("Erreur navigator : " + e);
		}
	}

	// Getters & Setters
	/**
	 * Getteur du moteur de la roue gauche
	 * 
	 * @return le moteur de la roue gauche
	 */
	public NXTRegulatedMotor getLeftW() {
		return this.leftW;

	}

	/**
	 * Getteur du moteur de la roue droite
	 * 
	 * @return le moteur de la roue droite
	 */
	public NXTRegulatedMotor getRightW() {
		return this.rightW;

	}

	/**
	 * Getteur du moteur contrôlant les bras
	 * 
	 * @return le moteur des bras
	 */
	public NXTRegulatedMotor getArms() {
		return this.arms;

	}

	/**
	 * Getteur du capteur de couleur du robot
	 * 
	 * @return le capteur de couleur
	 */
	public EV3ColorSensor getColorSensor() {
		return this.colorS;
	}

	/**
	 * Getteur du capteur tactile du robot
	 * 
	 * @return le capteur tactile
	 */
	public EV3TouchSensor getTouchSensor() {
		return this.touchS;
	}

	/**
	 * Getteur du capteur gyroscopique du robot
	 * 
	 * @return le capteur gyroscopique
	 */
	public EV3GyroSensor getGyroSensor() {
		return this.gyroS;
	}

	/**
	 * Getteur du capteur ultrasonique du robot
	 * 
	 * @return le capteur ultrasonique
	 */
	public EV3UltrasonicSensor getSonarSensor() {
		return this.sonarS;
	}

	/**
	 * Getteur de la couleur percue par le capteur de couleur
	 * 
	 * @return ID de la couleur
	 */
	public int getColorID() {
		int res = colorS.getColorID();
		return res;
	}

	/**
	 * Getteur de l'état de la map dans la mémoire du robot
	 * 
	 * @return la carte
	 */
	public Map_twister getMapMemoire() {
		return this.memoire_map;
	}

	/**
	 * Setteur de l'état de la map dans la mémoire du robot
	 * 
	 * @param m la carte
	 */
	public void setMapMemoire(Map_twister m) {
		this.memoire_map = m;
	}

	/**
	 * Getteur de la liste de couleurs dans la mémoire du robot
	 * 
	 * @return la liste des couleurs apprises
	 */
	public ArrayList<Color_twister> getCouleurMemoire() {
		return this.memoire_couleurs;
	}

	/**
	 * Setteur de la liste de couleurs dans la mémoire du robot
	 * 
	 * @param mc la liste de couleurs
	 */
	public void setCouleurMemoire(ArrayList<Color_twister> mc) {
		this.memoire_couleurs = mc;
	}

	/**
	 * Getteur d'une couleur dont le nom est entré en paramètre
	 * 
	 * @param nom de la couleur
	 * @return la couleur si le nom en paramètre est correct, null sinon
	 */
	public Color_twister getCouleur(String nom) {
		for (Color_twister c : memoire_couleurs) {
			if (c.getName() == nom) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Getteur du pilote du robot
	 * 
	 * @return le pilote du robot
	 */
	public MovePilot getPilot() {
		return this.pilot;
	}

	/**
	 * Getteur du navigateur du robot
	 * 
	 * @return le navigateur du robot
	 */
	public Navigator getNav() {
		return this.nav;
	}
	

	// Méthodes
	/**
	 * Le capteur de couleur détecte une couleur, récupère son code RGB et créé une
	 * nouvelle couleur sans nom à partir de ces valeurs
	 * 
	 * @return color une nouvelle couleur, sans nom, avec ses 3 valeurs RGB
	 */
	public Color_twister detectColor() {
		float[] tabRGB = new float[3];
		colorS.getRGBMode().fetchSample(tabRGB, 0);
		Color_twister color = new Color_twister("inconnue", (int) (tabRGB[0] * 1000), (int) (tabRGB[1] * 1000),
				(int) (tabRGB[2] * 1000));
		return color;
	}

	/**
	 * Méthode qui permet au robot d'apprendre les différentes couleurs, puis
	 * enregistre la liste de couleur dans un fichier en mémoire
	 */
	public void learnColors() {
		LCD.clear();
		LCD.drawString("Apprendre Couleurs", 0, 0);
		LCD.drawString("Chargement...", 0, 1);
		Enregistreur.resetMemoireCouleurs();
		LCD.drawString("Pret ! Touche Moi.", 0, 2);
		Button.waitForAnyPress(); // On attend que l'utilisateur soit prêt

		// Début de l'apprentissage des couleurs
		// Couleur : Noir
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le NOIR...", 2, 1);
		LCD.drawString("Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress(); // On attend que l'utilisateur le place sur la bonne couleur
		LCD.clear();
		noir = detectColor(); // Créé une nouvelle couleur grâce au code RGB
		noir.setName("noir"); // On nomme la couleur
		noir.getRGB(); // On affiche le code de la couleur à l'écran pour que l'utilisateur ait un
						// retour
		memoire_couleurs.add(noir); // On ajoute la couleur à la liste des couleurs en mémoire
		// Couleur : Rouge
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le ROUGE...", 2, 1);
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		rouge = detectColor();
		rouge.setName("rouge");
		rouge.getRGB();
		memoire_couleurs.add(rouge);
		// Couleur : Bleu
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le BLEU...", 2, 1);
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		bleu = detectColor();
		bleu.setName("bleu");
		bleu.getRGB();
		memoire_couleurs.add(bleu);
		// Couleur : Vert
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le VERT...", 2, 1);
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		vert = detectColor();
		vert.setName("vert");
		vert.getRGB();
		memoire_couleurs.add(vert);
		// Couleur : Orange
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le ORANGE...", 2, 1);
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		orange = detectColor();
		orange.setName("orange");
		orange.getRGB();
		memoire_couleurs.add(orange);
		// Couleur : Blanc
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le BLANC...", 2, 1);
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		blanc = detectColor();
		blanc.setName("blanc");
		blanc.getRGB();
		memoire_couleurs.add(blanc);

		// Sauvegarde de la map en mémoire
		Enregistreur.serialiserCouleurs(this.memoire_couleurs); // On sauvegarde la liste des couleurs sur un fichier
																// dans la mémoire du robot

		// Fin de l'apprentissage
		LCD.clear();
		LCD.drawString("Fin Apprentissage.", 0, 0);
		Delay.msDelay(3000); // Petit délai avant de quitter la méthode
		LCD.clear();
	}

	/**
	 * Détecte une couleur grâce à son capteur, la compare avec la liste des
	 * couleurs en mémoire, puis retourne la couleur la plus proche de celle qu'il
	 * détecte
	 * 
	 * @return la couleur la plus proche de celle détectée par le capteur
	 */
	public Color_twister comparerCouleur() {
		Color_twister couleur_detectee = detectColor(); // On détecte la couleur sous le capteur
		Color_twister plus_proche_couleur; // On cherche à savoir la couleur la plus proche
		double min = couleur_detectee.DistanceEuclidienneCouleur(memoire_couleurs.get(0));
		plus_proche_couleur = memoire_couleurs.get(0); // Par défaut la couleur la plus proche est le blanc

		for (Color_twister couleurs_en_memoire : memoire_couleurs) { // On parcourt toutes les couleurs en mémoire
			if (couleur_detectee.DistanceEuclidienneCouleur(couleurs_en_memoire) < min) {
				min = couleur_detectee.DistanceEuclidienneCouleur(couleurs_en_memoire); // plus la distance euclidienne
																						// est courte entre deux
																						// couleurs, plus les couleurs
																						// sont proches
				plus_proche_couleur = couleurs_en_memoire;
				// Si la couleur détectée est plus proche d'une autre couleur que du blanc
				// alors, on change la couleur la plus proche
			}
		}
		return plus_proche_couleur; // On retourne la couleur la plus proche de la couleur détectée
	}

	/**
	 * Permet d'avoir un visuel sur l'environnement du robot tel qu'il est
	 * représenté dans sa mémoire Se présente ainsi : "Map : |x|x|x|x|x|x|x|
	 * |x|x|x|x|x|x|x| |x|x|x|x|x|x|x| |x|x|x|x|x|x|x| |x|x|x|x|x|x|x|" x représente
	 * la couleur de la case (première lettre de la couleur ou '/' si la couleur de
	 * la case est nulle)
	 */
	public void printMap() {
		try {
			LCD.clear();
			System.out.println("Map :");
			System.out.println(this.memoire_map.toString());
		} catch (Error e) {
			LCD.clear();
			System.out.println(e);
			Button.waitForAnyPress();
		}
	}

	/**
	 * Permet d'afficher le texte à l'écran issu de la commande 'System.out.println'
	 * (concerne le message de création de l'Arbitrator ou l'affichage de la carte)
	 */
	public void clearPrint() {
		for (int x = 0; x < 7; x++) {
			System.out.println(" "); // Permet d'effacer le message du constructeur de l'Arbitrator
		}
	}

	/**
	 * Permet de donner la même vitesse à tous les moteurs
	 * 
	 * @param speed la vitesse que l'on souhaite donner aux moteurs
	 */
	public void setPowerAllMotor(int speed) {
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
	}

	/**
	 * Permet de stopper les moteurs des roues du robot
	 */
	public void stopAllMotor() {
		Motor.B.stop();
		Motor.C.stop();

	}

	/**
	 * Permet de fermer tous les capteurs du robot (lors de l'arrêt du robot)
	 */
	public void closeAllSensors() {
		this.touchS.close();
		this.gyroS.close();
		this.colorS.close();
		this.sonarS.close();

	}

	/**
	 * Permet de fermer tous les moteurs du robot (lors de l'arrêt du robot)
	 */
	public void closeAllMotor() {
		Motor.A.close();
		Motor.B.close();
		Motor.C.close();

	}

	/**
	 * Methode qui permet de faire avancer un moteur donné en paramètre à la vitesse
	 * et la direction donnée en paramètre
	 * 
	 * @param motor moteur à utiliser
	 * @param direction la direction et la vitesse à lui donner
	 */
	public void doStep(NXTRegulatedMotor motor, int direction) {

		if (direction < 0) { // si la direction est inférieure à zero, on le fait reculer à la vitesse donnée
			direction = Math.abs(direction);
			motor.setSpeed(direction);
			motor.backward();
		} else { // sinon, on le fait avancer à la vitesse donnée
			motor.setSpeed(direction);
			motor.forward();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // On attend deux secondes
		motor.stop(); // On stoppe le moteur
	}

}
