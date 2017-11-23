package modules;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		java.util.Collections.sort(words);
		if(words.size() == 2) {
			return this.trie.contains(words.get(0)) + this.trie.contains(words.get(1));
		}
		else if (words.size() == 1) {
			return this.trie.contains(words.get(0));
		}
		else {
			return "Mais de duas palavras.";
		}
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
