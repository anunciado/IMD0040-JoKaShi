package database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map <String, Set<String>> files;
	
	public Base() {
		files = new HashMap <String, Set<String>>();
	}
	
	public void put(String file, Set<String> words) {
		files.put(file, words);
	}
	
	public void remove(String file) {
		files.remove(file);
	}
	
	public boolean containsKey(String file) {
		return files.containsKey(file);
	}
	
	public boolean containsValues(String file, List <String> words) {
		return files.get(file).containsAll(words);
	}
		
	public String toString() {
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