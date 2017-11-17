package modules;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;

import database.Base;
import datastructure.Trie;

public class IndexModule {
	private Trie trie;
	private Base base;
	
	public IndexModule(Trie trie, Base base) {
		this.setTrie(trie);
		this.setBase(base);
	}
	
	public void index(String location) {
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream(location);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			HashSet <String> auxiliar = new HashSet<String>();
			String strLine;
			//Read File Line By Line
			location = location.substring(location.lastIndexOf("/") + 1);
			//File file = new File(location);
			for (int i = 1; (strLine = br.readLine()) != null; i++) {
				List<String> words = new ArrayList<String>(Arrays.asList(strLine.split(" ")));
				for (String word: words) {
					word = word.replaceAll(" " , "");
				}
				words.removeAll(Arrays.asList(""));
				for (String word: words) {
					//TODO Ver se números são importantes
					String result = word.replaceAll("[|_]*\\{!-/\\}\\{0-9\\}\\{:-@\\}\\{[-]\\}","").toLowerCase();
					this.trie.insert(result, location, i);
					auxiliar.add(word);
				}
			}
			base.put(location, auxiliar.size());
			//Close the input stream
			in.close();
			} catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
	}
	
	public void remove(String file) {
		file = file.substring(file.lastIndexOf("/") + 1);
		this.trie.remove(file);
	}
	
	public void update(String file) {
		this.trie.remove(file);
		this.index(file);
	}
	
	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	public Base show() {
		return this.base;
	}

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

}
