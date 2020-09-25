package main_Package;
import ressource_exercice.*;

import general_methods.*;

/**
 * Classe qui permet d'executer les diff�rentes r�ponses aux exercices
 * @author blondin
 *
 */
public class Main {
	
	/**
	 * On instancie un robot avec la classe Robot_Component
	 * Ce robot sera utilis� dans les ExerciceTd afin de programmer le robot physique
	 * Dans les classe ExerciceTD (qui h�rite de Ex_Model) on trouvera de multiples 
	 * m�thodes pour r�pondre aux diff�rentes question du Td
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			ExerciceTd1 td1 = new ExerciceTd1(new Robot_Component());
			td1.danceAwayYourProblems();
		}catch(NullPointerException e) {
			System.out.println("erreur main");
			System.out.println(e);
		}
		
		//td1.sayHello(); //Exercice 1
	
		//td1.driveForward(50, 5000); //Exercice 2 (speed percentage, delay in milliseconds)
		
		//td1.turnAround(50, 2000); //Exercice 3

		
	}
	
}