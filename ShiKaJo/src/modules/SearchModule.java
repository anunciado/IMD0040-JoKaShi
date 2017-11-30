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
import datastructure.Node;
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
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		String s1 = "";
		for (String wordAux: words) {
			Node nodeAux = trie.getNode(wordAux);
			if (nodeAux != null) {
				HashMap <String, Stats> map2 = nodeAux.getWords();
				for (Map.Entry <String, Stats > entry : map2.entrySet()) {
					List<String> arraylist = new ArrayList<String>();
					String file = entry.getKey();
					Stats stats = entry.getValue();
					if (mode.equals("or")) {
						HashMap<Integer,Integer> lines = stats.getLines();
						for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
							int line = entry2.getKey();
							int occurrence = entry2.getValue();
							arraylist.add(file + ": " + occurrence + "​ ocorrência​(s) da palavra " + wordAux + "​​ na linha " + line);
						}
					}
					else {
						if (base.containsValues(file, words)) {
							HashMap<Integer,Integer> lines = stats.getLines();
							for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
								int line = entry2.getKey();
								int occurrence = entry2.getValue();
								arraylist.add(file + ": " + occurrence + "​ ocorrência​(s) da palavra " + wordAux + "​​ na linha " + line);
							}
						}
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
			else {
				s1 = levenshteinDistance(wordAux);
			}	
		}
		Map<String, List<String>> mapSorted = sort(map);
		String s = "";
		for (List<String> list : mapSorted.values()) {
			s = s.concat("\n");
			s = s.concat(String.join("\n", list));
		}
		s = s.concat(s1);
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
	
	private String levenshteinDistance(String wordAux) {
		List <String> words = new ArrayList <String>();
		for (Node value : trie.getRoot().getChildren().values()) {
		  levenshteinDistance2(words, value, Character.toString(value.getLetter()), wordAux);
		}
		String s = "";
		for (String word : words) {
			System.out.println(word);
			s = s.concat(word + "\n");
		}
		return s;
	}
	
	
	public void levenshteinDistance2(List<String> words, Node node, String word, String wordAux) {
		if(node.hasChildren()) {
			if(word.length() - wordAux.length() <= 2) {
				HashMap<Character, Node> children = node.getChildren();
				for (Node value : children.values()) {
					if(value.isEnd()) {
						word = word.concat(Character.toString(value.getLetter()));
						if(calculateDistance(wordAux, word) <= 2 && wordAux.length() - word.length() <= 2) {
							words.add(word);
						}		
					}
					else if(value.hasChildren()) {
						levenshteinDistance2(words, value, word.concat(Character.toString(value.getLetter())), wordAux);
					}
				}
			}		
		}
	}

	public static int calculateDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
}
