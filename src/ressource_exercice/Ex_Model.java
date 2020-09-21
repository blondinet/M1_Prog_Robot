package ressource_exercice;

import general_methods.*;

/**
 * Cette classe permet de mod�liser une r�ponse type
 * Les autres classes de r�ponses h�rite de celle ci
 * 
 * @author blondin
 *
 */
public class Ex_Model {

	/** C'est le robot auquel on donne des ordres */
	protected Robot_Component robot;
	
	/**
	 * C'est le constructeur de la classe auquel on donne le robot
	 * @param r le robot qui sera utilis�
	 */
	public Ex_Model(Robot_Component r) {
		this.robot=r;
	}

	/**
	 * Getteur du robot
	 * @return le robot utilis� par l'exercice
	 */
	public Robot_Component getRobot() {
		return this.robot;
	}
	
	/**
	 * Setteur du robot
	 * @param r le nouveau robot de l'exercice
	 */
	public void setRobot_Component(Robot_Component r){
		this.robot=r;
	}
}
