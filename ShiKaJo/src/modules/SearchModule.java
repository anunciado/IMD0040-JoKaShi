package modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

import database.Base;
import database.Stats;
import datastructure.Trie;

public class SearchModule {
	private Trie trie;
	private Base base;
	
	public SearchModule(Trie trie, Base base) {
		this.setTrie(trie);
		this.setBase(base); 
	}

	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}
	
	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public String search(String word, String mode) {
		List<String> words = new ArrayList<String>(Arrays.asList(word.split(" ")));
		for (String wordAux: words) {
			wordAux = wordAux.replaceAll(" " , "");
			wordAux = Normalizer.normalize(word, Normalizer.Form.NFD);
			wordAux = wordAux.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
			wordAux = wordAux.replaceAll("[-|_.,]+","");
			wordAux = wordAux.replaceAll("\\{!-@\\}\\{[-]\\}","");
		}
		if (mode.equals("or")) {
			String stringOr = searchModeOr(words);
			return stringOr;
		}
		else {
			String stringAnd = searchModeAnd(words);
			return stringAnd;
		}		
	}
	
	private String searchModeOr(List<String> words) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String wordAux: words) {
			HashMap <String, Stats> map2 = trie.contains(wordAux);
			for (Map.Entry <String, Stats > entry : map2.entrySet()) {
				List<String> arraylist = new ArrayList<String>();
				String file = entry.getKey();
				Stats stats = entry.getValue();
				HashMap<Integer,Integer> lines = stats.getLines();
				for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
					int line = entry2.getKey();
					int occurrence = entry2.getValue();
					arraylist.add(file + ": " + occurrence + "​ ocorrência​(s) da palavra " + wordAux + "​​ na linha " + line);
				}
				if(map.containsKey(file)) {
					List<String> arraylist2 = map.get(file);
					arraylist2.addAll(arraylist);
				}
				else {
					map.put(file, arraylist);
				}				
			}
		}
		Map<String, List<String>> mapSorted = sort(map);
		String s = "";
		for (List<String> list : mapSorted.values()) {
			s = s.concat("\n");
			s = s.concat(String.join("\n", list));
		}
		return s;
	}
	
	private String searchModeAnd(List<String> words) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String wordAux: words) {
			HashMap <String, Stats> map2 = trie.contains(wordAux);
			for (Map.Entry <String, Stats > entry : map2.entrySet()) {
				List<String> arraylist = new ArrayList<String>();
				String file = entry.getKey();
				Stats stats = entry.getValue();
				if (base.containsValues(file, words)) {
					HashMap<Integer,Integer> lines = stats.getLines();
					for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
						int line = entry2.getKey();
						int occurrence = entry2.getValue();
						arraylist.add(file + ": " + occurrence + "​ ocorrência​(s) da palavra " + wordAux + "​​ na linha " + line);
					}
					if(map.containsKey(file)) {
						List<String> arraylist2 = map.get(file);
						arraylist2.addAll(arraylist);
					}
					else {
						map.put(file, arraylist);
					}	
				}				
			}
		}
		Map<String, List<String>> mapSorted = sort(map);
		String s = "";
		for (List<String> list : mapSorted.values()) {
			s = s.concat("\n");
			s = s.concat(String.join("\n", list));
		}
		return s;
	}
	
	public Map<String, List<String>> sort(Map<String, List<String>> treeMap){
		Map<String, List<String>> map = new TreeMap<String, List<String>>(treeMap);
        List<Map.Entry<String, List<String>>> entries = new LinkedList<Map.Entry<String, List<String>>>(map.entrySet());
     
        Collections.sort(entries, new Comparator<Map.Entry<String, List<String>>>() {

            @Override
            public int compare(Entry<String, List<String>> o1, Entry<String, List<String>> o2) {
                return o2.getValue().size() - o1.getValue().size();
            }
        });
        Map<String, List<String>> sortedMap = new LinkedHashMap<String, List<String>>();
        for(Map.Entry<String, List<String>> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

	/*
	public int calculateDistance(String str1, String str2) {
		int distance = 0;
		if (str1.isEmpty())
			return str1.length();
		if (str2.isEmpty())
			return str2.length();
		int len1 = str1.length();
		int len2 = str2.length();
		// Check if the last chars match
		if (str1.charAt(len1 - 1) == str2.charAt(len2 - 1))
			distance = 0;
		else
			distance = 1;
		return Math.min(
				Math.min(calculateDistance(str1.substring(0, len1), str2) + 1,
						calculateDistance(str1, str2.substring(0, len2)) + 1),
				calculateDistance(str1.substring(0, len1),
						str2.substring(0, len2)));
	}
	*/
}
