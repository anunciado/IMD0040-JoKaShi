package modules;

import java.util.HashMap;
import java.util.HashSet;
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
import java.util.Set;
import java.util.TreeMap;

import database.Base;
import database.Stats;
import datastructure.Node;
import datastructure.Trie;

/**
 * 
 * @author 	Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @author 	Luis Eduardo  (cruxiu@ufrn.edu.br)
 * @author 	Jo�o Paulo 	  (vilarjp93@gmail.com)
 * @version 04.12.2017
 */
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
			wordAux = wordAux.replaceAll("[-|_.,()';\"]+","");
			wordAux = wordAux.replaceAll("\\{!-@\\}\\{[-]\\}","");
		}	
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		StringBuffer strLevenshteinDistance = new StringBuffer();
		for (String wordAux: words) {
			Node nodeAux = trie.getNode(wordAux);
			if (nodeAux != null) {
				Map <String, Stats> map2 = nodeAux.getWords();
				transformNode(words, map, map2, wordAux, mode);
			}
			else {
				strLevenshteinDistance.append(levenshteinDistance(wordAux));
			}	
		}
		Map<String, List<String>> mapSorted = sort(map);
		StringBuffer strReturn = new StringBuffer();
		for (List<String> list : mapSorted.values()) {
			strReturn.append("\n");
			strReturn.append(String.join("\n", list));
		}
		strReturn.append("\n");
		strReturn.append(strLevenshteinDistance);
		return strReturn.toString();	
	}
	
	private void transformNode(List<String> words, Map<String, List<String>> map, Map <String, Stats> map2, String wordAux, String mode) {
		for (Map.Entry <String, Stats > entry : map2.entrySet()) {
			List<String> arraylist = new ArrayList<String>();
			String file = entry.getKey();
			Stats stats = entry.getValue();
			if (mode.equals("or")) {
				transformStats(arraylist, stats, file, wordAux);			
			}
			else {
				if (base.containsValues(file, words)) {
					transformStats(arraylist, stats, file, wordAux);
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
	
	private void transformStats(List<String> arraylist, Stats stats, String file, String wordAux){
		Map<Integer,Integer> lines = stats.getLines();
		for (Map.Entry<Integer, Integer> entry2 : lines.entrySet()) {
			int line = entry2.getKey();
			int occurrence = entry2.getValue();
			arraylist.add(file.substring(file.lastIndexOf("/") + 1) + ": " + occurrence + "​ occurrence(s) of the word " + wordAux + "​​ on the line " + line);
		}
	}

	private Map<String, List<String>> sort(Map<String, List<String>> treeMap){
		Map<String, List<String>> map = new TreeMap<String, List<String>>(treeMap);
        List<Map.Entry<String, List<String>>> entries = new LinkedList<Map.Entry<String, List<String>>>(map.entrySet());
     
        Collections.sort(entries, new Comparator<Map.Entry<String, List<String>>>() {

            @Override
            public int compare(Entry<String, List<String>> o1, Entry<String, List<String>> o2) {
            	List<String> occurences1 = o1.getValue();
            	List<String> occurences2 = o2.getValue();
            	Set <Integer> o1Set = new HashSet <Integer>();
            	Set <Integer> o2Set = new HashSet <Integer>();
            	for (String occurence : occurences1) {
            		o1Set.add(Integer.parseInt(occurence.substring(occurence.lastIndexOf(" ") + 1)));
            	}
            	for (String occurence : occurences2) {
            		o2Set.add(Integer.parseInt(occurence.substring(occurence.lastIndexOf(" ") + 1)));
            	}
                return o2Set.size() - o1Set.size();
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
			StringBuffer word = new StringBuffer();
			word.append(value.getLetter());
			addWords(words, value, word, wordAux);
		}
		StringBuffer strReturn = new StringBuffer();
		if(!words.isEmpty()) {
			strReturn.append("The word " + wordAux + " was not found, did you mean: \n");
			for (String word : words) {
				strReturn.append(word + "\n");
			}
		}
		else {
			strReturn.append("The word " + wordAux + " was not found. \n");
		}
		return strReturn.toString();
	}
	
	
	private void addWords(List<String> words, Node node, StringBuffer word, String wordAux) {
		if(node.hasChildren()) {
			if(word.length() - wordAux.length() <= 2) {
				HashMap<Character, Node> children = node.getChildren();
				for (Node value : children.values()) {
					if(value.isEnd()) {
						word.append(value.getLetter());
						if(calculateDistance(wordAux, word.toString()) <= 2 && wordAux.length() - word.length() <= 2) {
							words.add(word.toString());
						}		
					}
					else if(value.hasChildren()) {
						addWords(words, value, word.append(value.getLetter()), wordAux);
					}
				}
			}		
		}
	}

	private static int calculateDistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
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
