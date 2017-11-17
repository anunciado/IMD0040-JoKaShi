package database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap <String, Integer> files;
	
	public Base() {
		files = new HashMap <String, Integer>();
	}
	
	public void put(String file, int words) {
		files.put(file, words);
	}
	
	
	public String toString() {
		String s = "";
		for (Map.Entry <String, Integer> entry : files.entrySet()) {
			String file = entry.getKey();
			int words = entry.getValue();
	        s = s.concat(file + ": " + words + " palavras indexadas.");
		}
		return s;
	}
}