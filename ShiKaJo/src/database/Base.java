package database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class is responsable for store the archive name and the words 
 * with the its correspond file
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map <String, Set<String>> files;
	
	/**
	 * The constructor of the Base class
	 */
	public Base () {
		files = new HashMap <String, Set<String>>();
	}
	
	/**
	 * Method to insert a file and a words set 
	 * @param 	file 	The file
	 * @param 	words	The words
	 */
	public void put (String file, Set<String> words) {
		files.put(file, words);
	}
	
	/**
	 * Remove a file of this class
	 * @param 	file 	The file 
	 */
	public void remove (String file) {
		files.remove(file);
	}
	
	/**
	 * Method to know if the file is in the Base 
	 * @param 	file 	The file
	 * @return 	true/false
	 */
	public boolean containsKey (String file) {
		return files.containsKey(file);
	}
	
	/**
	 * Verify if the file contains the followings words
	 * @param 	file 	The file
	 * @param 	words 	The words
	 * @return 	true/false
	 */
	public boolean containsValues (String file, List <String> words) {
		return files.get(file).containsAll(words);
	}
	
	/**
	 * Convert the class to String
	 */
	public String toString () {
		StringBuffer strReturn = new StringBuffer();
		TreeMap<String, Set<String>> sorted = new TreeMap<>(files);
		for (Map.Entry <String, Set<String>> entry : sorted.entrySet()) {
			String file = entry.getKey();
			int words = entry.getValue().size();
			if(!file.equals("blacklist.txt")) {
				strReturn.append(file + ": " + words + " palavras indexadas. \n");
			}
		}
		return strReturn.toString();
	}
}