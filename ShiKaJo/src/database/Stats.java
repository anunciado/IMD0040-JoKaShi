package database;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class store the amount of word occurrence 
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class Stats implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private HashMap<Integer,Integer> lines; /** The lines of a word in a file	*/
	
	/**
	 * The constructor of the class Stats
	 */
	public Stats () {
		this.setLines(new HashMap<Integer,Integer>());
	}

	/**
	 * Return the field lines of this class 
	 * @return 	lines 	The lines
	 */
	public HashMap<Integer,Integer> getLines() {
		return lines;
	}

	/**
	 * Change the lines of the class 
	 * @param 	lines 	The lines
	 */
	public void setLines(HashMap<Integer,Integer> lines) {
		this.lines = lines;
	}
}
