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
 * Classe qui permet de sauvegarder la mémoire d'un robot et de la charger
 * Mémoire du robot : liste des couleurs et map
 * 
 * @author William
 */
public class Enregistreur {

	// Mémoire Couleurs
	/**
	 * Permet de sauvegarder la liste des couleurs dans le fichier
	 * "memoire_couleurs.ser"
	 * 
	 * @param couleurs Liste des couleurs mémorisée par le premier robot
	 */
	public static void serialiserCouleurs(ArrayList<Color_twister> memoire_couleurs) {
		LCD.clear();
		LCD.drawString("Sauvegarde Couleur", 0, 0);
		ObjectOutputStream objet_out = null;

		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_couleurs.ser");
			objet_out = new ObjectOutputStream(fichier_out);
			objet_out.writeObject(memoire_couleurs);
			objet_out.flush();
			fichier_out.close();
		} catch (final IOException io_exception) {
			LCD.clear(1);
			LCD.drawString("ERREUR FICHIER OUT", 0, 1);
			// ioexception.printStackTrace();
		} finally {
			try {
				if (objet_out != null) {
					objet_out.flush();
					objet_out.close();
				}
			} catch (final IOException exception) {
				LCD.clear(2);
				LCD.drawString("ERREUR SAVE COLORS", 0, 2);
				// exception.printStackTrace();
			}
		}
	}

	/**
	 * Récupère le fichier "memoire_couleur.ser" pour permettre à l'autre robot de
	 * charger la liste des couleurs
	 * 
	 * @return La liste des couleurs mémorisées
	 */
	public static ArrayList<Color_twister> deserialiserCouleurs() {
		LCD.clear();
		LCD.drawString("Chargement Couleur", 0, 0);
		// ArrayList<Color_twister> save_couleurs = new ArrayList<Color_twister>();
		ObjectInputStream objet_in = null;

		try {
			final FileInputStream fichier_in = new FileInputStream("memoire_couleurs.ser");
			objet_in = new ObjectInputStream(fichier_in);
			final ArrayList<Color_twister> save_couleurs = (ArrayList<Color_twister>) objet_in.readObject();
			fichier_in.close();
			return save_couleurs;
		} catch (final ClassNotFoundException class_not_found) {
			LCD.clear(1);
			LCD.drawString("CLASS NOT FOUND", 0, 1);
			class_not_found.printStackTrace();
		} catch (final IOException io_exception) {
			LCD.clear(1);
			LCD.drawString("ERREUR FICHIER IN", 0, 1);
			// io_exception.printStackTrace();
		} finally {
			try {
				if (objet_in != null) {
					objet_in.close();
				}
			} catch (final IOException exception) {
				LCD.clear(2);
				LCD.drawString("ERREUR LOAD COLORS", 0, 2);
				// exception.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Permet de supprimer le contenu de la mémoire des couleurs
	 */
	public static void resetMemoireCouleurs() {
		ObjectOutputStream objet_out = null;

		// Reset de la mémoire des couleurs
		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_couleurs.ser");
			objet_out = new ObjectOutputStream(fichier_out);
			objet_out.writeObject(null);
			objet_out.flush();
			fichier_out.close();
		} catch (final IOException io_exception) {
			// io_exception.printStackTrace();
		} finally {
			try {
				if (objet_out != null) {
					objet_out.flush();
					objet_out.close();
				}
			} catch (final IOException exception) {
				LCD.clear();
				LCD.drawString("ERREUR RESET COLORS", 0, 0);
				// exception.printStackTrace();
			}
		}
	}

	// Mémoire Map
	/**
	 * Permet de sauvegarder la mémoire de la map dans le fichier "memoire_map.ser"
	 * 
	 * @param map Map du jeu twister cartographiée par le premier robot
	 */
	public static void serialiserMap(Map_twister map) {
		LCD.clear();
		LCD.drawString("Sauvegarde Map", 0, 0);
		ObjectOutputStream objet_out = null;

		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_map.ser");
			objet_out = new ObjectOutputStream(fichier_out);
			objet_out.writeObject(map);
			objet_out.flush();
			fichier_out.close();
		} catch (final IOException io_exception) {
			LCD.clear(1);
			LCD.drawString("ERREUR FICHIER OUT", 0, 1);
			// io_exception.printStackTrace();
		} finally {
			try {
				if (objet_out != null) {
					objet_out.flush();
					objet_out.close();
				}
			} catch (final IOException exception) {
				LCD.clear(2);
				LCD.drawString("ERREUR SAVE MAP", 0, 2);
				// exception.printStackTrace();
			}
		}
	}

	/**
	 * Récupère le fichier "memoire_map.ser" pour permettre à l'autre robot de
	 * charger la map du jeu
	 * 
	 * @return La map du jeu cartographiée
	 */
	public static Map_twister deserialiserMap() {
		LCD.clear();
		LCD.drawString("Chargement Map", 0, 0);
		ObjectInputStream objet_in = null;

		try {
			final FileInputStream fichier_in = new FileInputStream("memoire_map.ser");
			objet_in = new ObjectInputStream(fichier_in);
			final Map_twister save_map = (Map_twister) objet_in.readObject();
			fichier_in.close();
			return save_map;
		} catch (final ClassNotFoundException class_not_found) {
			LCD.clear(1);
			LCD.drawString("CLASS NOT FOUND", 0, 1);
			// class_not_found.printStackTrace();
		} catch (final IOException io_exception) {
			LCD.clear(1);
			LCD.drawString("ERREUR FICHIER IN", 0, 1);
			// io_exception.printStackTrace();
		} finally {
			try {
				if (objet_in != null) {
					objet_in.close();
				}
			} catch (final IOException exception) {
				LCD.clear(2);
				LCD.drawString("ERREUR LOAD MAP", 0, 2);
				// exception.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Permet de supprimer le contenu de la mémoire de la map
	 */
	public static void resetMemoireMap() {
		ObjectOutputStream objet_out = null;

		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_map.ser");
			objet_out = new ObjectOutputStream(fichier_out);
			objet_out.writeObject(null);
			objet_out.flush();
			fichier_out.close();
		} catch (final IOException io_exception) {
			// io_exception.printStackTrace();
		} finally {
			try {
				if (objet_out != null) {
					objet_out.flush();
					objet_out.close();
				}
			} catch (final IOException exception) {
				LCD.clear();
				LCD.drawString("ERREUR RESET MAP", 0, 0);
				// exception.printStackTrace();
			}
		}
	}
}
