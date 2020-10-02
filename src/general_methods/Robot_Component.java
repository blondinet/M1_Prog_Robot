package general_methods;
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
public class Robot_Component {
	private NXTRegulatedMotor motorB;
	private NXTRegulatedMotor motorC;
	private NXTRegulatedMotor motorA;
	
	private EV3TouchSensor toucheS;
	private EV3GyroSensor gyroS;
	private EV3ColorSensor colorS;
	private EV3UltrasonicSensor ultraSonicS;
	
	/**
	 * Constructeur de la classe
	 * Ce constructeur récupère les motor physique et donne leurs valeurs a des variables d'instanciation
	 */
	public Robot_Component() {		
		try {
			this.motorA = Motor.A; //bras
			this.motorB = Motor.B; //moteur B = roue de gauche
			this.motorC = Motor.C;
		}catch(NullPointerException e) {
			System.out.println("Erreur Constructeur");
			System.out.println(e);
		}
		
		try {
			//capteur[0] = new EV3TouchSensor(SensorPort.S1);
			
			//capteur[1] = new EV3GyroSensor(SensorPort.S2);
			
			//capteur[2] = new EV3ColorSensor(SensorPort.S3);
			
			//capteur[3] = new EV3UltrasonicSensor(SensorPort.S4);
			
			this.toucheS = new EV3TouchSensor(SensorPort.S1);
			this.gyroS = new EV3GyroSensor(SensorPort.S2);
			this.colorS = new EV3ColorSensor(SensorPort.S3);
			this.ultraSonicS = new EV3UltrasonicSensor(SensorPort.S4);
			
		}catch(NullPointerException e) {
			System.out.print("erreur capteurs");
			System.out.print(e);
		}
	}
	
	/**
	 * getteur de la couleur percu par le senseur
	 * @return
	 */
	public int getColorID(){
		int res = colorS.getColorID();
		return res;
	}
	
	public void getColorRGB() {
		float[] tabRGB = new float[3];
		colorS.getRGBMode().fetchSample(tabRGB, 0);
		for (int i = 0; i<=2; i++) {
			tabRGB[i] = tabRGB[i]*1000;
		}
		
		System.out.println("La couleur est :\nR = "+(int)tabRGB[0]+ "\nV = "+(int)tabRGB[1]+ "\nB = "+(int)tabRGB[2]);
		//return 
				
	}
	/**
	 * Methode qui permet de donner la même valeur de vitesse à tout les moteur
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
	 * Methode qui permet de fermer tous les moteurs du robot
	 */
	public void closeAllMotor() {
		Motor.B.close();
		Motor.C.close();
	}
	
	/**
	 * getteur des moteur 
	 * @param le moteur a récupérer [1 : A | 2 : B | 3 : C | 4 : D]
	 * @return le premier Moteur
	 */
	public NXTRegulatedMotor getMotor(int motor) {
		NXTRegulatedMotor res=null;
		
		switch(motor) {
			case 1:
				res = Motor.A;
			break;
			case 2:
				res = Motor.B;
			break;
			case 3:
				res = Motor.C;
			break;
			case 4:
				res = Motor.D;
			break;
		}
		return res;
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
		return this.toucheS;
	}

	/**
	 * Methode qui permet de faire avancer un moteur donné en paramètre a la vitesse et la direction donné en paramètre
	 * @param motor C'est le moteur à utiliser
	 * @param direction C'est la direction et la vitesse à lui donner
	 */
	public void doStep(NXTRegulatedMotor motor, int direction) {

		if(direction < 0) { // si la direction est inférieur à zero, on le fait reculer a la vitesse donné
			direction = Math.abs(direction);
			motor.setSpeed(direction);
			motor.backward(); 
		}else { // sinon, on le fait avancer à la vitesse donné
			motor.setSpeed(direction);
			motor.forward();
		}
		
		try {Thread.sleep(2000);}catch (InterruptedException e) {e.printStackTrace();} // On attend deux secondes
		motor.stop(); // on stop le moteur
	}

}
