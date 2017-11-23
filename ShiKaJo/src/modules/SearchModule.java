package modules;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import database.Stats;
import datastructure.Trie;

public class SearchModule {
	private Trie trie;
	
	public SearchModule(Trie trie) {
		this.setTrie(trie);
	}

	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	public String search(String word, String mode) {
		List<String> words = new ArrayList<String>(Arrays.asList(word.split(" ")));
		for (String wordAux: words) {
			wordAux = wordAux.replaceAll(" " , "");
			wordAux = Normalizer.normalize(word, Normalizer.Form.NFD);
			wordAux = wordAux.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
			wordAux = wordAux.replaceAll("[|_.,]+","");
			wordAux = wordAux.replaceAll("\\{!-@\\}\\{[-]\\}","");
		}
		if(words.size() == 2) {
			if (mode.equals("or")) {
				HashMap <String, Stats> map1 = trie.contains(words.get(0));
				HashMap <String, Stats> map2 = trie.contains(words.get(1));
				if(map1 != null && map2 != null) {
					return null;
				}
				else if(map1 != null && map2 == null) {
					return null;
				}
				else if(map1 == null && map2 != null) {
					return null;
				}
				else {
					return "Palavras não encontradas ou impróprias";
				}
			}
			else {
				HashMap <String, Stats> map1 = trie.contains(words.get(0));
				HashMap <String, Stats> map2 = trie.contains(words.get(1));
				if(map1 != null && map2 != null) {
					return null;
				}
				else if(map1 != null && map2 == null) {
					return null;
				}
				else if(map1 == null && map2 != null) {
					return null;
				}
				else {
					return "Palavras não encontradas ou impróprias";
				}
			}
		}
		else if (words.size() == 1) {
			HashMap <String, Stats> map = trie.contains(words.get(0));
			if(map != null) {
				String s = "";
				for (Map.Entry <String, Stats > entry : map.entrySet()) {
					String file = entry.getKey();
					Stats stats = entry.getValue();
					HashMap<Integer,Integer> lines = stats.getLines();
				    for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
				        int line = entry2.getKey();
				        int occurrence = entry2.getValue();
				        if (occurrence > 1){
					        s = s.concat(file + ": " + occurrence + "​ ocorrência​s da palavra " + word + "​​ na linha " + line + "\n");
				        }
				        else{
					        s = s.concat(file + ": " + occurrence + "​ ocorrência​ da palavra " + word + "​​ na linha " + line + "\n");
				        }
				    }
				}
				return s;
			}
			else {
				return "Palavra não encontrada ou imprópria";
			}
			
		}
		else {
			return "Mais de duas palavras.";
		}
	}
	
	public Map<String, Stats> sortByValues(Map<String, Stats> map){
        List<Map.Entry<String, Stats>> entries = new LinkedList<Map.Entry<String, Stats>>(map.entrySet());
     
        Collections.sort(entries, new Comparator<Map.Entry<String, Stats>>() {

            @Override
            public int compare(Entry<String, Stats> o1, Entry<String, Stats> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String, Stats> sortedMap = new LinkedHashMap<String, Stats>();
        for(Map.Entry<String, Stats> entry: entries){
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

	
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
}
