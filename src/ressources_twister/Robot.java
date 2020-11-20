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
 * Classe qui permet de d'instancier un robot avec ses composant Nous
 * l'utiliserons afin de programmer le robot physique et de repondre aux
 * question du td
 * 
 * @author blondin
 *
 */
public class Robot implements Serializable {
	private NXTRegulatedMotor arms;
	private NXTRegulatedMotor leftW; // leftWheel
	private NXTRegulatedMotor rightW;

	private EV3TouchSensor touchS; // touchSensor
	private EV3GyroSensor gyroS;
	private EV3ColorSensor colorS;
	private EV3UltrasonicSensor sonarS;

	private Color_twister rouge, bleu, vert, orange, blanc, noir;
	private ArrayList<Color_twister> memoire_couleurs = new ArrayList<Color_twister>();
	private Map_twister memoire_map = new Map_twister();

	private Case_twister position;
	private Chassis chassis;
	private MoveController pilot;
	private Navigator nav;

	/**
	 * Constructeur de la classe Ce constructeur récupère les motor physique et
	 * donne leurs valeurs a des variables d'instanciation
	 */
	public Robot() {
		try {
			this.arms = Motor.A; // bras
			this.leftW = Motor.B; // moteur B = roue de gauche
			this.rightW = Motor.C;
		} catch (NullPointerException e) {
			LCD.clear();
			System.out.println("Erreur Constructeurs : " + e);
		}

		try {
			this.touchS = new EV3TouchSensor(SensorPort.S1);
			this.gyroS = new EV3GyroSensor(SensorPort.S2);
			this.colorS = new EV3ColorSensor(SensorPort.S3);
			this.sonarS = new EV3UltrasonicSensor(SensorPort.S4);

		} catch (NullPointerException e) {
			LCD.clear();
			System.out.print("Erreur capteurs : " + e);
		}

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

	/**
	 * Getteur de la couleur percue par le senseur
	 * 
	 * @return ID de la couleur
	 */
	public int getColorID() {
		int res = colorS.getColorID();
		return res;
	}

	public Map_twister getMapMemoire() {
		return this.memoire_map;
	}

	public void setMapMemoire(Map_twister m) {
		this.memoire_map = m;
	}

	public ArrayList<Color_twister> getCouleurMemoire() {
		return this.memoire_couleurs;
	}

	public void setCouleurMemoire(ArrayList<Color_twister> mc) {
		this.memoire_couleurs = mc;
	}

	public Color_twister getNoir() {
		return this.noir;
	}

	public MoveController getPilot() {
		return this.pilot;
	}

	public Navigator getNav() {
		return this.nav;
	}

	/**
	 * Détecteur de couleur par capteur
	 * 
	 * @return color une couleur au format RGB
	 */
	public Color_twister detectColor() {
		float[] tabRGB = new float[3];
		colorS.getRGBMode().fetchSample(tabRGB, 0);
		Color_twister color = new Color_twister("inconnue", (int) (tabRGB[0] * 1000), (int) (tabRGB[1] * 1000),
				(int) (tabRGB[2] * 1000));
		return color;
	}

	/**
	 * Méthode qui permet au robot d'apprendre les différentes couleurs
	 * 
	 * @return
	 */
	public void learnColors() {
		LCD.clear();
		LCD.drawString("Apprendre Couleurs", 0, 0);
		LCD.drawString("Chargement...", 0, 1);
		Enregistreur.resetMemoireCouleurs();
		LCD.drawString("Pret ! Touche Moi.", 0, 2);
		Button.waitForAnyPress();

		// Début de l'apprentissage des couleurs
		// Couleur : Noir
		LCD.clear();
		LCD.drawString("Placez moi sur :", 0, 0);
		LCD.drawString("Le NOIR...", 2, 1);
		LCD.drawString("Touche moi.", 0, 2);
		LCD.refresh();
		Button.waitForAnyPress();
		LCD.clear();
		noir = detectColor();
		noir.setName("noir");
		noir.getRGB();
		memoire_couleurs.add(noir);
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
		Enregistreur.serialiserCouleurs(this.memoire_couleurs);
		// Chargement de la liste des couleurs en mémoire
		// robot.setCouleurMemoire(Enregistreur.deserialiserCouleurs());

		// Fin de l'apprentissage
		LCD.clear();
		LCD.drawString("Fin Apprentissage.", 0, 0);
		Delay.msDelay(3000);
		LCD.clear();
	}

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

		// LCD.clear();
		// LCD.drawString("C'est du : ", 0, 0);
		// LCD.drawString(plus_proche_couleur.getName(), 0, 1);
		// Delay.msDelay(3000);
		return plus_proche_couleur; // On retourne la couleur la plus proche de la couleur détectée
	}

	/**
	 * Cartographie de la map
	 */
	public void cartography() {
		LCD.clear();
		LCD.drawString("Cartographie", 0, 0);
		LCD.drawString("Chargement...", 0, 1);
		Enregistreur.resetMemoireMap();
		LCD.drawString("Pret ! Touche moi.", 0, 2);
		Button.waitForAnyPress();

		Behavior comp_avancer = new Drive_forward(this);
		Behavior comp_detecter_noir = new Detecter_noir(this);
		Behavior comp_stop = new Bouton_stop(this);
		// rotate
		
		Behavior[] comportements_case_suivante = { comp_avancer, comp_detecter_noir, comp_stop }; // du moins prioritaire au plus
		//Behavior[] comportements_ligne_suivante = { comp_avancer, comp_detecter_noir, comp_stop };
		Arbitrator arbitrator_case_suivante = new Arbitrator(comportements_case_suivante);
		//Arbitrator arbitrator_ligne_suivante = new Arbitrator(comportements_ligne_suivante);
		
		for (int i=0; i<this.memoire_map.lengthX(); i++) {
			LCD.clear();
			this.memoire_map.getCase(i, 0).setCouleur(this.comparerCouleur());
			arbitrator_case_suivante.go();
		}
		//arbitrator_ligne_suivante.go();
			
		
		// Le placer sur la case rouge en bas à gauche
		/*
		this.memoire_map.getCase(0, 0).setCouleur(comparerCouleur());
		arbitrator_cartographie.go();
		for (int x = 0; x < memoire_map.lengthX(); x++) {
			this.memoire_map.getCase(x, 0).setCouleur(comparerCouleur());
			arbitrator_cartographie.go();
		}

		// Sauvegarde de la map en mémoire
		// Enregistreur.serialiserMap(this.memoire_map);
		// Chargement de la map en mémoire
		// this.memoire_map = Enregistreur.deserialiserMap();
		// robot.setMapMemoire(Enregistreur.deserialiserMap());

		// Fin de la cartographie
		LCD.clear();
		LCD.drawString("Fin cartographie.", 0, 0);
		Delay.msDelay(3000);
		LCD.clear();*/
		
		
	}

	/**
	 * Methode qui permet de donner la même valeur de vitesse à tout les moteur
	 * 
	 * @param speed la vitesse qu'on souhaite donner à tout les moteurs
	 */
	public void setPowerAllMotor(int speed) {
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
	}

	/**
	 * Methode qui permet de stopper tous les moteur du robot
	 */
	public void stopAllMotor() {
		Motor.B.stop();
		Motor.C.stop();

	}

	/**
	 * Methode qui permet de stopper tous les moteur du robot
	 */
	public void closeAllSensors() {
		this.touchS.close();
		this.gyroS.close();
		this.colorS.close();
		// this.ultraSonicS.close();

	}

	/**
	 * Methode qui permet de fermer tous les moteurs du robot
	 */
	public void closeAllMotor() {
		Motor.A.close();
		Motor.B.close();
		Motor.C.close();

	}

	/**
	 * getteur des moteurs
	 * 
	 * @param none
	 * @return le premier Moteur
	 */
	public NXTRegulatedMotor getLeftW() {
		return this.leftW;

	}

	public NXTRegulatedMotor getRightW() {
		return this.rightW;

	}

	public NXTRegulatedMotor getArms() {
		return this.arms;

	}

	/**
	 * getteur des senseur du robot
	 * 
	 * @param
	 * @return le senseur voulu
	 */
	public EV3ColorSensor getColorSensor() {
		return this.colorS;
	}

	public EV3TouchSensor getTouchSensor() {
		return this.touchS;
	}

	public EV3GyroSensor getGyroSensor() {
		return this.gyroS;
	}

	public EV3UltrasonicSensor getSonarSensor() {
		return this.sonarS;
	}


	/**
	 * Methode qui permet de faire avancer un moteur donné en paramètre a la vitesse
	 * et la direction donné en paramètre
	 * 
	 * @param motor     C'est le moteur à utiliser
	 * @param direction C'est la direction et la vitesse à lui donner
	 */
	public void doStep(NXTRegulatedMotor motor, int direction) {

		if (direction < 0) { // si la direction est inférieur à zero, on le fait reculer a la vitesse donné
			direction = Math.abs(direction);
			motor.setSpeed(direction);
			motor.backward();
		} else { // sinon, on le fait avancer à la vitesse donné
			motor.setSpeed(direction);
			motor.forward();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // On attend deux secondes
		motor.stop(); // on stop le moteur
	}

}
