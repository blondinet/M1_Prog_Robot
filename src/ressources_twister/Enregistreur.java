package ressources_twister;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

/**
 * Classe qui permet de sauvegarder la m�moire d'un robot et de la charger
 * M�moire du robot : liste des couleurs et map
 * 
 * @author William
 */
public class Enregistreur {
	
	// M�moire Couleurs
	/**
	 * Permet de sauvegarder la m�moire des couleurs dans le fichier "memoire_couleurs.ser"
	 * 
	 * @param couleurs Liste des couleurs m�moris�es par le premier robot 
	 */
	public static void serialiserCouleurs(ArrayList<Color_twister> save_couleurs) {
		// Cr�ation d'une nouvelle liste de couleur (copie)
		//final ArrayList<Color_twister> save_couleurs = new ArrayList<Color_twister>();
		//save_couleurs.setList(couleurs.getList());
		ObjectOutputStream output = null;
		
		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_couleurs.ser");
			output = new ObjectOutputStream(fichier_out);
			output.writeObject(save_couleurs);
			output.flush();
		} catch(final IOException e) {
			LCD.clear();
			LCD.drawString("IOException 1 : "+e,0,0);
			LCD.refresh();
			Button.waitForAnyPress();
			LCD.clear();
		} finally {
			try {
				if(output != null) {
					output.flush();
					output.close();
				}
			} catch(final IOException ex) {
				LCD.clear();
				LCD.drawString("IOException 2 : "+ex,0,0);
				LCD.refresh();
				Button.waitForAnyPress();
				LCD.clear();
			}
		}
		
	}
	/**
	 * R�cup�re le fichier "memoire_couleur.ser" pour permettre � l'autre robot de charger la liste des couleurs
	 * @return La liste des couleurs m�moris�es
	 */
	public static ArrayList<Color_twister> deserialiserCouleurs() {
		ArrayList<Color_twister> save_couleurs = new ArrayList<Color_twister>();
		ObjectInputStream input = null;
		
		try {
			final FileInputStream fichier_in = new FileInputStream("memoire_couleurs.ser");
			input = new ObjectInputStream(fichier_in);
			save_couleurs = (ArrayList) input.readObject();
			return save_couleurs;
		} catch(final ClassNotFoundException e) {
			LCD.clear();
			LCD.drawString("ClassNotFOundException : "+e,0,0);
			LCD.refresh();
			Button.waitForAnyPress();
			LCD.clear();
		} catch(final IOException e) {
			LCD.clear();
			LCD.drawString("IOException 1 : "+e,0,0);
			LCD.refresh();
			Button.waitForAnyPress();
			LCD.clear();
		} finally {
			try {
				if(input != null) {
					input.close();
				}
			} catch(final IOException e) {
				LCD.clear();
				LCD.drawString("IOException 2 : "+e,0,0);
				LCD.refresh();
				Button.waitForAnyPress();
				LCD.clear();
			}
		}
		return null;
	}
	
	// M�moire Map
	/**
	 * Permet de sauvegarder la m�moire de la map dans le fichier "memoire_map.ser"
	 * 
	 * @param map Map du jeu twister cartographi�e par le premier robot
	 */
	public static void serialiserMap(Map_twister map) {
		// Cr�ation d'une nouvelle map (copie) 
		Map_twister save_map = new Map_twister();
		save_map.setMap(map.getMap());
		ObjectOutputStream output = null;
		
		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_map.ser");
			output = new ObjectOutputStream(fichier_out);
			output.writeObject(save_map);
			output.flush();
		} catch(final IOException e) {
			LCD.clear();
			e.printStackTrace();
		} finally {
			try {
				if(output != null) {
					output.flush();
					output.close();
				}
			} catch(final IOException ex) {
				LCD.clear();
				ex.printStackTrace();
			}
		}
		
	}
	/**
	 * R�cup�re le fichier "memoire_map.ser" pour permettre � l'autre robot de charger la map du jeu
	 * @return La map du jeu cartographi�e
	 */
	public static Map_twister deserialiserMap() {
		ObjectInputStream input = null;
		
		try {
			final FileInputStream fichier_in = new FileInputStream("memoire_map.ser");
			input = new ObjectInputStream(fichier_in);
			final Map_twister save_map = (Map_twister) input.readObject();
			return save_map;
		} catch(final ClassNotFoundException e) {
			LCD.clear();
			e.printStackTrace();
		} catch(final IOException e) {
			LCD.clear();
			e.printStackTrace();
		} finally {
			try {
				if(input != null) {
					input.close();
				}
			} catch(final IOException e) {
				LCD.clear();
				e.printStackTrace();
			}
		}
		return null;
	}

}
