package modules;

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

	public String search(String word) {
		/* List<String> words = new ArrayList<String>(Arrays.asList(word.split(" ")));
		for (String wordAux: words) {
			wordAux = wordAux.replaceAll(" " , "");
			wordAux = wordAux.replaceAll("[|_]*\\{!-/\\}\\{0-9\\}\\{:-@\\}\\{[-]\\}","").toLowerCase();
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
		} */
		return this.trie.contains(word);
	}
}
