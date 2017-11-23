package database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap <String, Integer> files;
	
	public Base() {
		files = new HashMap <String, Integer>();
	}
	
	public void put(String file, int words) {
		files.put(file, words);
	}
	
	public void remove(String file) {
		files.remove(file);
	}
	public boolean contains(String file) {
		return files.containsKey(file);
	}
	
	
	public String toString() {
		String s = "";
		TreeMap<String, Integer> sorted = new TreeMap<>(files);
		for (Map.Entry <String, Integer> entry : sorted.entrySet()) {
			String file = entry.getKey();
			int words = entry.getValue();
	        s = s.concat(file + ": " + words + " palavras indexadas. \n");
		}
		return s;
	}
}