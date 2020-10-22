package ressources_twister;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Classe qui permet de sauvegarder la mémoire d'un robot et de la charger
 * Mémoire du robot : liste des couleurs et map
 * 
 * @author William
 */
public class Enregistreur {
	
	// Mémoire Couleurs
	/**
	 * Permet de sauvegarder la mémoire des couleurs dans le fichier "memoire_couleurs.ser"
	 * 
	 * @param couleurs Liste des couleurs mémorisées par le premier robot 
	 */
	public static void serialiserCouleurs(ArrayList<Color_twister> save_couleurs) {
		// Création d'une nouvelle liste de couleur (copie)
		//final ArrayList<Color_twister> save_couleurs = new ArrayList<Color_twister>();
		//save_couleurs.setList(couleurs.getList());
		ObjectOutputStream output = null;
		
		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_couleurs.ser");
			output = new ObjectOutputStream(fichier_out);
			output.writeObject(save_couleurs);
			output.flush();
		} catch(final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(output != null) {
					output.flush();
					output.close();
				}
			} catch(final IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	/**
	 * Récupère le fichier "memoire_couleur.ser" pour permettre à l'autre robot de charger la liste des couleurs
	 * @return La liste des couleurs mémorisées
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
			e.printStackTrace();
		} catch(final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(input != null) {
					input.close();
				}
			} catch(final IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	// Mémoire Map
	/**
	 * Permet de sauvegarder la mémoire de la map dans le fichier "memoire_map.ser"
	 * 
	 * @param map Map du jeu twister cartographiée par le premier robot
	 */
	public static void serialiserMap(Map_twister map) {
		// Création d'une nouvelle map (copie) 
		Map_twister save_map = new Map_twister();
		save_map.setMap(map.getMap());
		ObjectOutputStream output = null;
		
		try {
			final FileOutputStream fichier_out = new FileOutputStream("memoire_map.ser");
			output = new ObjectOutputStream(fichier_out);
			output.writeObject(save_map);
			output.flush();
		} catch(final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(output != null) {
					output.flush();
					output.close();
				}
			} catch(final IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	/**
	 * Récupère le fichier "memoire_map.ser" pour permettre à l'autre robot de charger la map du jeu
	 * @return La map du jeu cartographiée
	 */
	public static Map_twister deserialiserMap() {
		ObjectInputStream input = null;
		
		try {
			final FileInputStream fichier_in = new FileInputStream("memoire_map.ser");
			input = new ObjectInputStream(fichier_in);
			final Map_twister save_map = (Map_twister) input.readObject();
			return save_map;
		} catch(final ClassNotFoundException e) {
			e.printStackTrace();
		} catch(final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(input != null) {
					input.close();
				}
			} catch(final IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
