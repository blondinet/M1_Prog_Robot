package ressources_twister;
import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

/**
 * Classe qui permet de d'instancier un robot avec ses composant
 * Nous l'utiliserons afin de programmer le robot physique et de repondre aux question du td
 * 
 * @author blondin
 *
 */
public class Robot {
	private NXTRegulatedMotor arms;
	private NXTRegulatedMotor leftW; //leftWheel
	private NXTRegulatedMotor rightW;
	
	private EV3TouchSensor touchS; //touchSensor
	private EV3GyroSensor gyroS;
	private EV3ColorSensor colorS;
	private EV3UltrasonicSensor ultraSonicS;
	
	private Color_twister rouge, bleu, vert, orange, blanc, noir;
	
	/**
	 * Constructeur de la classe
	 * Ce constructeur r�cup�re les motor physique et donne leurs valeurs a des variables d'instanciation
	 */
	public Robot() {		
		try {
			this.arms = Motor.A; //bras
			this.leftW = Motor.B; //moteur B = roue de gauche
			this.rightW = Motor.C;
		}catch(NullPointerException e) {
			System.out.println("Erreur Constructeur");
			System.out.println(e);
		}
		
		try {
			this.touchS = new EV3TouchSensor(SensorPort.S1);
			this.gyroS = new EV3GyroSensor(SensorPort.S2);
			this.colorS = new EV3ColorSensor(SensorPort.S3);
			this.ultraSonicS = new EV3UltrasonicSensor(SensorPort.S4);
			
		}catch(NullPointerException e) {
			System.out.print("erreur capteurs");
			System.out.print(e);
		}
	}
	
	/**
	 * Getteur de la couleur percue par le senseur
	 * @return ID de la couleur
	 */
	public int getColorID(){
		int res = colorS.getColorID();
		return res;
	}
	/**
	 * Detecteur de couleur par capteur
	 * @return color une couleur au format RGB
	 */
	public Color_twister detectColor() {
		float[] tabRGB = new float[3];
		colorS.getRGBMode().fetchSample(tabRGB, 0);
		Color_twister color = new Color_twister("inconnue", (int)tabRGB[0]*1000, (int)tabRGB[1]*1000, (int)tabRGB[2]*1000 );
		return color;
	}

	/**
	 * M�thode qui permet au robot d'apprendre les diff�rentes couleurs
	 * @return
	 */
	public void learnColors() {
		System.out.println("Learn Colors\nPress key to start...");
		Button.waitForAnyPress();
		
		System.out.println("Place moi sur le NOIR\nAppuie pour continuer");
		Button.waitForAnyPress();
		// le robot va stocker la valeur RGB du noir
		noir = detectColor();
		noir.setName("noir");
		
		System.out.println("Place moi sur le ROUGE\nAppuie pour continuer");
		Button.waitForAnyPress();
		rouge = detectColor();
		rouge.setName("rouge");
		
		System.out.println("Place moi sur le BLEU\nAppuie pour continuer");
		Button.waitForAnyPress();
		bleu = detectColor();
		bleu.setName("bleu");
		
		System.out.println("Place moi sur le VERT\nAppuie pour continuer");
		Button.waitForAnyPress();
		vert = detectColor();
		vert.setName("vert");
		
		System.out.println("Place moi sur le ORANGE\nAppuie pour continuer");
		Button.waitForAnyPress();
		orange = detectColor();
		orange.setName("orange");
		
		System.out.println("Place moi sur le BLANC\nAppuie pour continuer");
		Button.waitForAnyPress();
		blanc = detectColor();
		blanc.setName("blanc");
		
		System.out.println("Fin de l'apprentissage\nAppuie pour quitter");
		Button.waitForAnyPress();		
	}
	
	/**
	 * Methode qui permet de donner la m�me valeur de vitesse � tout les moteur
	 * @param speed la vitesse qu'on souhaite donner � tout les moteurs
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
		this.ultraSonicS.close();
		
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
	 * getteur des moteur 
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
	 * @param
	 * @return le senseur voulu
	 */
	public EV3ColorSensor getColorSensor() {
		return this.colorS;
	}
	
	public EV3TouchSensor getTouchSensor() {
		return this.touchS;
	}

	/**
	 * Methode qui permet de faire avancer un moteur donn� en param�tre a la vitesse et la direction donn� en param�tre
	 * @param motor C'est le moteur � utiliser
	 * @param direction C'est la direction et la vitesse � lui donner
	 */
	public void doStep(NXTRegulatedMotor motor, int direction) {

		if(direction < 0) { // si la direction est inf�rieur � zero, on le fait reculer a la vitesse donn�
			direction = Math.abs(direction);
			motor.setSpeed(direction);
			motor.backward(); 
		}else { // sinon, on le fait avancer � la vitesse donn�
			motor.setSpeed(direction);
			motor.forward();
		}
		
		try {Thread.sleep(2000);}catch (InterruptedException e) {e.printStackTrace();} // On attend deux secondes
		motor.stop(); // on stop le moteur
	}

}