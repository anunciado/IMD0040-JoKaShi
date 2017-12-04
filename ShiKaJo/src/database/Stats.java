package database;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	João Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
public class Stats implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap<Integer,Integer> lines;
	
	public Stats() {
		this.setLines(new HashMap<Integer,Integer>());
	}

	public HashMap<Integer,Integer> getLines() {
		return lines;
	}

	public void setLines(HashMap<Integer,Integer> lines) {
		this.lines = lines;
	}
}
